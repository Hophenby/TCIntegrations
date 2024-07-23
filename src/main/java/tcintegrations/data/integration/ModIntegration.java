package tcintegrations.data.integration;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import tcintegrations.common.CreativeTabs;
import tcintegrations.common.TCIntegrationsModule;

import static tcintegrations.util.ResourceLocationHelper.location;

public final class ModIntegration extends TCIntegrationsModule {

    public static final String BOTANIA_MODID = "botania";
    public static final String IE_MODID = "immersiveengineering";
    public static final String TCON_MODID = "tconstruct";
    public static final String CREATE_MODID = "create";
    public static final String AQUACULTURE_MODID = "aquaculture";
    public static final String ARS_MODID = "ars_nouveau";
    public static final String ARS_ELEMENTAL_MODID = "ars_elemental";
    public static final String ALEX_MODID = "alexsmobs";
    public static final String MALUM_MODID = "malum";
    public static final String UNDERGARDEN_MODID = "undergarden";
    public static final String BEYOND_EARTH_MODID = "beyond_earth";
    public static final String BYG_MODID = "byg";
    public static final String MEKANISM_MODID = "mekanism";
    //public static final String MEKANISM_TOOLS_MODID = "mekanismtools";
    public static final String MEKANISM_GENERATORS_MODID = "mekanismgenerators";
    //public static final String MEKANISM_ADDITIONS_MODID = "mekanismadditions";
    public static final String MYTHIC_BOTANY_MODID = "mythicbotany";
    public static final String IFD_MODID = "iceandfire";
    public static final String CONSECRATION_MODID = "consecration";
    public static final String AD_ASTRA_MODID = "ad_astra";

    public static Item BOTANIA_LIVINGWOOD_PLANKS;
    public static Item BEYOND_EARTH_CHEESE;
    public static Item SOURCE_GEM;
    public static Item SOURCE_GEM_BLOCK;
    public static Item MAGE_FIBER;
    public static Item BLAZE_FIBER;
    public static Item END_FIBER;
    public static Item DRAGON_BONE;
    public static Item FIRE_DRAGON_BLOOD;
    public static Item ICE_DRAGON_BLOOD;
    public static Item LIGHTNING_DRAGON_BLOOD;
    public static Item GHOST_INGOT;
    public static Item ALFSTEEL_INGOT;
    public static Item VOLTMETER;
    public static Item ROADRUNNER_FEATHER;
    public static Item BEAR_FUR;
    public static Item SPIKED_SCUTE;
    public static Item BISON_FUR;
    public static Item SERRATED_SHARK_TOOTH;
    public static Item SHARK_TOOTH;
    public static Item MOSQUITO_PROBOSCIS;
    public static Item CROCODILE_SCUTE;
    public static Item RACCOON_TAIL;
    public static Item PENDORITE_BLOCK;
    public static Item PENDORITE_SCRAPS;
    public static Item PENDORITE_INGOT;
    public static Item PENDORITE_ORE;
    public static Item RAW_PENDORITE;
    public static Item RAW_PENDORITE_BLOCK;
    public static Item MECHANICAL_ARM;
    public static Item GOGGLES;
    public static Item SOUL_STAINED_STEEL_INGOT;
    public static Item REDSTONE_HOOK;
    public static Item IRON_HOOK;
    public static Item NEPTUNIUM_INGOT;
    public static Item TIN_CAN;
    public static Item FISH_FILLET_RAW;
    public static Item FORGOTTEN_BLOCK;
    public static Item FORGOTTEN_INGOT;
    public static Item FORGOTTEN_NUGGET;
    public static Item MASTICATOR_SCALES;
    public static Item UTHERIUM_CRYSTAL;
    public static Item FROSTSTEEL_INGOT;
    public static Item CLOGGRUM_INGOT;
    public static Item INGOT_REFINED_GLOWSTONE;
    public static Item LIVINGWOOD_TWIG;
    public static Item RUNE_SPRING;
    public static Item RUNE_SUMMER;
    public static Item RUNE_AUTUMN;
    public static Item RUNE_WINTER;
    public static Item LIVING_ROCK;
    public static Item MANA_STRING;
    public static Item IFD_SILVER_METAL_HELMET;
    public static Item IFD_SILVER_METAL_CHESTPLATE;
    public static Item IFD_SILVER_METAL_LEGGINGS;
    public static Item IFD_SILVER_METAL_BOOTS;
    public static Item IFD_SILVER_AXE;
    public static Item IFD_SILVER_PICKAXE;
    public static Item IFD_SILVER_SHOVEL;
    public static Item IFD_SILVER_SWORD;
    public static Item IFD_SILVER_HOE;
    public static Item IFD_DRAGONARMOR_SILVER_HEAD;
    public static Item IFD_DRAGONARMOR_SILVER_NECK;
    public static Item IFD_DRAGONARMOR_SILVER_BODY;
    public static Item IFD_DRAGONARMOR_SILVER_TAIL;
    public static Item IFD_COPPER_METAL_HELMET;
    public static Item IFD_COPPER_METAL_CHESTPLATE;
    public static Item IFD_COPPER_METAL_LEGGINGS;
    public static Item IFD_COPPER_METAL_BOOTS;
    public static Item IFD_COPPER_AXE;
    public static Item IFD_COPPER_PICKAXE;
    public static Item IFD_COPPER_SHOVEL;
    public static Item IFD_COPPER_SWORD;
    public static Item IFD_COPPER_HOE;
    public static Item IFD_DRAGONARMOR_COPPER_HEAD;
    public static Item IFD_DRAGONARMOR_COPPER_NECK;
    public static Item IFD_DRAGONARMOR_COPPER_BODY;
    public static Item IFD_DRAGONARMOR_COPPER_TAIL;
    public static Item ARS_ELEMENTAL_MASTER_CORE;
    public static Item ARS_AIR_ESSENCE;
    public static Item ARS_FIRE_ESSENCE;
    public static Item ARS_WATER_ESSENCE;
    public static Item ARS_EARTH_ESSENCE;

