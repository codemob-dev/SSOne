package com.codemob.ssone.entity.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class CobbledZombieAnimation {
    public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(1.0F).looping()
                                                           .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                   new Keyframe(0.0F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                                                                   new Keyframe(0.5F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                                                                   new Keyframe(1.0F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
                                                           ))
                                                           .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                   new Keyframe(0.0F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                                                                   new Keyframe(0.5F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                                                                   new Keyframe(1.0F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
                                                           ))
                                                           .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                   new Keyframe(0.0F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                                                                   new Keyframe(0.25F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                                                                   new Keyframe(0.5F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                                                                   new Keyframe(0.75F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                                                                   new Keyframe(1.0F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
                                                           ))
                                                           .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                   new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                                                                   new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, -1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                                                                   new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                                                                   new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, 4.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                                                                   new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                                                           ))
                                                           .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                   new Keyframe(0.0F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                                                                   new Keyframe(0.25F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                                                                   new Keyframe(0.5F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                                                                   new Keyframe(0.75F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                                                                   new Keyframe(1.0F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
                                                           ))
                                                           .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                   new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                                                                   new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, 4.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                                                                   new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                                                                   new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                                                                   new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                                                           ))
                                                           .build();

    public static final AnimationDefinition ATTACK = AnimationDefinition.Builder.withLength(0.625F)
                                                             .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                     new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                                                                     new Keyframe(0.25F, KeyframeAnimations.degreeVec(-130.0457F, -38.4189F, -0.7333F), AnimationChannel.Interpolations.CATMULLROM),
                                                                     new Keyframe(0.375F, KeyframeAnimations.degreeVec(-73.5742F, 10.5117F, 12.9746F), AnimationChannel.Interpolations.CATMULLROM),
                                                                     new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
                                                             ))
                                                             .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                     new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                                                                     new Keyframe(0.25F, KeyframeAnimations.degreeVec(-77.211F, 37.9536F, 12.721F), AnimationChannel.Interpolations.CATMULLROM),
                                                                     new Keyframe(0.375F, KeyframeAnimations.degreeVec(-54.0715F, -5.5517F, -5.4841F), AnimationChannel.Interpolations.CATMULLROM),
                                                                     new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
                                                             ))
                                                             .build();
}
