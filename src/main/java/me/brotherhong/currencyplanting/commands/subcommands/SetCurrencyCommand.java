package me.brotherhong.currencyplanting.commands.subcommands;

import me.brotherhong.currencyplanting.CurrencyPlanting;
import me.brotherhong.currencyplanting.Permissions;
import me.brotherhong.currencyplanting.commands.SubCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SetCurrencyCommand extends SubCommand {

    public SetCurrencyCommand(CurrencyPlanting plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getDescription() {
        return "Set the item in hand as the currency to drop.";
    }

    @Override
    public String getUsage() {
        return "/cp set";
    }

    @Override
    public boolean hasPermission(Player player) {
        return player.hasPermission(Permissions.SET_CURRENCY);
    }

    @Override
    public void execute(Player player, String[] args) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType().isAir()) {
            messages.sendNoItemInHand(player);
            return;
        }
        config.getConfig().set("currency", item);
        config.saveConfig();
        messages.sendSuccessSet(player);
    }
}