    public static RegistryObject<MobEffect> OXYGEN_EFFECT;
    public static MobEffect ARS_SHOCKED;
    public static MobEffect ARS_ELEMENTAL_LIGHTNING_LURE;


    public static RegisterEvent.RegisterHelper<Item> ITEM_REGISTRY;

    public static void init(RegisterEvent.RegisterHelper<Item> registryHelper) {
        String dataGen = System.getenv("DATA_GEN");

        ITEM_REGISTRY = registryHelper;

        if (dataGen != null && dataGen.contains("all")) {
            BOTANIA_LIVINGWOOD_PLANKS = registerItem(botaniaLoc("livingwood_planks"));
            BEYOND_EARTH_CHEESE = registerItem(beyondEarthLoc("cheese"));
            SOURCE_GEM = registerItem(arsLoc("source_gem"));
            SOURCE_GEM_BLOCK = registerItem(arsLoc("source_gem_block"));
            MAGE_FIBER = registerItem(arsLoc("magebloom_fiber"));
            BLAZE_FIBER = registerItem(arsLoc("blaze_fiber"));
            END_FIBER = registerItem(arsLoc("end_fiber"));
            DRAGON_BONE = registerItem(ifdLoc("dragonbone"));
            FIRE_DRAGON_BLOOD = registerItem(ifdLoc("fire_dragon_blood"));
            ICE_DRAGON_BLOOD = registerItem(ifdLoc("ice_dragon_blood"));
            LIGHTNING_DRAGON_BLOOD = registerItem(ifdLoc("lightning_dragon_blood"));
            GHOST_INGOT = registerItem(ifdLoc("ghost_ingot"));
            ALFSTEEL_INGOT = registerItem(mbotLoc("alfsteel_ingot"));
            VOLTMETER = registerItem(ieLoc("voltmeter"));
            ROADRUNNER_FEATHER = registerItem(alexLoc("roadrunner_feather"));
            BEAR_FUR = registerItem(alexLoc("bear_fur"));
            SPIKED_SCUTE = registerItem(alexLoc("spiked_scute"));
            BISON_FUR = registerItem(alexLoc("bison_fur"));
            SERRATED_SHARK_TOOTH = registerItem(alexLoc("serrated_shark_tooth"));
            SHARK_TOOTH = registerItem(alexLoc("shark_tooth"));
            MOSQUITO_PROBOSCIS = registerItem(alexLoc("mosquito_proboscis"));
            CROCODILE_SCUTE = registerItem(alexLoc("crocodile_scute"));
            RACCOON_TAIL = registerItem(alexLoc("raccoon_tail"));
            PENDORITE_BLOCK = registerItem(bygLoc("pendorite_block"));
            PENDORITE_SCRAPS = registerItem(bygLoc("pendorite_scraps"));
            PENDORITE_INGOT = registerItem(bygLoc("pendorite_ingot"));
            PENDORITE_ORE = registerItem(bygLoc("pendorite_ore"));
            RAW_PENDORITE = registerItem(bygLoc("raw_pendorite"));
            RAW_PENDORITE_BLOCK = registerItem(bygLoc("raw_pendorite_block"));
            MECHANICAL_ARM = registerItem(createLoc("mechanical_arm"));
            GOGGLES = registerItem(createLoc("goggles"));
            SOUL_STAINED_STEEL_INGOT = registerItem(malumLoc("soul_stained_steel_ingot"));
            REDSTONE_HOOK = registerItem(aquaLoc("redstone_hook"));
            IRON_HOOK = registerItem(aquaLoc("iron_hook"));
            NEPTUNIUM_INGOT = registerItem(aquaLoc("neptunium_ingot"));
            TIN_CAN = registerItem(aquaLoc("tin_can"));
            FISH_FILLET_RAW = registerItem(aquaLoc("fish_fillet_raw"));
            FORGOTTEN_BLOCK = registerItem(ugLoc("forgotten_block"));
            FORGOTTEN_INGOT = registerItem(ugLoc("forgotten_ingot"));
            FORGOTTEN_NUGGET = registerItem(ugLoc("forgotten_nugget"));
            MASTICATOR_SCALES = registerItem(ugLoc("masticator_scales"));
            UTHERIUM_CRYSTAL = registerItem(ugLoc("utherium_crystal"));
            FROSTSTEEL_INGOT = registerItem(ugLoc("froststeel_ingot"));
            CLOGGRUM_INGOT = registerItem(ugLoc("cloggrum_ingot"));
            INGOT_REFINED_GLOWSTONE = registerItem(mekanismLoc("ingot_refined_glowstone"));
            LIVINGWOOD_TWIG = registerItem(botaniaLoc("livingwood_twig"));
            RUNE_SPRING = registerItem(botaniaLoc("rune_spring"));
            RUNE_SUMMER = registerItem(botaniaLoc("rune_summer"));
            RUNE_AUTUMN = registerItem(botaniaLoc("rune_autumn"));
            RUNE_WINTER = registerItem(botaniaLoc("rune_winter"));
            LIVING_ROCK = registerItem(botaniaLoc("livingrock"));
            MANA_STRING = registerItem(botaniaLoc("mana_string"));
            IFD_SILVER_METAL_HELMET = registerItem(ifdLoc("armor_silver_metal_helmet"));
            IFD_SILVER_METAL_CHESTPLATE = registerItem(ifdLoc("armor_silver_metal_chestplate"));
            IFD_SILVER_METAL_LEGGINGS = registerItem(ifdLoc("armor_silver_metal_leggings"));
            IFD_SILVER_METAL_BOOTS = registerItem(ifdLoc("armor_silver_metal_boots"));
            IFD_SILVER_AXE = registerItem(ifdLoc("silver_axe"));
            IFD_SILVER_PICKAXE = registerItem(ifdLoc("silver_pickaxe"));
            IFD_SILVER_SHOVEL = registerItem(ifdLoc("silver_shovel"));
            IFD_SILVER_SWORD = registerItem(ifdLoc("silver_sword"));
            IFD_SILVER_HOE = registerItem(ifdLoc("silver_hoe"));
            IFD_DRAGONARMOR_SILVER_HEAD = registerItem(ifdLoc("dragonarmor_silver_head"));
            IFD_DRAGONARMOR_SILVER_NECK = registerItem(ifdLoc("dragonarmor_silver_neck"));
            IFD_DRAGONARMOR_SILVER_BODY = registerItem(ifdLoc("dragonarmor_silver_body"));
            IFD_DRAGONARMOR_SILVER_TAIL = registerItem(ifdLoc("dragonarmor_silver_tail"));
            IFD_COPPER_METAL_HELMET = registerItem(ifdLoc("armor_copper_metal_helmet"));
            IFD_COPPER_METAL_CHESTPLATE = registerItem(ifdLoc("armor_copper_metal_chestplate"));
            IFD_COPPER_METAL_LEGGINGS = registerItem(ifdLoc("armor_copper_metal_leggings"));
            IFD_COPPER_METAL_BOOTS = registerItem(ifdLoc("armor_copper_metal_boots"));
            IFD_COPPER_AXE = registerItem(ifdLoc("copper_axe"));
            IFD_COPPER_PICKAXE = registerItem(ifdLoc("copper_pickaxe"));
            IFD_COPPER_SHOVEL = registerItem(ifdLoc("copper_shovel"));
            IFD_COPPER_SWORD = registerItem(ifdLoc("copper_sword"));
            IFD_COPPER_HOE = registerItem(ifdLoc("copper_hoe"));
            IFD_DRAGONARMOR_COPPER_HEAD = registerItem(ifdLoc("dragonarmor_copper_head"));
            IFD_DRAGONARMOR_COPPER_NECK = registerItem(ifdLoc("dragonarmor_copper_neck"));
            IFD_DRAGONARMOR_COPPER_BODY = registerItem(ifdLoc("dragonarmor_copper_body"));
            IFD_DRAGONARMOR_COPPER_TAIL = registerItem(ifdLoc("dragonarmor_copper_tail"));
            ARS_ELEMENTAL_MASTER_CORE = registerItem(arsElementalLoc("mark_of_mastery"));
            ARS_AIR_ESSENCE = registerItem(arsElementalLoc("air_essence"));
            ARS_FIRE_ESSENCE = registerItem(arsElementalLoc("fire_essence"));
            ARS_WATER_ESSENCE = registerItem(arsElementalLoc("water_essence"));
            ARS_EARTH_ESSENCE = registerItem(arsElementalLoc("earth_essence"));
        }
        if (canLoad(ARS_MODID)) {
            ARS_SHOCKED = com.hollingsworth.arsnouveau.common.potions.ModPotions.SHOCKED_EFFECT.get();
        }
        if (canLoad(ARS_ELEMENTAL_MODID)) {
            ARS_ELEMENTAL_LIGHTNING_LURE = alexthw.ars_elemental.registry.ModPotions.LIGHTNING_LURE.get();
        }

    }

