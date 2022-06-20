package fr.mrcubee.menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class ChestMenu extends Menu {

    public ChestMenu(final Menus manager, final String title, final int slots) {
        super(manager, title, slots);
    }

    @Override
    protected Inventory buildInventory() {
        final Inventory inventory = Bukkit.createInventory(null, getSize(), this.title);

        inventory.setContents(this.items);
        return inventory;
    }

}
