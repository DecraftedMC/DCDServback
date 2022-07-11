package io.github.decraftedmc.dcdservback.item;

import io.github.decraftedmc.dcdservback.DCDServback;
import io.github.decraftedmc.dcdservback.item.backpacks.EnderBackpackItem;
import io.github.decraftedmc.dcdservback.item.backpacks.GlobalBackpackItem;
import io.github.decraftedmc.dcdservback.polyinghardrn.PolymerModels;
import io.github.decraftedmc.dcdservback.item.backpacks.BackpackItem;
import io.github.decraftedmc.dcdservback.polyinghardrn.SimplePolymerAutoItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    // Backpacks
    public static final Item COOKIE_BACKPACK = registerItem("cookie_backpack",
            new BackpackItem(new FabricItemSettings().rarity(Rarity.COMMON), 9));
    public static final Item TRUFFLE_BACKPACK = registerItem("truffle_backpack",
            new BackpackItem(new FabricItemSettings().rarity(Rarity.COMMON), 18));
    public static final Item PANCAKE_BACKPACK = registerItem("pancake_backpack",
            new BackpackItem(new FabricItemSettings().rarity(Rarity.UNCOMMON), 27));
    public static final Item PIEROGI_BACKPACK = registerItem("pierogi_backpack",
            new BackpackItem(new FabricItemSettings().rarity(Rarity.RARE), 36));
    public static final Item STRAWBERRY_PIEROGI_BACKPACK = registerItem("strawberry_pierogi_backpack",
            new BackpackItem(new FabricItemSettings().fireproof().rarity(Rarity.EPIC), 45));
    // Special Backpacks
    public static final Item PUBLIC_BACKPACK = registerItem("public_backpack",
            new GlobalBackpackItem(new FabricItemSettings().rarity(Rarity.RARE)));
    public static final Item ENDER_BACKPACK = registerItem("ender_backpack",
            new EnderBackpackItem(new FabricItemSettings().rarity(Rarity.RARE)));
    // Upgrades
    public static final Item CHOCOLATE_UPGRADE = registerItem("chocolate_upgrade",
            new SimplePolymerAutoItem(new FabricItemSettings().rarity(Rarity.COMMON), Items.BLACK_STAINED_GLASS_PANE));
    public static final Item DOUGH_UPGRADE = registerItem("dough_upgrade",
            new SimplePolymerAutoItem(new FabricItemSettings().rarity(Rarity.UNCOMMON), Items.BLACK_STAINED_GLASS_PANE));
    public static final Item COTTAGE_CHEESE_UPGRADE = registerItem("cottage_cheese_upgrade",
            new SimplePolymerAutoItem(new FabricItemSettings().rarity(Rarity.RARE), Items.BLACK_STAINED_GLASS_PANE));
    public static final Item SOUR_CREAM_UPGRADE = registerItem("sour_cream_upgrade",
            new SimplePolymerAutoItem(new FabricItemSettings().fireproof().rarity(Rarity.EPIC), Items.BLACK_STAINED_GLASS_PANE));
    // Converts Pancake into End Chest, or Dough Upgrade to Void Dough
    public static final Item VID_DOUGH = registerItem("vid_dough",
            new SimplePolymerAutoItem(new FabricItemSettings().rarity(Rarity.RARE), Items.BLACK_STAINED_GLASS_PANE));


    public static Item registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(DCDServback.MOD_ID, name), item);

        PolymerModels.requestModel(new Identifier(DCDServback.MOD_ID, "item/" + name), item);
        return item;
    }

    public static void registerModItems() {
    }
}
