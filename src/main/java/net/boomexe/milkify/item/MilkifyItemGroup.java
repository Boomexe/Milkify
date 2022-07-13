package net.boomexe.milkify.item;

import net.boomexe.milkify.Milkify;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MilkifyItemGroup {
    public static final ItemGroup MILKIFY = FabricItemGroupBuilder.build(new Identifier(Milkify.MOD_ID, "milkify"),
            () -> new ItemStack(MilkifyItems.MILK_BOTTLE));
}
