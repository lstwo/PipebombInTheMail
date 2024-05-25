package net.lstwo.pipe_bomb_in_the_mail.item.pipebomb;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class InventoryPipeBombItem extends PipeBombItem {

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.UNCOMMON;
    }

    public InventoryPipeBombItem() {
        super(new FabricItemSettings().maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (player.isSneaking()) {
            itemStack.getOrCreateNbt().putBoolean("activated", true);
            player.sendMessage(Text.of("Pipe Bomb Activated!"), true);
            return TypedActionResult.success(itemStack);
        } else
            return TypedActionResult.fail(itemStack);
    }

    @Override
    public float getExplosionPower() {
        return 4;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        if (!stack.hasNbt()) return false;
        assert stack.getNbt() != null;
        return stack.getNbt().getBoolean("activated");
    }
}