package com.codemob.ssone.start;

import com.mojang.blaze3d.vertex.*;
import me.srrapero720.watermedia.api.math.MathAPI;
import me.srrapero720.watermedia.api.player.SyncVideoPlayer;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static com.mojang.blaze3d.systems.RenderSystem.*;

public class StartAnimationScreen extends Screen {
    private Screen screenToOpen = null;
    private SyncVideoPlayer player;

    private static final String videoURL = "https://github.com/codemob-dev/SSOne/raw/master/start_anim.mp4";
    public void setScreenToOpen(Screen screenToOpen) {
        this.screenToOpen = screenToOpen;
    }

    public StartAnimationScreen() {
        super(GameNarrator.NO_TITLE);
    }

    @Override
    protected void init() {
        player = new SyncVideoPlayer(Minecraft.getInstance());
        player.setRepeatMode(false);
        player.start(videoURL);
    }

    @Override
    public void tick() {
        super.tick();
        if (player.isEnded()) {
            Minecraft.getInstance().setScreen(screenToOpen);
        }
    }

    @Override
    public void onClose() {
        player.release();
        super.onClose();
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float deltaTime) {
        if (player.getDimensions() == null) return;

        var texture = player.getGlTexture();

        enableBlend();
        graphics.fill(0, 0, width, height, MathAPI.argb(255, 0, 0, 0));
        disableBlend();

        bindTexture(texture);
        setShader(GameRenderer::getPositionTexColorShader);
        setShaderTexture(0, texture);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

        // Get video dimensions
        Dimension videoDimensions = player.getDimensions();
        double videoWidth = videoDimensions.getWidth();
        double videoHeight = videoDimensions.getHeight();

        // Calculate aspect ratios for both the screen and the video
        float screenAspectRatio = (float) width / height;
        float videoAspectRatio = (float) ((float) videoWidth / videoHeight);

        // New dimensions for rendering video texture
        int renderWidth, renderHeight;

        // If video's aspect ratio is greater than screen's, it means video's width needs to be scaled down to screen's width
        if(videoAspectRatio > screenAspectRatio) {
            renderWidth = width;
            renderHeight = (int) (width / videoAspectRatio);
        } else {
            renderWidth = (int) (height * videoAspectRatio);
            renderHeight = height;
        }

        int xOffset = (width - renderWidth) / 2; // xOffset for centering the video
        int yOffset = (height - renderHeight) / 2; // yOffset for centering the video

        Matrix4f matrix4f = graphics.pose().last().pose();

        enableBlend();
        BufferBuilder bufferbuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        bufferbuilder.addVertex(matrix4f, xOffset, yOffset, 0).setUv(0, 0).setUv(0, 0).setColor(-1);
        bufferbuilder.addVertex(matrix4f, xOffset, yOffset + renderHeight, 0).setUv(0, 1).setColor(-1);
        bufferbuilder.addVertex(matrix4f, xOffset + renderWidth, yOffset + renderHeight, 0).setUv(1, 1).setColor(-1);
        bufferbuilder.addVertex(matrix4f, xOffset + renderWidth, yOffset, 0).setUv(1, 0).setColor(-1);
        BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());

        disableBlend();
    }
}
