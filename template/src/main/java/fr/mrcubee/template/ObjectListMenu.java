package fr.mrcubee.template;

import fr.mrcubee.menu.CloseButton;
import fr.mrcubee.menu.Menus;
import fr.mrcubee.menu.object.ObjectButton;
import fr.mrcubee.menu.object.ObjectChestMenu;
import fr.mrcubee.menu.object.ObjectMenu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public abstract class ObjectListMenu<T> extends ObjectChestMenu<T> {

    private int page;
    protected final int displaySlots;
    protected final Supplier<Collection<? extends T>> collectionSupplier;
    protected final ObjectButton<T> nextButton;
    protected final ObjectButton<T> prevButton;

    protected ObjectListMenu(final Menus manager, final Class<T> objectClass, final String title, final int slots, final int displaySlots, final Supplier<Collection<? extends T>> collectionSupplier) {
        super(manager, objectClass, title, slots);
        this.page = 0;
        this.displaySlots = displaySlots;
        this.collectionSupplier = collectionSupplier;
        this.nextButton = this::nextButton;
        this.prevButton = this::prevButton;
    }

    protected int getPageCount(final Collection<? extends T> collection) {
        if (collection == null || collection.isEmpty())
            return 0;
        return (collection.size() / this.displaySlots) + 1;
    }

    public void setPage(final Collection<? extends T> collection, int page) {
        final int pageCount = getPageCount(collection);

        if (pageCount < 0 || page < 0) {
            this.page = 0;
            return;
        }
        this.page = Math.min(pageCount, page);
    }

    public int getPage(final Collection<? extends T> collection) {
        final int pageCount = getPageCount(collection);

        this.page = Math.max(this.page, pageCount);
        return this.page;
    }

    /*
     * TODO: Reduce this method.
     */
    @Override
    protected Inventory buildInventory() {
        final Inventory inventory = super.buildInventory();
        final Collection<? extends T> objectCollection = this.collectionSupplier.get();
        final List<? extends T> objectList;
        final int pageCount = getPageCount(objectCollection);
        final int page = getPage(objectCollection);
        final List<? extends T> viewObjectList;
        final int startIndex;
        int index;

        if (pageCount < 1)
            return inventory;
        objectList = new ArrayList<>(objectCollection);
        startIndex = this.displaySlots * page;
        viewObjectList = objectList.subList(startIndex, startIndex + this.displaySlots);
        index = 0;
        for (final T object : viewObjectList)
            setObjectItemButton(index++, createItemFromObject(object), object, createButtonFromObject(object));
        return inventory;
    }

    protected abstract ItemStack createItemFromObject(final T object);
    protected abstract ObjectButton<T> createButtonFromObject(final T object);

    private void nextButton(final Player player, final ObjectMenu<T> menu, final ItemStack itemStack, int slot, T object) {
        final CloseButton closeButton = getCloseButton();

        setCloseButton(null);
        player.closeInventory();
        setPage(this.collectionSupplier.get(), ++this.page);
        setCloseButton(closeButton);
        resetInventory();
        player.openInventory(menu.getInventory());
    }

    private void prevButton(final Player player, final ObjectMenu<T> menu, final ItemStack itemStack, int slot, T object) {
        final CloseButton closeButton = getCloseButton();

        setCloseButton(null);
        player.closeInventory();
        setPage(this.collectionSupplier.get(), --this.page);
        setCloseButton(closeButton);
        resetInventory();
        player.openInventory(menu.getInventory());
    }

}
