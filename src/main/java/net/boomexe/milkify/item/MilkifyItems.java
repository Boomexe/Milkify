package net.boomexe.milkify.item;

import net.boomexe.milkify.Milkify;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MilkifyItems {
    public static final Item MILK_BOTTLE = registerItem("milk_bottle",
            new MilkBottleItem(new FabricItemSettings().group(MilkifyItemGroup.MILKIFY).maxCount(16)));

    public static final Item THROWABLE_MILK_BOTTLE = registerItem("throwable_milk_bottle",
            new MilkBottleThrowableItem(new FabricItemSettings().group(MilkifyItemGroup.MILKIFY).maxCount(16)));

    public static final Item ANTIDOTE_MILK_BOTTLE = registerItem("antidote_milk_bottle",
            new AntidoteMilkBottleItem(new FabricItemSettings().group(MilkifyItemGroup.MILKIFY).maxCount(16)));

    public static final Item THROWABLE_ANTIDOTE_MILK_BOTTLE = registerItem("throwable_antidote_milk_bottle",
            new AntidoteMilkBottleThrowableItem(new FabricItemSettings().group(MilkifyItemGroup.MILKIFY).maxCount(16)));

    public static final Item DEBUFF_MILK_BOTTLE = registerItem("debuff_milk_bottle",
            new DebuffMilkBottleItem(new FabricItemSettings().group(MilkifyItemGroup.MILKIFY).maxCount(16)));

    public static final Item THROWABLE_DEBUFF_MILK_BOTTLE = registerItem("throwable_debuff_milk_bottle",
            new DebuffMilkBottleThrowableItem(new FabricItemSettings().group(MilkifyItemGroup.MILKIFY).maxCount(16)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Milkify.MOD_ID, name), item);
    }

    public static void registerMilkifyItems() {
        Milkify.LOGGER.info("Registering Mod Items for " + Milkify.MOD_ID);
    }
}
