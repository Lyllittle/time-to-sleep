package lylittle.time_to_sleep.Items;

import lylittle.time_to_sleep.Items.Custom.EmberGlazeElixirItem;
import lylittle.time_to_sleep.TimeToSleep;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item EMBERGLAZE_ELIXIR = registerItem("emberglaze_elixir",new EmberGlazeElixirItem(new Item.Settings()
            .food(new FoodComponent.Builder().nutrition(2).saturationModifier(0.0f).alwaysEdible().build())
            .maxCount(16)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(TimeToSleep.MOD_ID,name),item);
    }
}
