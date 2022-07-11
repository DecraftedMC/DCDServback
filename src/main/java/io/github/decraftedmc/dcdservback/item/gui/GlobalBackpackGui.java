package io.github.decraftedmc.dcdservback.item.gui;

import eu.pb4.sgui.api.gui.SimpleGui;
import io.github.decraftedmc.dcdservback.DCDServback;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class GlobalBackpackGui extends SimpleGui {
    public GlobalBackpackGui(ServerPlayerEntity player) {
        super(ScreenHandlerType.GENERIC_9X3, player, false);
        setTitle(Text.translatable("item.dcdservback.public_backpack_gui"));
        fillChest();
        open();
    }

    public void fillChest() {
        Inventory inventory = DCDServback.getInventory();
        for (int i = 0; i < 27; i++)
            setSlotRedirect(i, new Slot(inventory, i, i, 0));
    }
}