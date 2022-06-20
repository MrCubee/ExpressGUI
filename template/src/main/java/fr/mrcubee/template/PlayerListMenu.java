package fr.mrcubee.template;

import fr.mrcubee.menu.Menus;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Collection;
import java.util.function.Supplier;

public abstract class PlayerListMenu extends ObjectListMenu<Player> {

    protected PlayerListMenu(Menus manager, final String title, int slots, int displaySlots, final Supplier<Collection<? extends Player>> collectionSupplier) {
        super(manager, Player.class, title, slots, displaySlots, collectionSupplier);
    }

    @Override
    protected ItemStack createItemFromObject(Player object) {
        final ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        final SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        skullMeta.setOwner(object.getName());
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }
}
