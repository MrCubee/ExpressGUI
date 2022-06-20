package fr.mrcubee.menu.object;

import fr.mrcubee.menu.Menu;
import fr.mrcubee.menu.Menus;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;

public abstract class ObjectMenu<T> extends Menu {

    protected final Class<T> objectClass;
    protected final T[] objects;

    protected ObjectMenu(final Menus manager, final Class<T> objectClass, String title, int slots) {
        super(manager, title, slots);
        this.objectClass = objectClass;
        this.objects = (T[]) Array.newInstance(objectClass, slots);
    }

    public void setObject(final int slot, final T object) {
        if (slot < 0 || slot >= this.items.length)
            return;
        this.objects[slot] = object;
    }

    public void setObjectButton(final int slot, final T object, final ObjectButton<T> button) {
        if (slot < 0 || slot >= this.items.length)
            return;
        this.objects[slot] = object;
        this.buttons[slot] = button;
    }

    public void setObjectItemButton(final int slot, final ItemStack itemStack, final T object, final ObjectButton<T> button) {
        if (slot < 0 || slot >= this.items.length)
            return;
        this.items[slot] = itemStack;
        this.objects[slot] = object;
        this.buttons[slot] = button;
        resetInventory();
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.items.length; i++) {
            this.items[i] = null;
            this.buttons[i] = null;
            this.objects[i] = null;
        }
    }

    public Class<T> getObjectClass() {
        return this.objectClass;
    }
}
