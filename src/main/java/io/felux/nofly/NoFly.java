package io.felux.nofly;

import io.felux.nofly.hook.WorldGuardHook;
import io.felux.nofly.registerable.CommandRegisterable;
import io.felux.nofly.registerable.ListenerRegisterable;
import io.felux.nofly.util.Lang;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class NoFly extends JavaPlugin {
    private static NoFly plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        Lang.init();
        WorldGuardHook.register();
        CommandRegisterable.register();
        ListenerRegisterable.register();
    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    public static NoFly getPlugin() {
        return plugin;
    }

    public List<String> getBlacklistedRegions() {
        return getConfig().getStringList("blacklisted.region");
    }

    public List<String> getBlacklistedWorlds() {
        return getConfig().getStringList("blacklisted.world");
    }
}
