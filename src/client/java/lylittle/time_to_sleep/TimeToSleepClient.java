package lylittle.time_to_sleep;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class TimeToSleepClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		PayloadTypeRegistry.playS2C().register(DreamNetworking.SleepPayload.ID, DreamNetworking.SleepPayload.CODEC);

		ClientPlayNetworking.registerGlobalReceiver(DreamNetworking.SleepPayload.ID, (payload, context) -> {
			context.client().execute(() -> {
				context.client().setScreen(new SleepOverlayScreen());
			});
		});
	}
}