    public static void setup() {
        OXYGEN_EFFECT = EFFECTS_REGISTRY.register(
        "oxygen_bubble_effect", () -> new OxygenEffect(MobEffectCategory.BENEFICIAL, 3035801));
    }

    private static class OxygenEffect extends MobEffect {

        public OxygenEffect(MobEffectCategory typeIn, int color) {
            super(typeIn, color);
        }

        @Override
        public void applyEffectTick(LivingEntity entity, int amplifier) {
            entity.setAirSupply(300);
        }

    }

    private static Item registerItem(ResourceLocation loc) {
        Item item = (new Item(new Item.Properties().tab(CreativeTabs.INTEGRATION_TAB_GROUP)));

        ITEM_REGISTRY.register(loc, item);

        return item;
    }

    public static ResourceLocation botaniaLoc(String name) {
        return location(BOTANIA_MODID, name);
    }

    public static ResourceLocation malumLoc(String name) {
        return location(MALUM_MODID, name);
    }

    public static ResourceLocation beyondEarthLoc(String name) {
        return location(BEYOND_EARTH_MODID, name);
    }

    public static ResourceLocation bygLoc(String name) {
        return location(BYG_MODID, name);
    }

    public static ResourceLocation arsLoc(String name) {
        return location(ARS_MODID, name);
    }
    public static ResourceLocation arsElementalLoc(String name) {
        return location(ARS_ELEMENTAL_MODID, name);
    }

