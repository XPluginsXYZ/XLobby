package xyz.plocki.xlobby.utils.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.plocki.xlobby.XLobby;
import xyz.plocki.xlobby.utils.VersionUtil;
import xyz.plocki.xlobby.utils.files.FileBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class InventoryManager {

    private static YamlConfiguration yamlConfiguration;

    public static void init() {
        FileBuilder fileBuilder = new FileBuilder("plugins/XLobby/items.yml");
        yamlConfiguration = fileBuilder.getYaml();
        if(!yamlConfiguration.isSet("itemLocation")) {
            yamlConfiguration.set("itemLocation", 4);
        }
        if(!yamlConfiguration.isSet("handItem")) {
            yamlConfiguration.set("itemAction.0.location", "exampleLocation");
            yamlConfiguration.set("handItem", new ItemBuilder("§a§lMenü", Material.COMPASS, "default\nlore").buildItem());
        }
        if(!yamlConfiguration.isSet("cosmeticItem")) {
            ItemStack item;
            if(VersionUtil.is1_13()) {
                item = new ItemBuilder("§7", Material.BLACK_STAINED_GLASS_PANE, "default\nlore").buildItem();
            } else {
                item = new ItemBuilder("§7", new ItemStack(Material.valueOf("STAINED_GLASS_PANE"), 1, (short) 11), "default\nlore").buildItem();
            }
            yamlConfiguration.set("cosmeticInventoryPane", item);
            yamlConfiguration.set("cosmeticInventoryName", XLobby.prefix + "Gadgets");
            yamlConfiguration.set("cosmeticItem", new ItemBuilder("§6§lGadgets", Material.CHEST, "default\nlore").buildItem());
            yamlConfiguration.set("cosmeticItemLocation", 6);
        }
        if(!yamlConfiguration.isSet("inventory")) {
            Inventory inventory = Bukkit.createInventory(null, 3*9, XLobby.prefix + "Default Inventory");
            ItemStack itemStack;
            if(VersionUtil.is1_13()) {
                itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            } else  {
                itemStack = new ItemStack(Material.valueOf("STAINED_GLASS_PANE"), 1, (short) 11);
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§bDefaultItem (all versions)");
            itemMeta.setLore(new ArrayList<>(Arrays.asList("lore1", "lore2")));
            itemMeta.addEnchant(Enchantment.DURABILITY, 10, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            itemStack.setItemMeta(itemMeta);
            for(int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, itemStack);
            }
            if(VersionUtil.is1_13()) {
                inventory.setItem(0, new ItemBuilder("§bExampleHead (from value)", PlayerHead.getItemStackWithTexture("PLACE VALUE FROM https://minecraft-heads.com/custom-heads/ HERE"), "your\ncustom\nlore").setEnchanted(true).buildItem());
            }
            yamlConfiguration.set("inventory", "CONTROL");
            yamlConfiguration.set("inventory.size", inventory.getSize());
            yamlConfiguration.set("inventory.title", inventory.getTitle());
            for(int i = 0; i < inventory.getSize(); i++) {
                yamlConfiguration.set("inventory.slot." + i, inventory.getItem(i));
            }
            fileBuilder.save();
        }
    }

    public Location getItemLocation(int itemLocation) {
        String locationString = yamlConfiguration.getString("itemAction." + itemLocation + ".location");
        if(locationString == null) {
            return null;
        } else {
            return new LocationManager().getLocation(locationString);
        }
    }

    public int getInventoryItemLocation() {
        return yamlConfiguration.getInt("itemLocation");
    }

    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, yamlConfiguration.getInt("inventory.size"), yamlConfiguration.getString("inventory.title"));
        for(int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, yamlConfiguration.getItemStack("inventory.slot." + i));
        }
        return inventory;
    }

    public ItemStack getInventoryItem() {
        return yamlConfiguration.getItemStack("handItem");
    }

    public ItemStack getCosmeticInventoryPane() {
        return yamlConfiguration.getItemStack("cosmeticInventoryPane");
    }

    public ItemStack getCosmeticItem() {
        return yamlConfiguration.getItemStack("cosmeticItem");
    }

    public int getCosmeticItemLocation() {
        return yamlConfiguration.getInt("cosmeticItemLocation");
    }

    public String getCosmeticInventoryTitle() {
        return yamlConfiguration.getString("cosmeticInventoryName");
    }

}
