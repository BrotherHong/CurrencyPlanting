package me.brotherhong.currencyplanting.commands.subcommands;

import me.brotherhong.currencyplanting.CurrencyPlanting;
import me.brotherhong.currencyplanting.Permissions;
import me.brotherhong.currencyplanting.commands.SubCommand;
import org.bukkit.entity.Player;

public class ReloadCommand extends SubCommand {

    public ReloadCommand(CurrencyPlanting plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reload this plugin.";
    }

    @Override
    public String getUsage() {
        return "/cp reload";
    }

    @Override
    public boolean hasPermission(Player player) {
        return player.hasPermission(Permissions.RELOAD);
    }

    @Override
    public void execute(Player player, String[] args) {
        config.reload();
        messages.sendReloadComplete(player);
    }
}
