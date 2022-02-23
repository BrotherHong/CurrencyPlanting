package me.brotherhong.currencyplanting.config;

import me.brotherhong.currencyplanting.CurrencyPlanting;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public abstract class ConfigManager {

    private final CurrencyPlanting plugin;

    private FileConfiguration dataConfig = null;
    private File configFile = null;
    private final String fileName;

    public ConfigManager(CurrencyPlanting plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        // save / initializing the config
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), fileName);
        }

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource(fileName);
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.dataConfig == null) {
            reloadConfig();
        }
        return this.dataConfig;
    }

    public void saveConfig() {
        if (this.dataConfig == null || this.configFile == null) return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), fileName);
        }
        if (!this.configFile.exists()) {
            this.plugin.saveResource(fileName, false);
        }
    }

    public void saveAndReload() {
        saveConfig();
        reload();
    }

    public void reload() {
        reloadConfig();
        load();
    }

    protected abstract void load();
}
