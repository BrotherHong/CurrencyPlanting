package me.brotherhong.currencyplanting.config;

import me.brotherhong.currencyplanting.CurrencyPlanting;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Config extends ConfigManager {

    private String prefix;
    private List<String> disabledWorld;
    private boolean silkTouch;
    private boolean drop;
    private int chance;
    private ItemStack currency;

    public Config(CurrencyPlanting plugin) {
        super(plugin, "config.yml");
        load();
    }

    @Override
    protected void load() {
        prefix = getConfig().getString("prefix");
        disabledWorld = getConfig().getStringList("disabled-world");
        silkTouch = getConfig().getBoolean("budding.silk-touch");
        drop = getConfig().getBoolean("budding.drop");
        chance = getConfig().getInt("budding.chance");
        currency = getConfig().getItemStack("currency");
    }

    public List<String> getDisabledWorld() {
        if (disabledWorld == null) {
            disabledWorld = new ArrayList<>();
        }
        return disabledWorld;
    }

    public ItemStack getCurrency() {
        if (currency == null) {
            currency = new ItemStack(Material.AMETHYST_CLUSTER, 1);
        }
        return currency;
    }

    public boolean needSilkTouch() {
        return silkTouch;
    }

    public boolean canDrop() {
        return drop;
    }

    public int getChance() {
        if (chance < 0 || 100 < chance) {
            chance = 100;
        }
        return chance;
    }

    public String getPrefix() {
        return prefix;
    }
}
