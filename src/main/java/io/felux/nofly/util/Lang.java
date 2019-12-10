package io.felux.nofly.util;

import io.felux.nofly.NoFly;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Optional;

public enum Lang {
    PREFIX("&c[&lNF&c]"),

    MAIN_COMMAND("{0} &7You're running &f{1} &7version &bv{2} &7by &f{3}&7."),

    HELP_COMMAND_HEADER("", "{0} &7Listing commands:", "&7"),
    HELP_COMMAND_FORMAT(" &c/nofly {1} &8- &7{2}"),
    HELP_COMMAND_FOOTER("", "{0} &7A total of &f{1} &7added."),

    LIST_REGIONS_COMMAND_HEADER("", "{0} &7Blacklisted Regions:", "&7"),
    LIST_REGIONS_COMMAND_FORMAT(" &8- &c{1}"),
    LIST_REGIONS_COMMAND_FOOTER("", "{0} &7A total of &f{1} &7added."),

    LIST_WORLDS_COMMAND_HEADER("", "{0} &7Blacklisted Worlds:", "&7"),
    LIST_WORLDS_COMMAND_FORMAT(" &8- &c{1}"),
    LIST_WORLDS_COMMAND_FOOTER("", "{0} &7A total of &f{1} &7added."),

    RELOAD_COMMAND("{0} &7Successfully reloaded plugin."),

    ERROR_NO_PERMISSION_COMMAND("{0} &7You don't have permission to do that."),
    ERROR_PLAYER_ONLY("{0} &7That's a player only command."),
    ERROR_INVALID_COMMAND("{0} &7That's an invalid command, use &f/nofly help&7."),
    ERROR_INVALID_PLAYER("{0} &7That player couldn't be found."),

    CANNOT_USE_IN_REGION("{0} &7You cannot fly in that region."),
    CANNOT_USE_IN_WORLD("{0} &7You cannot fly in that world."),

    COMMAND_USAGE("{0} &7Usage: &b/nofly {1}");

    private static FileConfiguration c;
    private String message;

    Lang(final String... def) {
        this.message = String.join("\n", def);
    }

    public static String format(String s, final Object... objects) {
        for (int i = 0; i < objects.length; ++i) {
            s = s.replace("{" + i + "}", String.valueOf(objects[i]));
        }
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static boolean init() {
        NoFly plugin = NoFly.getPlugin();
        Lang.c = plugin.getConfig();
        for (final Lang value : values()) {
            if (value.getMessage().split("\n").length == 1) {
                Lang.c.addDefault(value.getPath().toLowerCase(), value.getMessage());
            } else {
                Lang.c.addDefault(value.getPath().toLowerCase(), value.getMessage().split("\n"));
            }
        }
        Lang.c.options().copyDefaults(true);
        plugin.saveConfig();
        return true;
    }

    private String getMessage() {
        return this.message;
    }

    public String getPath() {
        return "message." + this.name().toLowerCase().toLowerCase();
    }

    public void send(final Player player, final Object... args) {
        final String message = this.asString(args);
        Arrays.stream(message.split("\n")).forEach(player::sendMessage);
    }

    public void send(final CommandSender sender, final Object... args) {
        if (sender instanceof Player) {
            this.send((Player) sender, args);
        } else {
            Arrays.stream(this.asString(args).split("\n")).forEach(sender::sendMessage);
        }
    }

    public String asString(final Object... objects) {
        Optional<String> opt = Optional.empty();
        if (Lang.c.contains(this.getPath())) {
            if (Lang.c.isList(getPath())) {
                opt = Optional.of(String.join("\n", Lang.c.getStringList(this.getPath())));
            } else if (Lang.c.isString(this.getPath())) {
                opt = Optional.ofNullable(Lang.c.getString(this.getPath()));
            }
        }
        return this.format(opt.orElse(this.message), objects);
    }
}

