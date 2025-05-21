package lylittle.time_to_sleep.Blocks;

import lylittle.time_to_sleep.Blocks.Custom.DreamForestFloorBlock;
import lylittle.time_to_sleep.TimeToSleep;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block DREAM_FOREST_FLOOR = registerBlock("dream_forest_floor", new DreamForestFloorBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK)));

    private static Block registerBlock(String name, Block block){
        Registry.register(Registries.BLOCK, Identifier.of(TimeToSleep.MOD_ID,name),block);
        Registry.register(Registries.ITEM,Identifier.of(TimeToSleep.MOD_ID,name),new BlockItem(block, new Item.Settings()));
        return block;
    }

    public static void registerBlocks(){
        TimeToSleep.LOGGER.info("Registering Blocks for "+TimeToSleep.MOD_ID);
    }
}


