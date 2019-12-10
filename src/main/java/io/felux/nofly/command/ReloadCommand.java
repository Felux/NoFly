package io.felux.nofly.command;

import io.felux.nofly.NoFly;
import io.felux.nofly.command.util.Command;
import io.felux.nofly.util.Lang;
import org.bukkit.command.CommandSender;

public class ReloadCommand {
    @Command(aliases = {"reload"}, about = "Reload the configuration files.", permission = "nofly.reload", usage = "reload")
    public static void execute(final CommandSender sender, final NoFly plugin, final String[] args) {
        plugin.reloadConfig();
        Lang.RELOAD_COMMAND.send(sender, Lang.PREFIX.asString());
    }
}
