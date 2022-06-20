package fr.mrcubee.menu.object;

import fr.mrcubee.menu.CloseButton;
import fr.mrcubee.menu.Menu;
import org.bukkit.entity.Player;

@FunctionalInterface
public interface ObjectCloseButton<T> extends CloseButton {

    @Override
    default void close(final Player player, final Menu menu) {
        if (menu instanceof ObjectMenu)
            close(player, (ObjectMenu<T>) menu);
    }

    void close(final Player player, final ObjectMenu<T> menu);

}
