package me.brotherhong.currencyplanting.commands.subcommands;

import me.brotherhong.currencyplanting.CurrencyPlanting;
import me.brotherhong.currencyplanting.Messages;
import me.brotherhong.currencyplanting.Permissions;
import me.brotherhong.currencyplanting.commands.CommandManager;
import me.brotherhong.currencyplanting.commands.SubCommand;
import org.bukkit.entity.Player;

public class HelpCommand extends SubCommand {

    public HelpCommand(CurrencyPlanting plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "List all commands of this plugin.";
    }

    @Override
    public String getUsage() {
        return "/cp help";
    }

    @Override
    public boolean hasPermission(Player player) {
        return player.hasPermission(Permissions.HELP);
    }

    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage(Messages.trans("&d-----------------" + messages.getPrefix() + "&d-----------------"));
        for (SubCommand subCommand : CommandManager.getSubCommands()) {
            player.sendMessage(Messages.trans("&d" + subCommand.getUsage() + ": &e" + subCommand.getDescription()));
        }
    }
}
