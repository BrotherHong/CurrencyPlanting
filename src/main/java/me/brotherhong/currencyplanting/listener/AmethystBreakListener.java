package me.brotherhong.currencyplanting.listener;

import me.brotherhong.currencyplanting.CurrencyPlanting;
import me.brotherhong.currencyplanting.Permissions;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class AmethystBreakListener extends MyListener {

    public AmethystBreakListener(CurrencyPlanting plugin) {
        super(plugin);
    }

    @EventHandler
    public void onAmethystBreak(BlockBreakEvent event) {
        if (!isAmethyst(event.getBlock().getType()))
            return;
        if (config.getDisabledWorld().contains(event.getBlock().getWorld().getName()))
            return;
        Player player = event.getPlayer();
        if (!player.hasPermission(Permissions.BREAK_CLUSTER)) {
            event.setCancelled(true);
            messages.sendNoPermission(player);
            return;
        }
        if (event.getBlock().getType() != Material.AMETHYST_CLUSTER) {
            event.setCancelled(true);
            messages.sendNotReadyToBreak(player);
            return;
        }
        ItemStack currency = config.getCurrency();
        event.setDropItems(false);
        player.getWorld().dropItem(event.getBlock().getLocation(), currency);
    }

    @EventHandler
    public void onBuddingBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.BUDDING_AMETHYST)
            return;
        if (config.getDisabledWorld().contains(event.getBlock().getWorld().getName()))
            return;
        Player player = event.getPlayer();
        if (!player.hasPermission(Permissions.BREAK_BUDDING)) {
            event.setCancelled(true);
            messages.sendNoPermission(player);
            return;
        }
        if (!config.canDrop()) {
            return;
        }
        if (config.needSilkTouch() &&
                !player.getInventory().getItemInMainHand().getItemMeta()
                        .hasEnchant(Enchantment.SILK_TOUCH)) {
            event.setCancelled(true);
            messages.sendNeedSilkTouch(player);
            return;
        }
        event.setDropItems(true);
    }

    private boolean isAmethyst(Material type) {
        return (type == Material.AMETHYST_CLUSTER ||
                type == Material.LARGE_AMETHYST_BUD ||
                type == Material.MEDIUM_AMETHYST_BUD ||
                type == Material.SMALL_AMETHYST_BUD);
    }

}
