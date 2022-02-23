package me.brotherhong.currencyplanting;

import me.brotherhong.currencyplanting.commands.CommandManager;
import me.brotherhong.currencyplanting.listener.AmethystBreakListener;
import me.brotherhong.currencyplanting.listener.AmethystGrowListener;
import me.brotherhong.currencyplanting.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class CurrencyPlanting extends JavaPlugin {

    public final CurrencyPlanting instance;
    private final Config config;
    private final Messages messages;

    public CurrencyPlanting() {
        instance = this;
        config = new Config(this);
        messages = new Messages(this);
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new AmethystBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new AmethystGrowListener(this), this);
        getCommand("currencyplanting").setExecutor(new CommandManager(this));
    }

    @Override
    public void onDisable() {

    }

    public CurrencyPlanting getInstance() {
        return instance;
    }

    public Config getMyConfig() {
        return config;
    }

    public Messages getMessages() {
        return messages;
    }
}
