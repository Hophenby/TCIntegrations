package tcintegrations.common.capabilities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.INamespacedNBTView;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import tcintegrations.TCIntegrations;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class ToolEnergyCapability extends EnergyModifierHookIterator<ModifierEntry> implements IEnergyStorage {

    /*
        total_cell will be a fixed value 1 for each tool
     */
    public static final ResourceLocation TOTAL_CELL = new ResourceLocation(TCIntegrations.MODID, "total_cell");
    public static final ModuleHook<EnergyModifierHook> HOOK = ModifierHooks.register(
            new ResourceLocation((TCIntegrations.MODID), "energy"),
            EnergyModifierHook.class,
            EnergyModifierMerger::new,
            new EnergyModifierHook() {
                @Override
                public int receiveEnergy(IToolStackView tool, ModifierEntry modifier, int maxReceive, boolean simulate) {
                    return 0;
                }

                @Override
                public int extractEnergy(IToolStackView tool, ModifierEntry modifier, int maxExtract, boolean simulate) {
                    return 0;
                }
                @Override
                public int getCellCount(INamespacedNBTView volatileData, ModifierEntry modifier) {
                    return 1;
                }
            }
    );
    @Getter
    private final ItemStack itemStack;
    private final Supplier<? extends IToolStackView> tool;

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return receiveEnergy(tool.get(), maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return extractEnergy(tool.get(), maxExtract, simulate);
    }


    /*
    public int getEnergy(int cellIndex) {
        IToolStackView tool = this.tool.get();
        EnergyModifierHook hook = findHook(tool, cellIndex);
        return hook != null ? hook.getEnergy(tool, indexEntry, cellIndex - startIndex) : 0;
    }
     */

    @Override
    public int getEnergyStored() {
        return this.tool.get().getPersistentData().getInt(
                ToolEnergyHelper.ENERGY_HELPER.getKey()
        );
    }

    @Override
    public int getMaxEnergyStored() {
        return this.tool.get().getStats().getInt(ToolEnergyHelper.ENERGY_CAPACITY_STAT);
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }

    @Override
    protected Iterator<ModifierEntry> getIterator(IToolStackView tool) {
        return tool.getModifierList().iterator();
    }

    @Override
    protected EnergyModifierHook getHook(ModifierEntry entry) {

        indexEntry = entry;
        return entry.getHook(HOOK);
    }

    /* 添加cell */
    public static void addCells(ModifierEntry modifier, NamespacedNBT volatileData, EnergyModifierHook hook) {
        int cellCount = volatileData.getInt(TOTAL_CELL);
        volatileData.putInt(TOTAL_CELL, cellCount + hook.getCellCount(volatileData, modifier));
    }


    /**
     * The same logic as the fluid modifier hook iterator, but for energy.
     *
     * (<a href="https://github.com/SlimeKnights/TinkersConstruct/commit/988d84622056ce41aa34f937bcca86300a4ec383">Tinker's Construct #988d846</a>)
     *
     * Instead of shuffling the owner with every modifier implementing tanks, we have a single modifier that implements the tank, and other modifiers just add it as a trait
     * Modifiers using tanks can use the new helper to get and modify the fluid, instead of needing to talk to the module directly
     * Tank capacity has been moved from a volatile integer to a tool stat, reducing the need to have specialized modules for it. Custom tanks will just want to add their own custom stats or can handle capacity in another way if they prefer
     * Repackaged a few capability stuff as part of this commit
     * This refactor notably fixes fluid overflowing when tank capacity changes
     *
     */
    public interface EnergyModifierHook {
        default int getCellCount(INamespacedNBTView volatileData, ModifierEntry modifier) {
            return 1;
        }

        /**
         * get the capacity of a cell
         *
         */
        default int getCellCapacity(IToolStackView tool, ModifierEntry modifier, int cellIndex) {
            return 0;
        }

        /**
         *  get the energy stored in a cell
         */
        default int getEnergy(IToolStackView tool, ModifierEntry modifier, int cellIndex) {
            return 0;
        }

        int receiveEnergy(IToolStackView tool, ModifierEntry modifier, int maxReceive, boolean simulate);
        int extractEnergy(IToolStackView tool, ModifierEntry modifier, int maxExtract, boolean simulate);
    }
    @RequiredArgsConstructor
    private static class EnergyModifierMerger extends EnergyModifierHookIterator<EnergyModifierHook> implements EnergyModifierHook {
        private final Collection<EnergyModifierHook> hooks;
        @Override
        public int receiveEnergy(IToolStackView tool, ModifierEntry modifier, int maxReceive, boolean simulate) {
            indexEntry = modifier;
            return receiveEnergy(tool, maxReceive, simulate);
        }

        @Override
        public int extractEnergy(IToolStackView tool, ModifierEntry modifier, int maxExtract, boolean simulate) {
            indexEntry = modifier;
            return extractEnergy(tool, maxExtract, simulate);
        }

        @Override
        protected Iterator<EnergyModifierHook> getIterator(IToolStackView tool) {
            return hooks.iterator();
        }

        @Override
        protected EnergyModifierHook getHook(EnergyModifierHook entry) {
            return entry;
        }

        @Nullable
        private EnergyModifierHook findHook(IToolStackView tool, ModifierEntry modifier, int cellIndex) {
            indexEntry = modifier;
            return this.findHook(tool, cellIndex);
        }
        @Override
        public int getCellCount(INamespacedNBTView volatileData, ModifierEntry modifier) {
            return hooks.stream().mapToInt(hook -> hook.getCellCount(volatileData, modifier)).sum();
        }
        @Override
        public int getCellCapacity(IToolStackView tool, ModifierEntry modifier, int cellIndex) {
            return this.findHook(null, modifier, cellIndex)!=null ?
                    this.findHook(null, modifier, cellIndex).getCellCapacity(tool, modifier, cellIndex - startIndex) :
                    0;
        }
        @Override
        public int getEnergy(IToolStackView tool, ModifierEntry modifier, int cellIndex) {
            return this.findHook(null, modifier, cellIndex)!=null ?
                    this.findHook(null, modifier, cellIndex).getEnergy(tool, modifier, cellIndex - startIndex) :
                    0;
        }


    }

    /*

    public static class TinkerProvider implements ToolCapabilityProvider.IToolCapabilityProvider {
        private final LazyOptional<IEnergyStorage> handler;
        public TinkerProvider(ItemStack stack, Supplier<? extends IToolStackView> toolStack) {
            this.handler = LazyOptional.of(() -> new ToolEnergyCapability(stack, toolStack));
        }
        @Override
        @NotNull
        public <T> LazyOptional<T> getCapability(IToolStackView tool, Capability<T> cap) {
            if (
                    cap != ForgeCapabilities.ENERGY ||
                            tool.getVolatileData().getInt(TOTAL_CELL) == 0) {
                return LazyOptional.empty();
            }
            return this.handler.cast();
        }
    }

     */


    public static class ForgeProvider implements ICapabilityProvider {
        private final LazyOptional<IEnergyStorage> handler;
        private final ToolStack toolStack;

        public ForgeProvider(ItemStack itemStack) {
            this.toolStack = ToolStack.from(itemStack);
            this.handler = LazyOptional.of(() -> new ToolEnergyCapability(itemStack, () -> toolStack));
        }

        @Override
        @NotNull
        public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable net.minecraft.core.Direction side) {
            if (cap == ForgeCapabilities.ENERGY &&
                    toolStack.getVolatileData().getInt(TOTAL_CELL) > 0
            ) {
                return this.handler.cast();
            }
            return LazyOptional.empty();
        }
    }

}
