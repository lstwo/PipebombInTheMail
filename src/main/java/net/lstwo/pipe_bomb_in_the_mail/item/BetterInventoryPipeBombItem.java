package net.lstwo.pipe_bomb_in_the_mail.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class BetterInventoryPipeBombItem extends InventoryPipeBombItem {

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }

    @Override
    public void doExplosion(ItemStack stack, World world, Entity entity) {
        if (stack.hasNbt()) {
            assert stack.getNbt() != null;
            if (stack.getNbt().getBoolean("activated")) {
                if (!world.isClient) {
                    stack.decrement(1);
                    world.createExplosion(null, entity.getX(), entity.getY(), entity.getZ(), 12.0F, World.ExplosionSourceType.MOB);
                }
            }
        }
    }
}
