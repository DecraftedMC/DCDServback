package io.github.decraftedmc.dcdservback.item.gui;

import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class EnderBackpackGui extends SimpleGui {
    public EnderBackpackGui(ServerPlayerEntity player) {

        super(ScreenHandlerType.GENERIC_9X3, player, false);
        setTitle(Text.translatable("item.dcdservback.ender_backpack_gui"));
        fillChest();
        open();
    }
    public void fillChest() {
        EnderChestInventory inventory = player.getEnderChestInventory();
        for (int i = 0; i < 27; i++)
            setSlotRedirect(i,new Slot(inventory,i,i,0));
    }
}
