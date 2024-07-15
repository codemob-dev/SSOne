package com.codemob.ssone.entity.client;

import com.codemob.ssone.SSOne;
import com.codemob.ssone.entity.CobbledZombieModel;
import com.codemob.ssone.entity.custom.CobbledZombie;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CobbledZombieRenderer extends MobRenderer<CobbledZombie, CobbledZombieModel<CobbledZombie>> {
    public CobbledZombieRenderer(EntityRendererProvider.Context context) {
        super(context, new CobbledZombieModel<>(context.bakeLayer(ModModelLayers.COBBLED_ZOMBIE_LAYER)), .5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull CobbledZombie cobbledZombie) {
        return ResourceLocation.fromNamespaceAndPath(SSOne.MODID, "textures/entity/cobbled_zombie.png");
    }

    @Override
    public void render(CobbledZombie cobbledZombie, float yaw, float partialTicks,
                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int p_115313_) {
        if (cobbledZombie.isBaby()) {
            poseStack.scale(.5f, .5f, .5f);
        }
        poseStack.scale(cobbledZombie.SCALE, cobbledZombie.SCALE, cobbledZombie.SCALE);
        super.render(cobbledZombie, yaw, partialTicks, poseStack, buffer, p_115313_);
    }

}
