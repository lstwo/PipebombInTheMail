package net.lstwo.pipe_bomb_in_the_mail.mixin;

import net.lstwo.pipe_bomb_in_the_mail.item.pipebomb.InstantInventoryPipeBombItem;
import net.lstwo.pipe_bomb_in_the_mail.item.pipebomb.InventoryPipeBombItem;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    @Shadow private int pickupDelay;

    @Inject(at = @At("HEAD"), method = "onPlayerCollision(Lnet/minecraft/entity/player/PlayerEntity;)V")
    public void onPlayerCollision(PlayerEntity player, CallbackInfo ci) {
        ItemEntity itemEntity = (ItemEntity) (Object) this;
        ItemStack stack = itemEntity.getStack();

        if (stack.getItem() instanceof InventoryPipeBombItem pipeBomb) {
            if (!itemEntity.getWorld().isClient) {

                if(stack.getItem() instanceof InstantInventoryPipeBombItem) {
                    if(stack.getNbt() != null && stack.getNbt().getInt("explosionDelay") > 0) stack.getNbt().putInt("explosionDelay", stack.getNbt().getInt("explosionDelay") - 1);
                    else stack.getOrCreateNbt().putInt("explosionDelay", 10);
                }
                if (this.pickupDelay == 0 || (stack.getItem() instanceof InstantInventoryPipeBombItem && stack.getNbt().getInt("explosionDelay") == 0)) {
                    pipeBomb.doItemExplosion(stack, itemEntity.getWorld(), player);
                }
            }
        }
    }
}