package fr.mrcubee.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@FunctionalInterface
public interface Button {

    public void click(final Player player, final Menu menu, final ItemStack itemStack, int slot);

}
