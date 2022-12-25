package atonkish.quartzelv.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import eu.pb4.polymer.core.api.item.PolymerBlockItem;

import atonkish.quartzelv.block.ModBlocks;

public class ModItems {
    public static final Item QUARTZ_ELEVATOR_BLOCK;
    public static final Item SMOOTH_QUARTZ_ELEVATOR;

    public static void init() {
    }

    private static Item register(Block block, Item virtualItem) {
        return ModItems.register(new PolymerBlockItem(block, new Item.Settings(), virtualItem));
    }

    private static Item register(BlockItem item) {
        return ModItems.register(item.getBlock(), (Item) item);
    }

    private static Item register(Identifier id, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
        return Registry.register(Registries.ITEM, id, item);
    }

    static {
        QUARTZ_ELEVATOR_BLOCK = register(ModBlocks.QUARTZ_ELEVATOR_BLOCK, Items.AMETHYST_BLOCK);
        SMOOTH_QUARTZ_ELEVATOR = register(ModBlocks.SMOOTH_QUARTZ_ELEVATOR, Items.BUDDING_AMETHYST);
    }
}