    public static ResourceLocation ifdLoc(String name) {
        return location(IFD_MODID, name);
    }

    public static ResourceLocation mbotLoc(String name) {
        return location(MYTHIC_BOTANY_MODID, name);
    }

    public static ResourceLocation ieLoc(String name) {
        return location(IE_MODID, name);
    }

    public static ResourceLocation alexLoc(String name) {
        return location(ALEX_MODID, name);
    }

    public static ResourceLocation createLoc(String name) {
        return location(CREATE_MODID, name);
    }

    public static ResourceLocation aquaLoc(String name) {
        return location(AQUACULTURE_MODID, name);
    }

    public static ResourceLocation ugLoc(String name) {
        return location(UNDERGARDEN_MODID, name);
    }

    public static ResourceLocation mekanismLoc(String name) {
        return location(MEKANISM_MODID, name);
    }
    public static ResourceLocation mekgenLoc(String name) {
        return location(MEKANISM_GENERATORS_MODID, name);
    }

    public static ResourceLocation adAstraLoc(String name) {
        return location(AD_ASTRA_MODID, name);
    }

    public static boolean canLoad(String modid) {
        String dataGen = System.getenv("DATA_GEN");

        return (dataGen != null && dataGen.contains("all")) || ModList.get().isLoaded(modid);
    }

}
