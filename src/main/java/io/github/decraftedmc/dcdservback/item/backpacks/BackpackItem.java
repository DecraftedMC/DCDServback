package io.github.decraftedmc.dcdservback.item.backpacks;

import io.github.decraftedmc.dcdservback.item.gui.BackpackGui;
import io.github.decraftedmc.dcdservback.polyinghardrn.PolymerAutoItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

// See this, we implement the interface that gives us custommodeldata boogaloo or whatever.
// ...and I was wondering what I was missing. xd
public class BackpackItem extends Item implements PolymerAutoItem {
    int slots;
    String name;

    public BackpackItem(Settings settings, int slots) {
        super(settings.maxCount(1));
        this.slots = slots;
        if (slots == 9)
            name = "cookie";
        if (slots == 18)
            name = "truffle";
        if (slots == 27)
            name = "pancake";
        if (slots == 36)
            name = "pierogi";
        if (slots == 45)
            name = "strawberry_pierogi";
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable("item.decraftedmc.backpack.type." + name);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        var cast = user.raycast(5,0,false);
        if (cast.getType() == HitResult.Type.BLOCK) return TypedActionResult.pass(stack);
        if (!(user instanceof ServerPlayerEntity player)) return TypedActionResult.pass(stack);
        if (player.isSneaking()) return TypedActionResult.pass(stack);
        new BackpackGui(player,slots,stack);
        return TypedActionResult.success(stack);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!(context.getPlayer() instanceof ServerPlayerEntity player)) return ActionResult.PASS;
        if (player.isSneaking()) return ActionResult.PASS;
        new BackpackGui(player,slots,context.getStack());
        return ActionResult.PASS;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.TOTEM_OF_UNDYING;
    }

    @Override
    public ItemStack getPolymerItemStack(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        ItemStack stack = PolymerAutoItem.super.getPolymerItemStack(itemStack, player);
        NbtCompound nbt = stack.getOrCreateNbt().copy();
        nbt.putInt("backpack",slots/9);
        stack.setNbt(nbt);
        return stack;
    }
}
