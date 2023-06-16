package utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class VersionChecker {

    public boolean isSupportedVersion(Plugin plugin) {
        String version = plugin.getServer().getBukkitVersion();
//        if (!plugin.compatibleVersions.contains(version.substring(0, 4))) {
//            System.out.println("--------------------------");
//            System.out.println("Your Server version is not supported. The plugin will NOT load.");
//            System.out.println("Supported version is 1.13-1.19");
//            System.out.println("--------------------------");
////            JajaDungeons.getInstance().getPluginLoader().disablePlugin(JajaDungeons.getInstance());
//            return false;
//        } else if (!Bukkit.getName().equals("Paper") && !Bukkit.getName().equals("Spigot") && !Bukkit.getName().equals("Purpur")) {
//            System.out.println("--------------------------");
//            System.out.println("Your Server is not running Spigot, Paper or Purpur (" + Bukkit.getName() + " detected).");
//            System.out.println("The plugin WILL load but it MAY NOT work properly.");
//            System.out.println("Full support is guaranteed only on Spigot/Paper/Purpur.");
//            System.out.println("--------------------------");
//        }
        return true;
    }
}
