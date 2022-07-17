package net.boomexe.milkify;

import net.boomexe.milkify.config.MilkifyConfigReader;
import net.boomexe.milkify.item.MilkifyItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Milkify implements ModInitializer {
	public static final String MOD_ID = "milkify";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MilkifyConfigReader.registerConfigs();
		MilkifyItems.registerItems();

		LOGGER.info("Milkify Mod Initialized");
	}
}
