package me.jaja.jajalibrary.handlers;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Hooks {
    List<String> supportedEconomies = new ArrayList<>(){{
        add("Vault");
        add("LiteEco");
    }};
    public boolean usePlaceholderAPI = false;
    public boolean useEconomy = false;
    public boolean usePlugManX = false;
    private Economy econ;

    public void loadHooks() {
        for (String economy : supportedEconomies) {
            if(Bukkit.getPluginManager().getPlugin(economy) != null) {
                useEconomy = true;
                System.out.println(economy + " Hooked");
                break;
            }
        }
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            usePlaceholderAPI = true;
            System.out.println("PlaceholderAPI Hooked");
        }
        if(Bukkit.getPluginManager().getPlugin("PlugManX") != null) {
            usePlaceholderAPI = true;
            System.out.println("PlugManX Hooked");
        }
    }

    public void setupEconomy(JavaPlugin plugin) {
        if(!useEconomy) {
            plugin.getLogger().severe("--------------------------");
            plugin.getLogger().severe("SUPPORTED ECONOMY IS NOT INSTALLED");
            plugin.getLogger().severe("Supported: " + getSupportedEconomies());
            plugin.getLogger().severe("Disabling plugin...");
            plugin.getLogger().severe("--------------------------");
            plugin.getPluginLoader().disablePlugin(plugin);
        } else {
            RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
            if(rsp == null) return;
            econ = rsp.getProvider();
        }
    }

    public Economy getEcon() {
        return econ;
    }

    public String getSupportedEconomies() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < supportedEconomies.size(); i++) {
            if(i == 4) break;
            String economy = supportedEconomies.get(i);
            stringBuilder.append(economy).append(" ");
        }

        return stringBuilder.toString();
    }
}
