package net.lstwo.pipe_bomb_in_the_mail.item.pipebomb;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lstwo.pipe_bomb_in_the_mail.entity.ThrownPipeBombEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ThrowablePipeBomb extends Item {
    protected ThrowablePipeBomb(Settings settings) {
        super(settings);
    }

    public ThrowablePipeBomb() {
        super(new FabricItemSettings().maxCount(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            ThrownPipeBombEntity thrownPipeBombEntity = new ThrownPipeBombEntity(world, user);
            thrownPipeBombEntity.setItem(itemStack);
            thrownPipeBombEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(thrownPipeBombEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
