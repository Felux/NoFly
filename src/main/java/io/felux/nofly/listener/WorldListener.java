package io.felux.nofly.listener;

import io.felux.nofly.NoFly;
import io.felux.nofly.util.Lang;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WorldListener implements Listener {
    private final NoFly plugin = NoFly.getPlugin();

    @EventHandler
    public void onRegionEnter(PlayerMoveEvent e) {
        final Player player = e.getPlayer();
        if (player.hasPermission("nofly.bypass")) return;
        for (final String world : plugin.getBlacklistedWorlds()) {
            if (player.getWorld().getName().equalsIgnoreCase(world) && player.isFlying()) {
                Lang.CANNOT_USE_IN_WORLD.send(player, Lang.PREFIX.asString());
                player.setAllowFlight(false);
                player.setFlying(false);
                return;
            }
        }
    }
}
