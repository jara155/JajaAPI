package me.jaja.jajalibrary.utils;

import me.jaja.jajalibrary.JajaLibrary;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateChecker {
    Map<String, String> spigotCodes = new HashMap<>(){{
        put("Jajatils", "104460");
        put("JajaSkilltree", "109725");
        put("JajaSell", "109528");
        put("JajaAuth", "");
        put("JajaShop", "");
        put("JajaDungeons", "");
        put("JajaCosmetics", "");
    }};

    public String getVersion(JavaPlugin plugin) throws IOException {
        URL pluginWeb;
        if(spigotCodes.get(plugin.getName()).isEmpty()) {
            return "1.0";
        } else {
            try {
                pluginWeb = new URL("https://api.spigotmc.org/legacy/update.php?resource="+spigotCodes.get(plugin.getName()));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            URLConnection con = pluginWeb.openConnection();
            return new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
        }
    }

    public void checkVersion(JavaPlugin plugin) throws IOException {
        String remoteVersion = getVersion(plugin);

        if(plugin.getDescription().getVersion().equals(remoteVersion)) {
            plugin.getLogger().info("--------------------------");
            plugin.getLogger().info("Plugin is up to date");
            plugin.getLogger().info("Plugin version: " + plugin.getDescription().getVersion());
            plugin.getLogger().info("--------------------------");
        } else {
            plugin.getLogger().warning("--------------------------");
            plugin.getLogger().warning("Plugin has older version ("+plugin.getDescription().getVersion()+")");
            plugin.getLogger().warning("Please download newer version ("+remoteVersion+")");
            plugin.getLogger().warning("https://www.spigotmc.org/resources/"+spigotCodes.get(plugin.getName()));
            plugin.getLogger().warning("--------------------------");
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(p.isOp()) {
                    p.sendMessage(JajaLibrary.stringsInstance.colorText("&cDetected &bnew &7version ("+ remoteVersion+") on &6"+plugin.getName()));
                }
            }
        }
    }
}
