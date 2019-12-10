package io.felux.nofly.listener;

import io.felux.nofly.NoFly;
import io.felux.nofly.hook.WorldGuardHook;
import io.felux.nofly.util.Lang;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class RegionListener implements Listener {
    private final NoFly plugin = NoFly.getPlugin();

    @EventHandler
    public void onRegionEnter(PlayerMoveEvent e) {
        final Player player = e.getPlayer();

        if (WorldGuardHook.isEnabled() && !player.hasPermission("nofly.bypass")) {
            for (final String region : plugin.getBlacklistedRegions()) {
                if (WorldGuardHook.checkIfPlayerInRegion(player, region) && player.isFlying()) {
                    Lang.CANNOT_USE_IN_REGION.send(player, Lang.PREFIX.asString());
                    player.setFlying(false);
                    return;
                }
            }
        }
    }
}
