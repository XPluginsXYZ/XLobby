package xyz.plocki.xlobby.utils.inventory;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import xyz.plocki.xlobby.utils.VersionUtil;

import java.lang.reflect.Field;
import java.util.UUID;

@SuppressWarnings("unused")
public class PlayerHead {

    public static ItemStack getItemStackWithTexture(String long_key) {
        ItemStack head;
        if(VersionUtil.is1_13()) {
            head = new ItemStack(Material.PLAYER_HEAD);
        } else {
            head = new ItemStack(Material.valueOf("SKULL"));
        }
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", long_key));
        Field profileField;
        try {
            assert meta != null;
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        head.setItemMeta(meta);
        return head;
    }

}
