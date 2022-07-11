package io.github.decraftedmc.dcdservback.polyinghardrn;

import eu.pb4.polymer.api.item.PolymerItem;
import eu.pb4.polymer.api.resourcepack.PolymerModelData;
import eu.pb4.polymer.api.resourcepack.PolymerRPUtils;
import io.github.decraftedmc.dcdservback.DCDServback;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.IdentityHashMap;
import java.util.Map;

// I was always wondering how you do this and well looks like I missed a single step before lol
public class PolymerModels {
    public static final Map<Item, PolymerModelData> MODELS = new IdentityHashMap<>();

    public static void setup() {
        PolymerRPUtils.addAssetSource(DCDServback.MOD_ID);

    }

    public static void requestModel(Identifier identifier, Item item) {
        MODELS.put(item, PolymerRPUtils.requestModel(((PolymerItem) item).getPolymerItem(item.getDefaultStack(), null), identifier));
    }
}
