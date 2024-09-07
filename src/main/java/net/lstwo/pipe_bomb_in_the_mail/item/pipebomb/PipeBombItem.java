package net.lstwo.pipe_bomb_in_the_mail.item.pipebomb;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lstwo.pipe_bomb_in_the_mail.PipeBombInTheMail;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class PipeBombItem extends Item {

    protected PipeBombItem(Settings settings) {
        super(settings);
    }

    public PipeBombItem() {
        super(new FabricItemSettings().maxCount(1));
    }

    public void doItemExplosion(ItemStack stack, World world, Entity entity) {
        if (stack.hasNbt()) {
            assert stack.getNbt() != null;
            if (stack.getNbt().getBoolean("activated")) {
                if (!world.isClient) {
                    stack.decrement(1);
                    doExplosion(world, entity);
                }
            }
        }
    }

    public void doExplosion(World world, Entity entity) {
         if (!world.isClient) {
             world.createExplosion(null, entity.getX(), entity.getY(), entity.getZ(), getExplosionPower(),
                     world.getGameRules().getBoolean(PipeBombInTheMail.DO_PROPERTY_DAMAGE) ? World.ExplosionSourceType.MOB : World.ExplosionSourceType.NONE);
         }
    }

    public void doExplosion(World world, float x, float y, float z) {
        if (!world.isClient) {
            world.createExplosion(null, x, y, z, getExplosionPower(),
                    world.getGameRules().getBoolean(PipeBombInTheMail.DO_PROPERTY_DAMAGE) ? World.ExplosionSourceType.MOB : World.ExplosionSourceType.NONE);
        }
    }

    public void doCustomExplosion(World world, float x, float y, float z, float power) {
        if (!world.isClient) {
            world.createExplosion(null, x, y, z, power,
                    world.getGameRules().getBoolean(PipeBombInTheMail.DO_PROPERTY_DAMAGE) ? World.ExplosionSourceType.MOB : World.ExplosionSourceType.NONE);
        }
    }

    public void doMultipliedExplosion(World world, float x, float y, float z, float multiplier) {
        if (!world.isClient) {
            world.createExplosion(null, x, y, z, multiplier,
                    world.getGameRules().getBoolean(PipeBombInTheMail.DO_PROPERTY_DAMAGE) ? World.ExplosionSourceType.MOB : World.ExplosionSourceType.NONE);
        }
    }

    public float getExplosionPower() {
        return 0;
    }
}
