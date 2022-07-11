package io.github.decraftedmc.dcdservback.polyinghardrn;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

import javax.annotation.Nullable;

// Turned google upside down for this one since I'm too dense to understand a simple documentation
// Well at least now I know how to do this, somewhat.
public class SimplePolymerAutoItem extends Item implements PolymerAutoItem {
    private final Item polymerItem;

    public SimplePolymerAutoItem(Settings settings, Item polymerItem) {
        super(settings);
        this.polymerItem = polymerItem;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerItem;
    }
}
