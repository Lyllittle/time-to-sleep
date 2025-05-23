package lylittle.time_to_sleep.Items.Custom;

import lylittle.time_to_sleep.Dimension;
import lylittle.time_to_sleep.DreamNetworking;
import lylittle.time_to_sleep.DreamState;
import lylittle.time_to_sleep.TimeToSleep;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

public class EmberGlazeElixirItem extends Item {
    public EmberGlazeElixirItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof ServerPlayerEntity player) {
            if (isNearSoulCampfire(world, player.getBlockPos(), 3)) {
                DreamNetworking.sendSleepAnimation(player);
                PersistentStateManager manager = world.getServer().getOverworld().getPersistentStateManager();
                DreamState dreamState = manager.getOrCreate(
                        DreamState.TYPE,"dream_dimensions"
                );
                if (!dreamState.isOnTheMap(user.getUuid())){
                    dreamState.setNewDimensionKey(user.getUuid());
                }
                Dimension.tp(user.getUuid(),user);
            }
        }

        return super.finishUsing(stack, world, user);
    }

    private boolean isNearSoulCampfire(World world, BlockPos pos, int radius) {
        BlockPos.Mutable searchPos = new BlockPos.Mutable();

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -1; dy <= 2; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    searchPos.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
                    BlockState state = world.getBlockState(searchPos);
                    if (state.getBlock() == Blocks.SOUL_CAMPFIRE && state.get(CampfireBlock.LIT)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
}
