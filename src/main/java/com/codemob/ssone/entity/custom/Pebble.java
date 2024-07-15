package com.codemob.ssone.entity.custom;

import com.codemob.ssone.entity.ModdedEntities;
import com.codemob.ssone.item.ModdedItems;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class Pebble extends ThrowableItemProjectile {
    public Pebble(EntityType<? extends Pebble> type, Level level) {
        super(type, level);
    }

    public static Pebble createPebble(EntityType<? extends Pebble> type, Level level) {
        return new Pebble(type, level);
    }

    public Pebble(Level level, LivingEntity entity) {
        super(ModdedEntities.PEBBLE.get(), entity, level);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModdedItems.PEBBLE_ITEM.get();
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entity = hitResult.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 1.5F);
    }

    @Override
    protected void onHit(@NotNull HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            this.discard();
        }
    }
}
