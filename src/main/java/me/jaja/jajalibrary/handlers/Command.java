package me.jaja.jajalibrary.handlers;

import me.jaja.jajalibrary.JajaLibrary;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.SimplePluginManager;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class Command extends BukkitCommand implements CommandExecutor {
    private List<String> delayedPlayers;
    private int delay = 1;
    private final int minArgs;
    private final int maxArgs;
    private final boolean playerOnly;

    public Command(String command) {
        this(command, 0);
    }
    public Command(String command, boolean playerOnly){
        this(command, 0, playerOnly);
    }
    public Command(String command, int reqArgs){
        this(command, reqArgs, reqArgs);
    }
    public Command(String command, int minArgs, int maxArgs){
        this(command, minArgs, maxArgs, false);
    }
    public Command(String command, int regArs, boolean playerOnly){
        this(command, regArs, regArs, playerOnly);
    }

    public Command(String command, int minArgs, int maxArgs, boolean playerOnly){
        super(command);

        this.minArgs = minArgs;
        this.maxArgs = maxArgs;
        this.playerOnly = playerOnly;

        CommandMap commandMap = getCommandMap();
        if(commandMap != null){
            commandMap.register(command, this);
        }
    }

    public CommandMap getCommandMap(){
        try {
            if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
                Field field = SimplePluginManager.class.getDeclaredField("commandMap");
                field.setAccessible(true);
                return (CommandMap) field.get(Bukkit.getPluginManager());
            }
        } catch (NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }

        return null;
    }

    public Command enableDelay(int delay){
        this.delay = delay;
        this.delayedPlayers = new ArrayList<>();
        return this;
    }

    public void removeDelay(Player player){
        this.delayedPlayers.remove(player.getName());
    }

    public void sendUsage(CommandSender sender){
        sender.sendMessage(JajaLibrary.stringsInstance.format("Usage", "%USAGE%", getUsage(), (Player) sender));
    }

    public void sendMessage(CommandSender sender, String text){
        sender.sendMessage(JajaLibrary.stringsInstance.format(text));
    }

    public void sendMessage(CommandSender sender, String text, String placeholders, String replaces){
        sender.sendMessage(JajaLibrary.stringsInstance.format(text, placeholders, replaces, (Player) sender));
    }

    public boolean execute(CommandSender sender, String alias, String[] args){
        if(args.length < minArgs || args.length > maxArgs && maxArgs != -1){
            sendUsage(sender);
            return true;
        }

        if(playerOnly && !(sender instanceof Player)){
            sendMessage(sender, "NotAPlayer" );
            return true;
        }

        String permission = getPermission();
        if(permission != null && !(sender.hasPermission(permission) || sender.hasPermission("jaja.*"))){
            sendMessage(sender, "NoPerms");
            return true;
        }

        if(!(sender.hasPermission("jaja.bypass")) && delayedPlayers != null && sender instanceof Player){
            Player p = (Player) sender;

            if(delayedPlayers.contains(p.getName())){
                sendMessage(sender, "IsDelayed");
                return true;
            }

            delayedPlayers.add(p.getName());
//            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager(, () -> {
//                delayedPlayers.remove(p.getName());
//            }, 20L * delay);
        }

        if(!onCommand(sender, args)){
            sendUsage(sender);
        }

        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        return this.onCommand(sender, args);
    }

    public abstract boolean onCommand(CommandSender sender, String[] args);
    public abstract String getUsage();
}
