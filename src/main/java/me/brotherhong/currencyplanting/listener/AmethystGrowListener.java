package me.brotherhong.currencyplanting.listener;

import me.brotherhong.currencyplanting.CurrencyPlanting;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockSpreadEvent;

import java.util.Random;
import java.util.logging.Level;

public class AmethystGrowListener extends MyListener {

    public AmethystGrowListener(CurrencyPlanting plugin) {
        super(plugin);
    }

    @EventHandler
    public void onAmethystGrow(BlockSpreadEvent event) {
        if (!isAmethyst(event.getNewState().getType()))
            return;
        if (config.getDisabledWorld().contains(event.getBlock().getWorld().getName()))
            return;
        // plugin.getServer().getLogger().log(Level.INFO, "BUDDING_AMETHYST_GROWTH");
        int chance = config.getChance();
        if (!canGrowth(chance)) {
            event.setCancelled(true);
            // plugin.getServer().getLogger().log(Level.INFO, "CANCEL GROWTH");
        }
    }

    private boolean canGrowth(int chance) {
        Random random = new Random();
        return ((random.nextInt(100) + 1) <= chance);
    }

    private boolean isAmethyst(Material type) {
        return (type == Material.AMETHYST_CLUSTER ||
                type == Material.LARGE_AMETHYST_BUD ||
                type == Material.MEDIUM_AMETHYST_BUD ||
                type == Material.SMALL_AMETHYST_BUD);
    }
}
