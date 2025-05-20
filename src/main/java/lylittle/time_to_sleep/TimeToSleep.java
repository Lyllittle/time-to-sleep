package lylittle.time_to_sleep;

import lylittle.time_to_sleep.Items.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeToSleep implements ModInitializer {
	public static final String MOD_ID = "time-to-sleep";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerItems();
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
			entries.add(ModItems.EMBERGLAZE_ELIXIR);
		});

	}
}