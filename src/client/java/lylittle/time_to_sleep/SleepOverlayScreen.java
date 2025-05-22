package lylittle.time_to_sleep;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SleepOverlayScreen extends Screen {
    private int ticks = 0;

    protected SleepOverlayScreen() {
        super(Text.literal("Falling asleep..."));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        int alpha = Math.min(ticks * 4, 255);
        int color = (alpha << 24); // ARGB: black with increasing alpha

        context.fill(0, 0, width, height, color);
        ticks++;

        // Optional: close screen after fade
        if (ticks > 220) {
            onSleepAnimationComplete();
        }
    }

    private void onSleepAnimationComplete() {
        // Teleport or trigger transition
        MinecraftClient.getInstance().setScreen(null);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
