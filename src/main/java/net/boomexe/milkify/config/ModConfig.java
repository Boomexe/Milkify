package net.boomexe.milkify.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "milkify")
//@Config.Gui.Background("minecraft:textures/block/.png")
public class ModConfig implements ConfigData {
    @ConfigEntry.BoundedDiscrete(max = 64, min = 1)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("Stack size for all drinkable types of milk bottles")
    public int bottle_stack_size = 1;

    @ConfigEntry.BoundedDiscrete(max = 64, min = 1)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("Stack size for all throwable types of milk bottles")
    public int throwable_bottle_stack_size = 1;

    @Comment("Effect range for all throwable types of milk bottles")
    public double throwable_bottle_effect_range = 3;
}
