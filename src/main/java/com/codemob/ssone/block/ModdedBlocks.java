package com.codemob.ssone.block;

import com.codemob.ssone.SSOne;
import com.codemob.ssone.item.ModdedItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SSOne.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModdedBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SSOne.MODID);
    public static final RegistryObject<Block> COPPER_FENCE_BLOCK = BLOCKS.register("copper_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                                         .strength(.75F)
                                         .sound(SoundType.COPPER)
                                         .mapColor(MapColor.COLOR_ORANGE)));
    public static final RegistryObject<Item> COPPER_FENCE_BLOCKITEM = ModdedItems.ITEMS.register("copper_fence",
            () -> new BlockItem(COPPER_FENCE_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Block> COPPER_LADDER_BLOCK = BLOCKS.register("copper_ladder",
            () -> new LadderBlock(BlockBehaviour.Properties.of()
                                          .strength(0.6F)
                                          .noOcclusion()
                                          .pushReaction(PushReaction.DESTROY)
                                          .sound(SoundType.COPPER)
                                          .mapColor(MapColor.COLOR_ORANGE)));
    public static final RegistryObject<Item> COPPER_LADDER_BLOCKITEM = ModdedItems.ITEMS.register("copper_ladder",
            () -> new BlockItem(COPPER_LADDER_BLOCK.get(), new Item.Properties()));

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(COPPER_FENCE_BLOCK); // Takes in an ItemLike, assumes block has registered item
            event.accept(COPPER_LADDER_BLOCK);
        }
    }
}
