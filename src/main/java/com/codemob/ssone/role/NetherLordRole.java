package com.codemob.ssone.role;

import com.codemob.ssone.role.system.DefaultRole;
import com.codemob.ssone.role.system.MagicAbilities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class NetherLordRole extends DefaultRole {
    @Override
    public UUID getDefaultUUID() {
        return UUID.fromString("0faa3273-9f71-4ee1-a63c-3faa927849c3"); // Dogoo_Dogster
    }

    @Override
    public @NotNull MagicAbilities getMagicAbilities() {
        return new MagicAbilities()
                .earth(.75)
                .air(.5)
                .fire(1.25)
                .water(.1)
                .dark(.75);
    }

    @Override
    public void onDeath(LivingDeathEvent event, ServerPlayer player) {
        ServerLevel level = (ServerLevel) player.level();
        level.explode(
                player,
                player.getX(),
                player.getY(.5),
                player.getZ(),
                5.0F,
                true,
                Level.ExplosionInteraction.NONE
        );
    }
}
