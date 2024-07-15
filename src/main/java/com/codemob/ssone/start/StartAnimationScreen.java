package com.codemob.ssone.start;

import me.srrapero720.watermedia.api.player.SyncVideoPlayer;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

public class StartAnimationScreen extends Screen {
    private float timePassed = 0F;
    private Screen screenToOpen = null;
    private SyncVideoPlayer player;
    private static final String videoURL = "";
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
        super.onClose();
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    public float getDuration() {
        return 100F;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float deltaTime) {
        timePassed += deltaTime;

    }
}
