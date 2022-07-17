package net.boomexe.milkify.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "milkify")
//@Config.Gui.Background("minecraft:textures/block/.png")
public class MilkifyConfig implements ConfigData {
    @ConfigEntry.BoundedDiscrete(max = 64, min = 1)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("Stack size for all drinkable types of milk bottles")
    public int bottle_stack_size = 16;

    @ConfigEntry.BoundedDiscrete(max = 64, min = 1)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("Stack size for all throwable types of milk bottles")
    public int throwable_bottle_stack_size = 16;

    @Comment("Effect range for all throwable types of milk bottles")
    public double throwable_bottle_effect_range = 3;

//    @ConfigEntry.Gui.CollapsibleObject
//    public MilkBottle milk_bottle = new MilkBottle();
//    @ConfigEntry.Gui.CollapsibleObject
//    public AntidoteMilkBottle antidote_milk_bottle = new AntidoteMilkBottle();
//    @ConfigEntry.Gui.CollapsibleObject
//    public DebuffMilkBottle debuff_milk_bottle = new DebuffMilkBottle();
//
//    public static class MilkBottle {
//        @ConfigEntry.Gui.RequiresRestart
//        @Comment("Stack size of Milk Bottles")
//        @ConfigEntry.BoundedDiscrete(max = 64, min = 1)
//        public int milk_bottle_stack_size = 16;
//        @ConfigEntry.Gui.RequiresRestart
//        @Comment("Stack size of Throwable Milk Bottles")
//        @ConfigEntry.BoundedDiscrete(max = 64, min = 1)
//        public int throwable_milk_bottle_stack_size = 16;
//    }
//
//    public static class AntidoteMilkBottle {
//        @ConfigEntry.Gui.RequiresRestart
//        @Comment("Stack size of Antidote Milk Bottles")
//        @ConfigEntry.BoundedDiscrete(max = 64, min = 1)
//        public int antidote_milk_bottle_stack_size = 16;
//        @ConfigEntry.Gui.RequiresRestart
//        @Comment("Stack size of Throwable Antidote Milk Bottles")
//        @ConfigEntry.BoundedDiscrete(max = 64, min = 1)
//        public int throwable_antidote_bottle_stack_size = 16;
//    }
//
//    public static class DebuffMilkBottle {
//        @ConfigEntry.Gui.RequiresRestart
//        @Comment("Stack size of Debuff Milk Bottles")
//        @ConfigEntry.BoundedDiscrete(max = 64, min = 1)
//        public int debuff_milk_bottle_stack_size = 16;
//        @ConfigEntry.Gui.RequiresRestart
//        @Comment("Stack size of Throwable Debuff Milk Bottles")
//        @ConfigEntry.BoundedDiscrete(max = 64, min = 1)
//        public int debuff_bottle_stack_size = 16;
//    }
}
