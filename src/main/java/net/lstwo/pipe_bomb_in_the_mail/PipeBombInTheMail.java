package net.lstwo.pipe_bomb_in_the_mail;

import net.fabricmc.api.ModInitializer;
import net.lstwo.pipe_bomb_in_the_mail.item.InventoryPipeBombItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PipeBombInTheMail implements ModInitializer {
    public static final String MODID = "pipe_bomb_in_the_mail";

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(MODID, "pipe_bomb"), new InventoryPipeBombItem());

    }
}
