package net.lstwo.pipe_bomb_in_the_mail.mixin;

import net.lstwo.pipe_bomb_in_the_mail.item.InventoryPipeBombItem;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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

        if(!itemEntity.world.isClient) {
            if(this.pickupDelay == 0 && (itemEntity.getOwner() == null || itemEntity.getOwner().equals(player.getUuid())))
                if (stack.getItem() instanceof InventoryPipeBombItem pipebombItem)
                    pipebombItem.doExplosion(stack, itemEntity.getWorld(), player);
        }
    }
}