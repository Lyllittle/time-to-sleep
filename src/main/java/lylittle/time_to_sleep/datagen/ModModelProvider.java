package lylittle.time_to_sleep.datagen;

import lylittle.time_to_sleep.Blocks.Custom.DreamForestFloorBlock;
import lylittle.time_to_sleep.Blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.DREAM_FOREST_FLOOR)
                .coordinate(BlockStateVariantMap.create(DreamForestFloorBlock.BLOCK_ID)
                                .register(0, BlockStateVariant.create().put(VariantSettings.MODEL,Identifier.ofVanilla("block/podzol")))
                                .register(1, BlockStateVariant.create().put(VariantSettings.MODEL,Identifier.ofVanilla("block/amethyst_block")))
                                .register(2, BlockStateVariant.create().put(VariantSettings.MODEL,Identifier.ofVanilla("block/mossy_stone")))
                        )
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }
}
