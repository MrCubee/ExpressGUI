package fr.mrcubee.menu.object;

import fr.mrcubee.menu.Button;
import fr.mrcubee.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@FunctionalInterface
public interface ObjectButton<T> extends Button {

    @Override
    default void click(final Player player, final Menu menu, final ItemStack itemStack, int slot) {
        final ObjectMenu<T> objectMenu;

        if (menu instanceof ObjectMenu) {
            objectMenu = (ObjectMenu<T>) menu;
            click(player, objectMenu, itemStack, slot, objectMenu.objects[slot]);
        }
    }

    void click(final Player player, final ObjectMenu<T> menu, final ItemStack itemStack, int slot, T object);
}
