package fr.mrcubee.menu;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class HopperMenu extends Menu {

    public HopperMenu(final Menus manager, final String title) {
        super(manager, title,5);
    }

    @Override
    protected Inventory buildInventory() {
        final Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER);

        inventory.setContents(this.items);
        return inventory;
    }
}
