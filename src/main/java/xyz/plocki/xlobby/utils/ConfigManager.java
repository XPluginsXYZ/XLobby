package xyz.plocki.xlobby.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.plocki.xlobby.utils.files.FileBuilder;

public class ConfigManager {

    private static YamlConfiguration yamlConfiguration;

    public static void init() {
        FileBuilder fileBuilder = new FileBuilder("plugins/XLobby/config.yml");
        yamlConfiguration = fileBuilder.getYaml();
        if(yamlConfiguration.getString("prefix") == null) {
            yamlConfiguration.set("prefix", "&c&lX&6&lLobby &8» &7");
            yamlConfiguration.set("noPermissions", "§cDu hast dazu keine Rechte.");
            fileBuilder.save();
        }
    }

    public String getPrefix() {
        return yamlConfiguration.getString("prefix").replaceAll("&", "§");
    }

    public String getNoPermissions() {
        return yamlConfiguration.getString("noPermissions");
    }

}
