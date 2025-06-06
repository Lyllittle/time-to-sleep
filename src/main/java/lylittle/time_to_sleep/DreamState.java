package lylittle.time_to_sleep;

import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentState;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class DreamState extends PersistentState {
    private static final Map<UUID, BlockPos> DREAM_PLAYER_MAP = Map.of();

    public void setNewDimensionKey(UUID uuid){
        Collection<BlockPos> posList = DREAM_PLAYER_MAP.values();
        int BiggestX = -65536;
        for(BlockPos pos : posList){
            if(pos.getX()>BiggestX){
                BiggestX = pos.getX();
            }
        }
        BlockPos pos = new BlockPos(BiggestX+4096,5,0);
        setDimensionKey(uuid,pos);
    }

    public Boolean isOnTheMap(UUID uuid){
        return DREAM_PLAYER_MAP.containsKey(uuid);
    }

    public static final PersistentState.Type<DreamState> TYPE =
            new PersistentState.Type<>(
                    DreamState::new,
                    DreamState::fromNbt,
                    DataFixTypes.LEVEL);

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        NbtList list = new NbtList();

        for (Map.Entry<UUID, BlockPos> entry : DREAM_PLAYER_MAP.entrySet()) {
            UUID uuid = entry.getKey();
            BlockPos pos = entry.getValue();

            NbtCompound data = new NbtCompound();
            data.putString("uuid", uuid.toString());
            data.putLong("dimension", pos.asLong());

            list.add(data);
        }

        nbt.put("dream_player_map", list);
        return nbt;
    }

    public void readNbt(NbtCompound nbt){
        DREAM_PLAYER_MAP.clear();
        if (nbt.contains("dream_player_map")){
            NbtList list = nbt.getList("dream_player_map", NbtElement.COMPOUND_TYPE);
            for (NbtElement element : list){
                if (!(element instanceof NbtCompound compound)) continue;

                String uuidString = compound.getString("uuid");
                Long posLong = compound.getLong("dimension");

                try {
                    UUID uuid = UUID.fromString(uuidString);
                    BlockPos pos = BlockPos.fromLong(posLong);
                    DREAM_PLAYER_MAP.put(uuid, pos);
                } catch (IllegalArgumentException e) {
                    // Log warning or silently skip if the data is invalid
                    TimeToSleep.LOGGER.error("Skipping invalid dream dimension entry: " + e.getMessage());
                }
            }
        }
    }

    public static DreamState fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        DreamState state = new DreamState();
        state.readNbt(nbt);
        return state;
    }


    public BlockPos getDimensionKey(UUID uuid){
        return DREAM_PLAYER_MAP.get(uuid);
    }

    public void setDimensionKey(UUID uuid, BlockPos pos){
        DREAM_PLAYER_MAP.put(uuid, pos);
        markDirty();
    }
}
