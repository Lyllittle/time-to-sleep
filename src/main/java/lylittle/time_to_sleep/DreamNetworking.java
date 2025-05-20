package lylittle.time_to_sleep;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class DreamNetworking {
    public static final Identifier SLEEP_PACKET_ID = Identifier.of(TimeToSleep.MOD_ID, "sleep_packet");
    public record SleepPayload() implements CustomPayload {
        public static final CustomPayload.Id<SleepPayload> ID = new CustomPayload.Id<>(SLEEP_PACKET_ID);
        public static final PacketCodec<RegistryByteBuf, SleepPayload> CODEC = PacketCodec.unit(new SleepPayload());

        @Override
        public CustomPayload.Id<? extends CustomPayload> getId() {
            return ID;
        }
    }

    public static void sendSleepAnimation(ServerPlayerEntity player){
        ServerPlayNetworking.send(player, new SleepPayload());
    }
}
