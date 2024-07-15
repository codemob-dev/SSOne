package com.codemob.ssone.entity.goal;

import com.codemob.ssone.entity.custom.CobbledZombie;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import org.jetbrains.annotations.NotNull;

public class CobbledZombieMeleeAttackGoal extends MeleeAttackGoal {
    private final CobbledZombie zombie;
    private int ticksAttacking = -1;
    public CobbledZombieMeleeAttackGoal(CobbledZombie zombie) {
        super(zombie, 1.0, true);
        this.zombie = zombie;
    }

    @Override
    protected void checkAndPerformAttack(@NotNull LivingEntity target) {
        if (this.canPerformAttack(target)) {
            if (ticksAttacking == -1) {
                ticksAttacking = 0;
                zombie.attacking(true);
            } else if (ticksAttacking == 8) {
                this.zombie.doHurtTarget(target);
            } else if (ticksAttacking == 13) {
                zombie.attacking(false);
                resetAttackCooldown();
            }
        } else if (ticksAttacking > -1) {
            resetAttackCooldown();
            zombie.attacking(false);
        }
    }

    @Override
    protected void resetAttackCooldown() {
        super.resetAttackCooldown();
        ticksAttacking = -1;
    }

    @Override
    public void tick() {
        super.tick();

        if (ticksAttacking > -1) {
            ticksAttacking++;
        }
    }
}
