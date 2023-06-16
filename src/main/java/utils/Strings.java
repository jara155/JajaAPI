package utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Strings {

    private final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public String colorText(String text) {

        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            String color = text.substring(matcher.start(), matcher.end());
            text = text.replace(color, ChatColor.of(color) + "");
            matcher = pattern.matcher(text);
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public List<String> formatList(String text) {
        List<String> textFromLocale = Instances.localesInstance.getLocale().getStringList(text);
        List<String> formattedText = new ArrayList<>();

        for(String row : textFromLocale) {
            formattedText.add(colorText(row));
        }
        return formattedText;
    }

    public List<String> formatList(String text, Player p) {
        List<String> textFromLocale = Instances.localesInstance.getLocale().getStringList(text);
        List<String> formattedText = new ArrayList<>();

        for(String row : textFromLocale) {
            row = colorText(row);
//            if(instance.getHooksManager().usePlaceholderAPI) {
//                row = PlaceholderAPI.setPlaceholders(p, row);
//            }
            formattedText.add(row);
        }
        return formattedText;
    }

    public String formatLocationWOPitchYaw(Location location) {
        return location.getWorld().getName() + ", " + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ();
    }

    public List<String> formatList(String text, String placeholder, List<String> replace, Player p) {
        List<String> textFromLocale = Instances.localesInstance.getLocale().getStringList(text);
        List<String> formattedText = new ArrayList<>();

        for(String row : textFromLocale) {
            row = colorText(row);

            if(row.contains(placeholder)) {
                row = row.replace(placeholder, "");

                formattedText.addAll(replace);
            }

//            if(instance.getHooksManager().usePlaceholderAPI) {
//                row = PlaceholderAPI.setPlaceholders(p, row);
//            }

            formattedText.add(colorText(row));
        }

        return formattedText;
    }

    public List<String> formatList(String text, String placeholder, String replace, Player p) {
        List<String> textFromLocale = Instances.localesInstance.getLocale().getStringList(text);
        List<String> formattedText = new ArrayList<>();

        for(String row : textFromLocale) {
            row = colorText(row);

            List<String> placeholders = Arrays.asList(placeholder.split("\\s*,\\s*"));
            List<String> replaces = Arrays.asList(replace.split("\\s*,\\s*"));

            if(placeholders.size() == replaces.size()){
                for (int i = 0; i < placeholders.size(); i++) {
                    row = row.replace(placeholders.get(i), replaces.get(i));
                }
            } else {
                System.out.println(Instances.localesInstance.getLocale().getString("NotEnoughArgs") + " | " + placeholders + " " + replaces);
            }

//            if(instance.getHooksManager().usePlaceholderAPI) {
//                row = PlaceholderAPI.setPlaceholders(p, row);
//            }

            formattedText.add(colorText(row));
        }

        return formattedText;
    }

    public String format(String text){
        if(Instances.localesInstance.getLocale().getString(text) == null) return "Not in localesInstance";
        text = Instances.localesInstance.getLocale().getString(text);

        text = text.replace("%prefix%", Instances.localesInstance.getLocale().getString("Prefix"));

        if (text.contains("%prefix%")) text = text.replace("%prefix%", Instances.localesInstance.getLocale().getString("Prefix") + "&f");

        return colorText(text);
    }

    public String format(String text, String placeholder, String replace){
        text = Instances.localesInstance.getLocale().getString(text) + "&7";

        List<String> placeholders = Arrays.asList(placeholder.split("\\s*,\\s*"));
        List<String> replaces = Arrays.asList(replace.split("\\s*,\\s*"));
        if(placeholders.size() == replaces.size()){
            for (int i = 0; i < placeholders.size(); i++) {
                text = text.replace(placeholders.get(i), replaces.get(i));
            }
        } else {
            System.out.println(Instances.localesInstance.getLocale().getString("NotEnoughArgs") + " | " + placeholders + " " + replaces);
        }

        if (text.contains("%prefix%")) text = text.replace("%prefix%", Instances.localesInstance.getLocale().getString("Prefix") + "&f");

        return colorText(text);
    }

    public String format(String text, String placeholder, String replace, Player p){
        text = Instances.localesInstance.getLocale().getString(text) + "&7";

//        if(instance.getHooksManager().usePlaceholderAPI) {
//            text = PlaceholderAPI.setPlaceholders(p, text);
//        }

        List<String> placeholders = Arrays.asList(placeholder.split("\\s*,\\s*"));
        List<String> replaces = Arrays.asList(replace.split("\\s*,\\s*"));
        if(placeholders.size() == replaces.size()){
            for (int i = 0; i < placeholders.size(); i++) {
                text = text.replace(placeholders.get(i), replaces.get(i));
            }
        } else {
            System.out.println(Instances.localesInstance.getLocale().getString("NotEnoughArgs") + " | " + placeholders + " " + replaces);
        }

        if (text.contains("%prefix%")) text = text.replace("%prefix%", Instances.localesInstance.getLocale().getString("Prefix") + "&f");

        return colorText(text);
    }

    public String ListToString(List<String> strings) {
        StringBuilder message = new StringBuilder();

        for (String string : strings) {
            message.append(string);
            if(strings.size() != strings.indexOf(string)) message.append("\n");
        }

        return message.toString();
    }

    public String capitalize(String text) {
        text = text.toLowerCase();
        text = text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
        return text;
    }
}
