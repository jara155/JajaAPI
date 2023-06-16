package utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UpdateChecker {
    public UpdateChecker() {
        try {
            checkVersion("0.0");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getVersion() throws IOException {
        URL pluginWeb;
        try {
            pluginWeb = new URL("https://api.spigotmc.org/legacy/update.php?resource=109725");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        URLConnection con = pluginWeb.openConnection();
        return new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
    }

    public void checkVersion(String version) throws IOException {
        String remoteVersion = getVersion();

        if(version.equals(remoteVersion)) {
            System.out.println("--------------------------");
            System.out.println("Plugin is up to date");
            System.out.println("Plugin version: " + version);
            System.out.println("--------------------------");
        } else {
            System.out.println("--------------------------");
            System.out.println("Plugin has older version ("+version+")");
            System.out.println("Please download newer version ("+remoteVersion+")");
            System.out.println("https://www.spigotmc.org/resources/109725/");
            System.out.println("--------------------------");
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(p.isOp()) {
                    p.sendMessage(Instances.stringsInstance.colorText("&cDetected &bnew &7version ("+ remoteVersion+") on &6JajaCore."));
                }
            }
        }
    }
}
