package me.brotherhong.currencyplanting.commands;

import me.brotherhong.currencyplanting.CurrencyPlanting;
import me.brotherhong.currencyplanting.Messages;
import me.brotherhong.currencyplanting.commands.subcommands.HelpCommand;
import me.brotherhong.currencyplanting.commands.subcommands.ReloadCommand;
import me.brotherhong.currencyplanting.commands.subcommands.SetCurrencyCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandManager implements TabExecutor {

    private final CurrencyPlanting plugin;
    private static final List<SubCommand> subCommands = new ArrayList<>();
    private final Messages messages;

    public CommandManager(CurrencyPlanting plugin) {
        this.plugin = plugin;
        this.messages = plugin.getMessages();

        subCommands.add(new ReloadCommand(plugin));
        subCommands.add(new SetCurrencyCommand(plugin));
        subCommands.add(new HelpCommand(plugin));
        subCommands.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;
        Player player = (Player) sender;
        if (args.length == 0) {
            subCommands.stream()
                    .filter(c -> c.getName().equalsIgnoreCase("help"))
                    .collect(Collectors.toList())
                    .get(0).execute(player, args);
            return true;
        }
        for (SubCommand subCommand : subCommands) {
            if (subCommand.getName().equalsIgnoreCase(args[0])) {
                if (!subCommand.hasPermission(player)) {
                    messages.sendNoPermission(player);
                    return true;
                }
                subCommand.execute(player, args);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (SubCommand subCommand : subCommands) {
                String commandName = subCommand.getName();
                if (args[0].startsWith(commandName)) {
                    result.add(commandName);
                }
            }
            return result;
        }
        return null;
    }

    public static List<SubCommand> getSubCommands() {
        return subCommands;
    }
}
