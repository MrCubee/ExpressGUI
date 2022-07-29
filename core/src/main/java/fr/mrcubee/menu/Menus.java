package fr.mrcubee.menu;

import fr.mrcubee.menu.object.ObjectChestMenu;
import fr.mrcubee.menu.object.ObjectHopperMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

public class Menus implements Listener {

    protected final Set<Menu> menus;

    public Menus() {
        this.menus = new HashSet<Menu>();
    }

    protected <T> T createMenuInstance(final Class<T> menuClass, final Object... parameters) {
        final Object[] completeParameters;
        final Constructor<T> constructor;
        T menu = null;

        if (menuClass == null)
            return null;
        if (parameters != null) {
            completeParameters = new Object[parameters.length + 1];
            System.arraycopy(parameters, 0, completeParameters, 1, parameters.length);
        } else
            completeParameters = new Object[1];
        completeParameters[0] = this;
        constructor = Reflections.getConstructor(menuClass, this, completeParameters);
        if (constructor == null)
            return null;
        try {
            menu = constructor.newInstance(parameters);
        } catch (Exception ignored) {}
        return menu;
    }

    public <T extends Menu> T createMenu(final Class<T> menuClass, final Object... parameters) {
        final T menu = createMenuInstance(menuClass, parameters);

        if (menu == null)
            return null;
        this.menus.add(menu);
        return menu;
    }

    public ChestMenu createChestMenu(final String title, final int rows) {
        return createMenu(ChestMenu.class, title, rows * 9);
    }

    public <T> ObjectChestMenu<T> createObjectChestMenu(final Class<T> objectClass, final String title, final int rows) {
        return createMenu(ObjectChestMenu.class, objectClass, title, rows);
    }

    public HopperMenu createHopperMenu(final String title) {
        return createMenu(HopperMenu.class, title);
    }

    public <T> ObjectHopperMenu<T> createObjectHopperMenu(final Class<T> objectClass, final String title) {
        return createMenu(ObjectHopperMenu.class, objectClass, title);
    }

    protected Menu getMenuFromInventory(final Inventory inventory) {
        if (inventory == null)
            return null;
        for (Menu menu : this.menus) {
            if (menu.isSame(inventory))
                return menu;
        }
        return null;
    }

    @EventHandler
    public void onClickEvent(final InventoryClickEvent event) {
        final Menu menu = getMenuFromInventory(event.getClickedInventory());

        if (menu != null) {
            event.setCancelled(true);
            menu.click(event);
        }
    }

    @EventHandler
    public void onCloseEvent(final InventoryCloseEvent event) {
        final Menu menu = getMenuFromInventory(event.getInventory());

        if (menu != null)
            menu.close(event);
    }

}
