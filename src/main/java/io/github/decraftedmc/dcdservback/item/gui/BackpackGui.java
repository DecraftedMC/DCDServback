package io.github.decraftedmc.dcdservback.item.gui;

import eu.pb4.sgui.api.gui.SimpleGui;
import io.github.decraftedmc.dcdservback.util.BackpackSlot;
import io.github.decraftedmc.dcdservback.util.NopSlot;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;

public class BackpackGui extends SimpleGui {
    public final int slots;
    public ItemStack stack;
    public Inventory inventory;

    public BackpackGui(ServerPlayerEntity player, int slots, ItemStack stack) {
        super(
                getHandler(slots),
                player, false
        );
        open();
        this.slots = slots;
        this.stack = stack;
        setTitle(Text.translatable(stack.getTranslationKey()));
        NbtCompound nbt = stack.getOrCreateNbt();
        DefaultedList<ItemStack> list = DefaultedList.ofSize(slots+1, ItemStack.EMPTY);
        list.set(slots, stack);
        Inventories.readNbt(nbt, list);
        inventory = new SimpleInventory(list.toArray(ItemStack[]::new));
        fillChest();
        for (int i = 0; i < 9; i++) {
            ItemStack invStack = player.getInventory().getStack(i);
            if (invStack.equals(stack)) {
                screenHandler.setSlot(i+27+slots, new NopSlot(inventory, i, i, i));
            }
        }
    }

    public static ScreenHandlerType<?> getHandler(int slots) {
        return switch (slots / 9) {
            case 1 -> ScreenHandlerType.GENERIC_9X1;
            case 2 -> ScreenHandlerType.GENERIC_9X2;
            case 3 -> ScreenHandlerType.GENERIC_9X3;
            case 4 -> ScreenHandlerType.GENERIC_9X4;
            case 5 -> ScreenHandlerType.GENERIC_9X5;
            default -> null;
        };
    }

    public void fillChest() {
        for (int i = 0; i < slots; i++) {
            setSlotRedirect(i, new BackpackSlot(inventory, i, i, i));
        }
    }

        public void saveMain() {
            DefaultedList<ItemStack> inv = DefaultedList.ofSize(slots, ItemStack.EMPTY);
            for (int w = 0; w < slots; w++) {
                ItemStack stack = getSlotRedirect(w).getStack();
                inv.set(w, stack);
            }
            NbtCompound root = stack.getNbt();
            NbtCompound invNbt = Inventories.writeNbt(root, inv);
            stack.setNbt(invNbt);
        }

        @Override
        public void onClose() {
            saveMain();
        }
    }
