package io.github.decraftedmc.dcdservback;

import io.github.decraftedmc.dcdservback.item.ItemRegistry;
import io.github.decraftedmc.dcdservback.polyinghardrn.PolymerModels;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.collection.DefaultedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;

public class DCDServback implements ModInitializer {
	public static final String MOD_ID = "dcdservback";
	public static final Logger LOGGER = LoggerFactory.getLogger("Decrafted Server Backpacks");

	@Override
	public void onInitialize() {
		LOGGER.info("Setting up Polymer Assets");
		PolymerModels.setup();
		LOGGER.info("Preparing Server-sided Backpacks");
		ItemRegistry.registerModItems();
		LOGGER.info("You can feel your storage problems fading away. That is, if you're willing to grind!");


		ServerLifecycleEvents.SERVER_STARTING.register((s) -> {
			LOGGER.info("Loading Public Backpack inventory");
			server = s;
			loadGlobal();
		});
		ServerLifecycleEvents.SERVER_STOPPING.register((s) -> {
			LOGGER.info("Saving Public Backpack inventory");
			saveGlobal();
		});
	}
	public static void loadGlobal() {
		try {
			Path savePath = server.getSavePath(WorldSavePath.ROOT);
			InputStream in = new FileInputStream(savePath.toString() + "/backpacks_global_inv.nbt");
			NbtCompound nbt = NbtIo.readCompressed(in);
			DefaultedList<ItemStack> inv = DefaultedList.ofSize(27,ItemStack.EMPTY);
			Inventories.readNbt(nbt,inv);
			globalInventory = inv.toArray(ItemStack[]::new);
		} catch (Exception ignored) {
			LOGGER.error("Error occured while loading Public Backpack Inventory");
			DefaultedList<ItemStack> inv = DefaultedList.ofSize(27,ItemStack.EMPTY);
			globalInventory = inv.toArray(ItemStack[]::new);
		}
	}
	public static void saveGlobal() {
		try {
			Path savePath = server.getSavePath(WorldSavePath.ROOT);
			OutputStream out = new FileOutputStream(savePath.toString() + "/backpacks_global_inv.nbt");
			DefaultedList<ItemStack> inv = DefaultedList.ofSize(27,ItemStack.EMPTY);
			for (int i = 0; i < 27; i++) {
				ItemStack stack = globalInventory[i];
				if (stack == null) stack = ItemStack.EMPTY;
				inv.set(i,stack);
			}
			NbtCompound invNbt = Inventories.writeNbt(new NbtCompound(), inv,true);
			NbtIo.writeCompressed(invNbt,out);
		} catch (Exception ignored) {
			LOGGER.error("Error occured while saving Public Backpack Inventory");
		}
	}

    public static Inventory getInventory() {
        return new SimpleInventory(globalInventory);
    }

	public static MinecraftServer server;
	public static ItemStack[] globalInventory = new ItemStack[27];
}
