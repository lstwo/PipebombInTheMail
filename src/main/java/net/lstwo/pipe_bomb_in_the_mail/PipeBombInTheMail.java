package net.lstwo.pipe_bomb_in_the_mail;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.lstwo.pipe_bomb_in_the_mail.entity.ThrownPipeBombEntity;
import net.lstwo.pipe_bomb_in_the_mail.item.pipebomb.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class PipeBombInTheMail implements ModInitializer {
    public static final String MODID = "pipe_bomb_in_the_mail";

    public static final Item pipeBomb = new PipeBombItem();
    public static final Item inventoryPipeBomb = new InventoryPipeBombItem();
    public static final Item betterInventoryPipeBomb = new BetterInventoryPipeBombItem();
    public static final Item instantInventoryPipeBomb = new InstantInventoryPipeBombItem();

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier(MODID, "pipe_bomb"), pipeBomb);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> content.add(pipeBomb));

        Registry.register(Registries.ITEM, new Identifier(MODID, "inventory_pipe_bomb"), inventoryPipeBomb);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.add(inventoryPipeBomb));

        Registry.register(Registries.ITEM, new Identifier(MODID, "better_inventory_pipe_bomb"), betterInventoryPipeBomb);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.add(betterInventoryPipeBomb));

        Registry.register(Registries.ITEM, new Identifier(MODID, "instant_inventory_pipe_bomb"), instantInventoryPipeBomb);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.add(instantInventoryPipeBomb));
    }
}
