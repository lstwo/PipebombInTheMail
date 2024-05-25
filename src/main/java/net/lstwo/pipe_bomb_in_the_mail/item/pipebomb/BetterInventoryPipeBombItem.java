package net.lstwo.pipe_bomb_in_the_mail.item.pipebomb;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class BetterInventoryPipeBombItem extends InventoryPipeBombItem {

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }

    @Override
    public float getExplosionPower() {
        return 12;
    }
}
