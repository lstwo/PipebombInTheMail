package net.lstwo.pipe_bomb_in_the_mail.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class InventoryPipeBombItem extends Item {

    public InventoryPipeBombItem() {
        super(new Item.Settings().group(ItemGroup.MISC));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (player.isSneaking()) {
            itemStack.getOrCreateNbt().putBoolean("activated", true);
            player.sendMessage(Text.of("Pipe Bomb Activated!"), true);
        }
        return TypedActionResult.success(itemStack);
    }

    public void doExplosion(ItemStack stack, World world, Entity entity) {
        if (stack.hasNbt()) {
            assert stack.getNbt() != null;
            if (stack.getNbt().getBoolean("activated")) {
                if (!world.isClient) {
                    stack.decrement(1);
                    world.createExplosion(null, entity.getX(), entity.getY(), entity.getZ(), 4.0F, Explosion.DestructionType.DESTROY);
                }
            }
        }
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        if (!stack.hasNbt()) return false;
        assert stack.getNbt() != null;
        return stack.getNbt().getBoolean("activated");
    }
}