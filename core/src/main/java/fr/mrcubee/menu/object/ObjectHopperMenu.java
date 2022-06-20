package fr.mrcubee.menu.object;

import fr.mrcubee.menu.Menus;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class ObjectHopperMenu<T> extends ObjectMenu<T> {

    protected ObjectHopperMenu(final Menus manager, Class<T> objectClass, String title, int slots) {
        super(manager, objectClass, title, slots);
    }

    @Override
    protected Inventory buildInventory() {
        final Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER);

        inventory.setContents(this.items);
        return inventory;
    }

}
