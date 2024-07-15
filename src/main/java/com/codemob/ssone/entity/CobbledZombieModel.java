package com.codemob.ssone.entity;// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.codemob.ssone.entity.animations.CobbledZombieAnimation;
import com.codemob.ssone.entity.custom.CobbledZombie;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class CobbledZombieModel<T extends CobbledZombie> extends HierarchicalModel<T> {
    private final ModelPart zombie;
    private final ModelPart head;

    public CobbledZombieModel(ModelPart root) {
        this.zombie = root.getChild("zombie");
        this.head = zombie.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition zombie = partdefinition.addOrReplaceChild("zombie", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition head = zombie.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -2.0F, 0.0F));

        head.addOrReplaceChild("headwear", CubeListBuilder.create().texOffs(38, 29).addBox(-2.0F, -2.0F, -4.2F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(4, 37).addBox(-3.0F, -4.0F, -4.2F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
        .texOffs(4, 37).addBox(1.0F, -4.0F, -4.2F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
        .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
        .texOffs(8, 0).addBox(-1.0F, -6.0F, -4.1F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(9, 8).addBox(-2.0F, -7.0F, -4.2F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(45, 22).addBox(-4.2F, -7.0F, -1.0F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
        .texOffs(46, 23).addBox(-4.05F, -4.0F, -2.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(21, 23).addBox(3.1F, -7.0F, -1.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(22, 24).addBox(3.2F, -6.0F, -3.0F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
        .texOffs(24, 26).addBox(3.4F, -5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        zombie.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -2.0F, 0.0F));

        zombie.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
        .texOffs(8, 0).addBox(0.0F, -2.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        zombie.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(8, 0).addBox(-2.0F, -2.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.0F, 0.0F, 0.0F));

        zombie.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.1F, 10.0F, 0.0F));

        zombie.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.9F, 10.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(CobbledZombieAnimation.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);

        this.animate(entity.attackAnimationState, CobbledZombieAnimation.ATTACK, ageInTicks);
    }

    private void applyHeadRotation(float netHeadYaw, float headPitch) {
        netHeadYaw = Mth.clamp(netHeadYaw, -30.0F, 30.0F);
        headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        zombie.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public @NotNull ModelPart root() {
        return zombie;
    }
}