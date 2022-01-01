package xyz.plocki.xlobby.utils;

import org.bukkit.Bukkit;

public class VersionUtil {

    public static boolean is1_13() {
        String version = Bukkit.getServer().getClass().getPackage().getName();
        return !version.contains("1_7") && !version.contains("1_8") && !version.contains("1_9") && !version.contains("1_10") && !version.contains("1_11") && !version.contains("1_12");
    }

}
