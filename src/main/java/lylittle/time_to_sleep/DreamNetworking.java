package lylittle.time_to_sleep;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class DreamNetworking {
    public record SleepAnimationPayload() implements CustomPayload {
        public static final CustomPayload.Id<SleepAnimationPayload> ID =
                new CustomPayload.Id<>(Identifier.of(TimeToSleep.MOD_ID, "sleep_animation"));

        public static final Codec<SleepAnimationPayload> CODEC = Codec.unit(new SleepAnimationPayload());

        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }
    }


    public static void sendSleepAnimation(ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new SleepAnimationPayload());
    }
}
