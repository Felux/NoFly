package io.felux.nofly.registerable;

import io.felux.nofly.NoFly;
import io.felux.nofly.command.util.CommandManager;
import org.bukkit.command.PluginCommand;

public class CommandRegisterable {
    private static final NoFly PLUGIN = NoFly.getPlugin();
    private static CommandManager commandManager;

    public static void register() {
        commandManager = new CommandManager(PLUGIN);

        PluginCommand pluginCommand = PLUGIN.getCommand("nofly");
        if (pluginCommand == null) {
            throw new RuntimeException("The /nofly command isn't found, contact the developer!");
        }

        pluginCommand.setExecutor(new io.felux.nofly.command.util.CommandExecutor());
        if (pluginCommand.getPlugin() != PLUGIN) {
            PLUGIN.getLogger().warning("/pouches command is being handled by plugin other than " + PLUGIN.getDescription().getName() + ". You must use /nofly:nofly instead.");
        }
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }
}
