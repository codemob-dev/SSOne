package com.codemob.ssone.entity;

import com.codemob.ssone.SSOne;
import com.codemob.ssone.entity.client.CobbledZombieRenderer;
import com.codemob.ssone.entity.custom.CobbledZombie;
import com.codemob.ssone.entity.custom.Pebble;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModdedEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SSOne.MODID);
    public static final RegistryObject<EntityType<CobbledZombie>> COBBLED_ZOMBIE =
            ENTITY_TYPES.register("cobbled_zombie", () -> EntityType.Builder.of(CobbledZombie::new, MobCategory.MONSTER)
                                                            .build("cobbled_zombie"));
    public static final RegistryObject<EntityType<Pebble>> PEBBLE =
            ENTITY_TYPES.register("pebble", () -> EntityType.Builder.of(Pebble::createPebble, MobCategory.MISC)
                                                                  .sized(0.25F, 0.25F)
                                                                  .build("pebble"));

    public static void registerRenderers() {
        EntityRenderers.register(ModdedEntities.COBBLED_ZOMBIE.get(), CobbledZombieRenderer::new);
        EntityRenderers.register(ModdedEntities.PEBBLE.get(), ThrownItemRenderer::new);
    }
}
