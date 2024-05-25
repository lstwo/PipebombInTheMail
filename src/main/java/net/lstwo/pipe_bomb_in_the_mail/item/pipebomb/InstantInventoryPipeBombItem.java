package net.lstwo.pipe_bomb_in_the_mail.item.pipebomb;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class InstantInventoryPipeBombItem extends InventoryPipeBombItem {

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}