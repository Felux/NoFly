package io.felux.nofly.command;

import io.felux.nofly.NoFly;
import io.felux.nofly.command.util.Command;
import io.felux.nofly.util.Lang;
import org.bukkit.command.CommandSender;

import java.util.List;

public class RegionsCommand {
    @Command(aliases = {"regions"}, about = "List blacklisted regions.", permission = "nofly.regions", usage = "regions")
    public static void execute(final CommandSender sender, final NoFly plugin, final String[] args) {
        List<String> regionList = plugin.getBlacklistedRegions();
        Lang.LIST_REGIONS_COMMAND_HEADER.send(sender, Lang.PREFIX.asString(), regionList.size());
        for (String region : regionList) Lang.LIST_REGIONS_COMMAND_FORMAT.send(sender, Lang.PREFIX.asString(), region);
        Lang.LIST_REGIONS_COMMAND_FOOTER.send(sender, Lang.PREFIX.asString(), regionList.size());
    }
}
