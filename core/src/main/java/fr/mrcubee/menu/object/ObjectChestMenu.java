package fr.mrcubee.menu.object;

import fr.mrcubee.menu.Menus;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class ObjectChestMenu<T> extends ObjectMenu<T> {

    protected ObjectChestMenu(final Menus manager, Class<T> objectClass, String title, int slots) {
        super(manager, objectClass, title, slots);
    }

    @Override
    protected Inventory buildInventory() {
        final Inventory inventory = Bukkit.createInventory(null, getSize(), this.title);

        inventory.setContents(this.items);
        return inventory;
    }

}
