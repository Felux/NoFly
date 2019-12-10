package io.felux.nofly.command;

import io.felux.nofly.NoFly;
import io.felux.nofly.command.util.Command;
import io.felux.nofly.util.Lang;
import org.bukkit.command.CommandSender;

import java.util.List;

public class WorldsCommand {
    @Command(aliases = {"worlds"}, about = "List blacklisted worlds.", permission = "nofly.worlds", usage = "worlds")
    public static void execute(final CommandSender sender, final NoFly plugin, final String[] args) {
        List<String> worldList = plugin.getBlacklistedWorlds();
        Lang.LIST_WORLDS_COMMAND_HEADER.send(sender, Lang.PREFIX.asString(), worldList.size());
        for (String world : worldList)
            Lang.LIST_WORLDS_COMMAND_FORMAT.send(sender, Lang.PREFIX.asString(), world);
        Lang.LIST_WORLDS_COMMAND_FOOTER.send(sender, Lang.PREFIX.asString(), worldList.size());
    }
}
