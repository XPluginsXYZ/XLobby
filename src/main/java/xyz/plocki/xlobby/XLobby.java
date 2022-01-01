package xyz.plocki.xlobby;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.plocki.xlobby.commands.BuildCommand;
import xyz.plocki.xlobby.commands.FlyCommand;
import xyz.plocki.xlobby.commands.SetLocationCommand;
import xyz.plocki.xlobby.listeners.BuildListener;
import xyz.plocki.xlobby.listeners.PlayerListener;
import xyz.plocki.xlobby.utils.ConfigManager;
import xyz.plocki.xlobby.utils.InventoryManager;
import xyz.plocki.xlobby.utils.LocationManager;

public final class XLobby extends JavaPlugin {

    public static String prefix = "§c§lLoading §8» §7";
    public static String noPermissions = "§cDu hast dazu keine Rechte.";

    @Override
    public void onEnable() {
        //init
        InventoryManager.init();
        ConfigManager.init();
        LocationManager.init();
        prefix = new ConfigManager().getPrefix();
        noPermissions = new ConfigManager().getNoPermissions();

        //register commands
        this.getCommand("build").setExecutor(new BuildCommand());
        this.getCommand("fly").setExecutor(new FlyCommand());
        this.getCommand("setlocation").setExecutor(new SetLocationCommand());

        //register listeners
        Bukkit.getPluginManager().registerEvents(new BuildListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        //world time setter
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> {
            Bukkit.getWorlds().forEach(world -> world.setTime(new LocationManager().getWorldTime()));
        }, 0, 20);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
