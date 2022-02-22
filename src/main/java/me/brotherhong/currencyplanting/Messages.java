package me.brotherhong.currencyplanting;

import me.brotherhong.currencyplanting.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {

    private final CurrencyPlanting plugin;
    private final Config config;

    private String prefix;

    public Messages(CurrencyPlanting plugin) {
        this.plugin = plugin;
        this.config = plugin.getMyConfig();
        prefix = trans(config.getPrefix()) + " ";
    }

    public static String trans(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public String getPrefix() {
        return prefix.trim();
    }

    public void sendNoPermission(Player player) {
        player.sendMessage(prefix + trans(config.getConfig().getString("messages.no-permission")));
    }

    public void sendReloadComplete(Player player) {
        player.sendMessage(prefix + trans(config.getConfig().getString("messages.reload")));
    }

    public void sendNoItemInHand(Player player) {
        player.sendMessage(prefix + trans(config.getConfig().getString("messages.no-item-in-hand")));
    }

    public void sendSuccessSet(Player player) {
        player.sendMessage(prefix + trans(config.getConfig().getString("messages.success-set-currency")));
    }

    public void sendNotReadyToBreak(Player player) {
        player.sendMessage(prefix + trans(config.getConfig().getString("messages.not-ready-to-break")));
    }

    public void sendNeedSilkTouch(Player player) {
        player.sendMessage(prefix + trans(config.getConfig().getString("messages.need-silk-touch")));
    }
}
