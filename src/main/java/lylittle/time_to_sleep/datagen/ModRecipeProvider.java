package lylittle.time_to_sleep.datagen;

import lylittle.time_to_sleep.Items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BREWING, ModItems.EMBERGLAZE_ELIXIR)
                .input(Items.HONEY_BOTTLE)
                .input(Items.BLAZE_POWDER)
                .input(Items.PHANTOM_MEMBRANE)
                .input(Items.SOUL_SOIL)
                .input(Items.NETHER_WART)
                .input(ItemTags.FLOWERS)
                .criterion(hasItem(Items.HONEY_BOTTLE),conditionsFromItem(Items.HONEY_BOTTLE))
                .offerTo(exporter);
    }
}
