package com.codemob.ssone.item;

import com.codemob.ssone.SSOne;
import com.codemob.ssone.entity.ModdedEntities;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SSOne.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModdedItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SSOne.MODID);
    public static final RegistryObject<Item> MUSTARD_ITEM = ITEMS.register("mustard",
            () -> new MustardItem(new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(0.6F)
                            .alwaysEdible().build())));
    public static final RegistryObject<Item> BAGEL_ITEM = ITEMS.register("bagel",
            () -> new Item(new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(7)
                            .saturationModifier(0.6F)
                            .build())));
    public static final RegistryObject<Item> PEBBLE_ITEM = ITEMS.register("pebble",
            () -> new PebbleItem(new Item.Properties()));

    public static final RegistryObject<Item> COPPER_ROD_ITEM = ITEMS.register("copper_rod",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBBLED_ZOMBIE_SPAWN_EGG = ITEMS.register("cobbled_zombie_spawn_egg",
            () -> new ForgeSpawnEggItem(ModdedEntities.COBBLED_ZOMBIE, 0x888888, 0x222222,
                    new Item.Properties()));
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SSOne.MODID);

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(MUSTARD_ITEM); // Takes in an ItemLike, assumes block has registered item
            event.accept(BAGEL_ITEM);
        } else if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(COBBLED_ZOMBIE_SPAWN_EGG);
        } else if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(PEBBLE_ITEM);
            event.accept(COPPER_ROD_ITEM);
        }
    }
}