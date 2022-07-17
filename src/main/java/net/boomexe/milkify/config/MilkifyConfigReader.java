package net.boomexe.milkify.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class MilkifyConfigReader {
    public static MilkifyConfig config = new MilkifyConfig();

    public static void registerConfigs() {
        AutoConfig.register(MilkifyConfig.class, JanksonConfigSerializer::new).getConfig();
        config = AutoConfig.getConfigHolder(MilkifyConfig.class).getConfig();
    }
}