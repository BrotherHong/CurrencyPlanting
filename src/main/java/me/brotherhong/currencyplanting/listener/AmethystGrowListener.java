package me.brotherhong.currencyplanting.listener;

import me.brotherhong.currencyplanting.CurrencyPlanting;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockGrowEvent;

import java.util.Random;
import java.util.logging.Level;

public class AmethystGrowListener extends MyListener {

    public AmethystGrowListener(CurrencyPlanting plugin) {
        super(plugin);
    }

    @EventHandler
    public void onAmethystGrow(BlockGrowEvent event) {
        if (event.getBlock().getType() != Material.BUDDING_AMETHYST)
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
}
