package lylittle.time_to_sleep.Blocks.Custom;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

public class DreamForestFloorBlock extends Block {
    public static final IntProperty BLOCK_ID = IntProperty.of("block_id",0,2);

    public DreamForestFloorBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(BLOCK_ID,0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BLOCK_ID);
    }
}
