package fr.mrcubee.menu;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class Menu {

    private final Menus manager;
    protected String title;
    protected final Button[] buttons;
    protected final ItemStack[] items;
    protected CloseButton closeButton;
    private Inventory inventory;

    protected Menu(final Menus manager, final String title, final int slots) {
        this.manager = manager;
        this.title = title;
        this.buttons = new Button[slots];
        this.items = new ItemStack[slots];
        this.closeButton = null;
        this.inventory = null;
    }

    protected abstract Inventory buildInventory();

    public boolean isSame(final Inventory inventory) {
        if (this.inventory == null || inventory == null)
            return false;
        return this.inventory.equals(inventory);
    }

    public void resetInventory() {
        this.inventory = null;
    }

    public void setCloseButton(CloseButton closeButton) {
        this.closeButton = closeButton;
    }

    public CloseButton getCloseButton() {
        return this.closeButton;
    }

    public void setItem(final int slot, final ItemStack itemStack) {
        if (slot < 0 || slot >= this.items.length)
            return;
        this.items[slot] = itemStack;
        resetInventory();
    }

    public void setButton(final int slot, final Button button) {
        if (slot < 0 || slot >= this.items.length)
            return;
        this.buttons[slot] = button;
    }

    public void setItemButton(final int slot, final ItemStack itemStack, final Button button) {
        if (slot < 0 || slot >= this.items.length)
            return;
        this.items[slot] = itemStack;
        this.buttons[slot] = button;
        resetInventory();
    }

    public void click(final Player player, final int slot) {
        final Button button;

        if (player == null || slot < 0 || slot >= this.buttons.length)
            return;
        button = this.buttons[slot];
        if (button != null)
            button.click(player, this, this.items[slot], slot);
    }

    public void click(final InventoryClickEvent event) {
        final HumanEntity humanEntity;

        if (event == null)
            return;
        humanEntity = event.getWhoClicked();
        if (!(humanEntity instanceof Player))
            return;
        click((Player) humanEntity, event.getSlot());
    }

    public void close(final Player player) {
        if (this.closeButton != null)
            this.closeButton.close(player, this);
    }

    public void close(final InventoryCloseEvent event) {
        final HumanEntity humanEntity;

        if (event == null)
            return;
        humanEntity = event.getPlayer();
        if (!(humanEntity instanceof Player))
            return;
        close((Player) humanEntity);
    }

    public String getTitle() {
        return this.title;
    }

    public int getSize() {
        return this.items.length;
    }

    public int getRowCount() {
        return this.items.length / 9;
    }

    public Inventory getInventory() {
        if (this.inventory == null)
            this.inventory = buildInventory();
        return this.inventory;
    }

    public void clear() {
        for (int i = 0; i < this.items.length; i++) {
            this.items[i] = null;
            this.buttons[i] = null;
        }
    }

    public void delete() {
        this.manager.menus.remove(this);
    }

}
