package tcintegrations.data.tcon;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.modules.behavior.RepairModule;
import slimeknights.tconstruct.library.modifiers.modules.build.ModifierRequirementsModule;
import slimeknights.tconstruct.library.modifiers.modules.build.ModifierSlotModule;
import slimeknights.tconstruct.library.modifiers.modules.build.StatBoostModule;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.tools.SlotType;
import tcintegrations.common.capabilities.ToolEnergyHelper;
import tcintegrations.data.integration.ModIntegration;
import tcintegrations.data.tcon.material.TciModifierIds;
import tcintegrations.items.TCIntegrationsModifiers;
import tcintegrations.items.modifiers.armor.EngineersGogglesModifier;
import tcintegrations.items.modifiers.armor.MultiVisionModifier;

public class ModifierProvider extends AbstractModifierProvider implements IConditionBuilder {

    public ModifierProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    @NotNull
    public String getName() {
        return "TCIntegrations - TCon Modifiers";
    }

    @Override
    protected void addModifiers() {
        /* Create */
        buildModifier(TciModifierIds.engineersGoggles, modLoaded(ModIntegration.CREATE_MODID))
            .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
            .addModule(EngineersGogglesModifier.INSTANCE);
        /* Immersive Engineering */
        buildModifier(TciModifierIds.multiVision, modLoaded(ModIntegration.IE_MODID))
            .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
            .addModule(MultiVisionModifier.INSTANCE);
        /* Botania */
        buildModifier(TciModifierIds.livingwood)
                .addModule(RepairModule.builder().eachLevel(0.75F));
        /* Mythic Botany */
        buildModifier(TCIntegrationsModifiers.ALF_MODIFIER.getId(), modLoaded(ModIntegration.MYTHIC_BOTANY_MODID))
            .addModule(ModifierRequirementsModule.builder().requireModifier(TCIntegrationsModifiers.TERRA_MODIFIER.getId(), 1)
            .modifierKey(TCIntegrationsModifiers.ALF_MODIFIER.getId()).build());
        buildModifier(TCIntegrationsModifiers.ALFHEIM_MODIFIER.getId(), modLoaded(ModIntegration.MYTHIC_BOTANY_MODID))
            .addModule(ModifierRequirementsModule.builder().requireModifier(TCIntegrationsModifiers.TERRESTRIAL_MODIFIER.getId(), 1)
            .modifierKey(TCIntegrationsModifiers.ALFHEIM_MODIFIER.getId()).build());
        /* Energy */
        buildModifier(TciModifierIds.energy)
            .addModules(StatBoostModule.add(ToolEnergyHelper.ENERGY_CAPACITY_STAT).eachLevel(1000000),ToolEnergyHelper.ENERGY_HANDLER);
        /* Ars Nouveau */
        buildModifier(TCIntegrationsModifiers.ARS_MODIFIER.getId())
                .levelDisplay(new ModifierLevelDisplay.UniqueForLevels(3))
                .addModule(new ModifierSlotModule(SlotType.UPGRADE));
        /* Ars Elemental */
        buildModifier(TCIntegrationsModifiers.AEROMANCER_MODIFIER.getId(), modLoaded(ModIntegration.ARS_ELEMENTAL_MODID))
            .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
            .addModule(ModifierRequirementsModule.builder().requireModifier(TCIntegrationsModifiers.ARS_MODIFIER.getId(), 3)
            .modifierKey(TCIntegrationsModifiers.AEROMANCER_MODIFIER.getId()).build());
        buildModifier(TCIntegrationsModifiers.AQUAMANCER_MODIFIER.getId(), modLoaded(ModIntegration.ARS_ELEMENTAL_MODID))
            .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
            .addModule(ModifierRequirementsModule.builder().requireModifier(TCIntegrationsModifiers.ARS_MODIFIER.getId(), 3)
            .modifierKey(TCIntegrationsModifiers.AQUAMANCER_MODIFIER.getId()).build());
        buildModifier(TCIntegrationsModifiers.PYROMANCER_MODIFIER.getId(), modLoaded(ModIntegration.ARS_ELEMENTAL_MODID))
            .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
            .addModule(ModifierRequirementsModule.builder().requireModifier(TCIntegrationsModifiers.ARS_MODIFIER.getId(), 3)
            .modifierKey(TCIntegrationsModifiers.PYROMANCER_MODIFIER.getId()).build());
        buildModifier(TCIntegrationsModifiers.GEOMANCER_MODIFIER.getId(), modLoaded(ModIntegration.ARS_ELEMENTAL_MODID))
            .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
            .addModule(ModifierRequirementsModule.builder().requireModifier(TCIntegrationsModifiers.ARS_MODIFIER.getId(), 3)
            .modifierKey(TCIntegrationsModifiers.GEOMANCER_MODIFIER.getId()).build());
    }

}
