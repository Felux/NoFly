package io.felux.nofly.registerable;

import io.felux.nofly.NoFly;
import io.felux.nofly.listener.RegionListener;
import io.felux.nofly.listener.WorldListener;
import org.bukkit.event.Listener;

public class ListenerRegisterable {
    private static final Listener[] LISTENERS = {
            new RegionListener(),
            new WorldListener()
    };

    public static void register() {
        NoFly pouches = NoFly.getPlugin();
        for (Listener listener : LISTENERS) {
            pouches.getServer().getPluginManager().registerEvents(listener, pouches);
        }
    }
}
