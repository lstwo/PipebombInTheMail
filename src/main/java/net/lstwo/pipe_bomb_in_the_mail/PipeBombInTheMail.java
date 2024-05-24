package net.lstwo.pipe_bomb_in_the_mail;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.lstwo.pipe_bomb_in_the_mail.item.BetterInventoryPipeBombItem;
import net.lstwo.pipe_bomb_in_the_mail.item.InventoryPipeBombItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class PipeBombInTheMail implements ModInitializer {
    public static final String MODID = "pipe_bomb_in_the_mail";

    @Override
    public void onInitialize() {
        Item inventoryPipeBomb = new InventoryPipeBombItem();
        Item betterInventoryPipeBomb = new BetterInventoryPipeBombItem();

        Registry.register(Registries.ITEM, new Identifier(MODID, "inventory_pipe_bomb"), inventoryPipeBomb);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.add(inventoryPipeBomb));

        Registry.register(Registries.ITEM, new Identifier(MODID, "better_inventory_pipe_bomb"), betterInventoryPipeBomb);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.add(betterInventoryPipeBomb));
    }
}
