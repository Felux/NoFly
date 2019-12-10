package io.felux.nofly.command;

import io.felux.nofly.NoFly;
import io.felux.nofly.command.util.Command;
import io.felux.nofly.registerable.CommandRegisterable;
import io.felux.nofly.util.Lang;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HelpCommand {
    @Command(aliases = {"help"}, about = "View list of commands.", permission = "nofly.help", usage = "help")
    public static void execute(final CommandSender sender, final NoFly plugin, final String[] args) {
        final List<Method> commandMethods = new ArrayList<Method>();
        for (final Method method : CommandRegisterable.getCommandManager().getCommands().values()) {
            if (!commandMethods.contains(method)) {
                commandMethods.add(method);
            }
        }
        Lang.HELP_COMMAND_HEADER.send(sender, Lang.PREFIX.asString(), commandMethods.size());
        for (final Method commandMethod : commandMethods) {
            final Command commandAnnotation = commandMethod.getAnnotation(Command.class);
            if (!sender.hasPermission(commandAnnotation.permission())) {
                continue;
            }
            Lang.HELP_COMMAND_FORMAT.send(sender, String.join(",", commandAnnotation.aliases()), commandAnnotation.usage(), commandAnnotation.about());
        }
        Lang.HELP_COMMAND_FOOTER.send(sender, Lang.PREFIX.asString(), commandMethods.size());
    }
}
