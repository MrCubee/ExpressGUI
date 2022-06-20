package fr.mrcubee.menu;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface CloseButton {

    public void close(final Player player, final Menu menu);

}
