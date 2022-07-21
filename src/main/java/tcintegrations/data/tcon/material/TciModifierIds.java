package tcintegrations.data.tcon.material;

import slimeknights.tconstruct.library.modifiers.ModifierId;

import tcintegrations.TCIntegrations;

public class TciModifierIds {

    public static final ModifierId mana = id("mana");
    public static final ModifierId livingwood = id("livingwood");
    public static final ModifierId moderate = id("moderate");
    public static final ModifierId waterPowered = id("water_powered");

    private TciModifierIds() {}

    private static ModifierId id(String name) {
        return new ModifierId(TCIntegrations.MODID, name);
    }

}
