package me.jaja.jajalibrary.handlers;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Locales {

    private YamlConfiguration localeConfig;
    private File localesFolder = new File("");
    private String language = "";
    public List<File> locales = new ArrayList<>();

    public void setup(){
        scanLocales();
    }

    public void scanLocales(){
        locales.addAll(Arrays.asList(localesFolder.listFiles()));
        setLocale();
    }

    public void createFolder(){
        localesFolder.mkdirs();
    }

    public void createDefaultLocale(Map<String, InputStream> localesStreams){

        for (String fileName : localesStreams.keySet()) {
            File localeFile = new File(localesFolder, fileName);
            if(localeFile.exists()) continue;
            try {
                localeFile.createNewFile();
                Files.copy(localesStreams.get(fileName), localeFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void setLocale(){
        if(language == null) {
            System.out.println("--------------------------");
            System.out.println("LANGUAGE IS NOT SET IN CONFIG");
            System.out.println("Set it then reload plugin");
            System.out.println("--------------------------");;
            return;
        }
        for (File locale : locales) {
            String localeName = locale.getName().replace("messages_", "").replace(".yml", "");

            if (localeName.equals(language)) {
                localeConfig = YamlConfiguration.loadConfiguration(locale);
                localeConfig.options().copyDefaults(true);
                setLocaleConfig(localeConfig);
                break;
            }
        }
    }

    public void reload() {
        for (File locale : locales) {
            String localeName = locale.getName().replace("messages_", "").replace(".yml", "");

            if (localeName.equals(language)) {
                localeConfig = YamlConfiguration.loadConfiguration(locale);
                localeConfig.options().copyDefaults(true);
                setLocaleConfig(localeConfig);
                break;
            }
        }
    }

    public YamlConfiguration getLocale() {
        return localeConfig;
    }

    public List<File> getLocales() {
        return locales;
    }

    public String getLanguage() {
        return language;
    }

    public void setLocaleConfig(YamlConfiguration localeConfig) {
        this.localeConfig = localeConfig;
    }

    public void setLocalesFolder(File directory) {
        localesFolder = directory;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

