package xyz.plocki.xlobby.utils.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.plocki.xlobby.utils.files.FileBuilder;

public class LocationManager {

    private static FileBuilder fileBuilder;
    private static YamlConfiguration yamlConfiguration;

    public static void init() {
        fileBuilder = new FileBuilder("plugins/XLobby/locations.yml");
        yamlConfiguration = fileBuilder.getYaml();
        if(!yamlConfiguration.isSet("worldTime.default")) {
            yamlConfiguration.set("worldTime.default", 1000L);
        }
        fileBuilder.save();
    }

    public void saveLocation(String name, Location location) {
        yamlConfiguration.set(name + ".X", location.getX());
        yamlConfiguration.set(name + ".Y", location.getY());
        yamlConfiguration.set(name + ".Z", location.getZ());
        yamlConfiguration.set(name + ".YAW", location.getYaw());
        yamlConfiguration.set(name + ".PITCH", location.getPitch());
        yamlConfiguration.set(name + ".WORLD", location.getWorld().getName());
        fileBuilder.save();
    }

    public Location getLocation(String name) {
        return new Location(Bukkit.getWorld(yamlConfiguration.getString(name + ".WORLD")), yamlConfiguration.getDouble(name + ".X"), yamlConfiguration.getDouble(name + ".Y"), yamlConfiguration.getDouble(name + ".Z"), (float) yamlConfiguration.getDouble(name + ".YAW"), (float) yamlConfiguration.getDouble(name + ".PITCH"));
    }

    public long getWorldTime() {
        return yamlConfiguration.getInt("worldTime.default");
    }

}
