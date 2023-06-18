package me.jaja.jajalibrary.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class VersionChecker {

    public List<String> compatibleVersions = new ArrayList<>(){{
        add("1.13");
        add("1.14");
        add("1.15");
        add("1.16");
        add("1.17");
        add("1.18");
        add("1.19");
        add("1.20");
        add("1.21");
    }};

    public boolean isSupportedVersion(Plugin plugin) {
        String version = plugin.getServer().getBukkitVersion();
        if (!compatibleVersions.contains(version.substring(0, 4))) {
            plugin.getLogger().severe("--------------------------");
            plugin.getLogger().severe("Your Server version is not supported. The plugin will NOT load.");
            plugin.getLogger().severe("Supported version is 1.13-1.19");
            plugin.getLogger().severe("--------------------------");
            plugin.getPluginLoader().disablePlugin(plugin);
            return false;
        } else if (!Bukkit.getName().equals("Paper") && !Bukkit.getName().equals("Spigot") && !Bukkit.getName().equals("Purpur")) {
            plugin.getLogger().warning("--------------------------");
            plugin.getLogger().warning("Your Server is not running Spigot, Paper or Purpur (" + Bukkit.getName() + " detected).");
            plugin.getLogger().warning("The plugin WILL load but it MAY NOT work properly.");
            plugin.getLogger().warning("Full support is guaranteed only on Spigot/Paper/Purpur.");
            plugin.getLogger().warning("--------------------------");
        }
        return true;
    }
}
