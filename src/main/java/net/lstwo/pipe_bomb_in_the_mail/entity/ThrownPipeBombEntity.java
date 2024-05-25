package net.lstwo.pipe_bomb_in_the_mail.entity;

import net.lstwo.pipe_bomb_in_the_mail.PipeBombInTheMail;
import net.lstwo.pipe_bomb_in_the_mail.item.pipebomb.PipeBombItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class ThrownPipeBombEntity extends ThrownItemEntity {
    public ThrownPipeBombEntity(EntityType<? extends SnowballEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrownPipeBombEntity(World world, LivingEntity owner) {
        super(EntityType.SNOWBALL, owner, world);
    }

    public ThrownPipeBombEntity(World world, double x, double y, double z) {
        super(EntityType.SNOWBALL, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return PipeBombInTheMail.pipeBomb;
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return (itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if(getStack().getItem() instanceof PipeBombItem pipeBombItem)
            pipeBombItem.doItemExplosion(getStack(), getWorld(), entityHitResult.getEntity());
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            if(getStack().getItem() instanceof PipeBombItem pipeBombItem)
                pipeBombItem.doItemExplosion(getStack(), getWorld(), this);
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.discard();
        }
    }
}
