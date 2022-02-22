package me.brotherhong.currencyplanting.listener;

import me.brotherhong.currencyplanting.CurrencyPlanting;
import me.brotherhong.currencyplanting.Messages;
import me.brotherhong.currencyplanting.config.Config;
import org.bukkit.event.Listener;

public class MyListener implements Listener {
    protected CurrencyPlanting plugin;
    protected Config config;
    protected Messages messages;

    public MyListener(CurrencyPlanting plugin) {
        this.plugin = plugin;
        this.config = plugin.getMyConfig();
        this.messages = plugin.getMessages();
    }
}
