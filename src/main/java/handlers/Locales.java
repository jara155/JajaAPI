package handlers;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
//
//    public void createFolder(){
//        if(!localesFolder.exists()){
//            localesFolder.mkdirs();
//        }
//    }

//    public void createDefaultLocale(){
//        File localeEN = new File(localesFolder, "messages_en.yml");
//        File localeCS = new File(localesFolder, "messages_cs.yml");
//        if(localeEN.exists() && localeCS.exists()) return;
//
//        try{
//            localeEN.createNewFile();
//            localeCS.createNewFile();
//            InputStream streamEN = localesFolder..getResource("locales/messages_en.yml");
//            InputStream streamCS = JajaDungeons.getInstance().getResource("locales/messages_cs.yml");
//            Files.copy(streamEN, localeEN.toPath(), StandardCopyOption.REPLACE_EXISTING);
//            Files.copy(streamCS, localeCS.toPath(), StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }

    public void setLocale(){
        if(language == null) {
//            JajaDungeons.getInstance().getLogger().severe("--------------------------");
//            JajaDungeons.getInstance().getLogger().severe("LANGUAGE IS NOT SET IN CONFIG");
//            JajaDungeons.getInstance().getLogger().severe("Disabling plugin..");
//            JajaDungeons.getInstance().getLogger().severe("--------------------------");
//            JajaDungeons.getInstance().getPluginLoader().disablePlugin(JajaDungeons.getInstance());
            return;
        }
        for (File locale : locales) {
            if (locale.getName().contains(language)) {
                localeConfig = YamlConfiguration.loadConfiguration(locale);
                localeConfig.options().copyDefaults(true);
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

    public void setLocalesFolder(File directory) {
        localesFolder = directory;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

