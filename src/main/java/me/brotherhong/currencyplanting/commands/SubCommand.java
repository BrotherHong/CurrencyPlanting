package me.brotherhong.currencyplanting.commands;

import me.brotherhong.currencyplanting.CurrencyPlanting;
import me.brotherhong.currencyplanting.Messages;
import me.brotherhong.currencyplanting.config.Config;
import org.bukkit.entity.Player;

public abstract class SubCommand {

    protected final CurrencyPlanting plugin;
    protected final Config config;
    protected final Messages messages;

    public SubCommand(CurrencyPlanting plugin) {
        this.plugin = plugin;
        this.config = plugin.getMyConfig();
        this.messages = plugin.getMessages();
    }

    public abstract String getName();
    public abstract String getDescription();
    public abstract String getUsage();

    public abstract boolean hasPermission(Player player);

    public abstract void execute(Player player, String[] args);
}
