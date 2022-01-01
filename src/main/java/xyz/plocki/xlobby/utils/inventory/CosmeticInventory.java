package xyz.plocki.xlobby.utils.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.plocki.xlobby.utils.VersionUtil;

import java.util.HashMap;
import java.util.Map;

public class CosmeticInventory {

    public static Map<Player, Boolean> doubleJump = new HashMap<>();
    public static Map<Player, Boolean> lavaBoots = new HashMap<>();
    public static Map<Player, Boolean> waterBoots = new HashMap<>();
    public static Map<Player, Boolean> fly = new HashMap<>();

    public Inventory getCosmeticInventory() {
        Inventory inventory = Bukkit.createInventory(null, 3*9, new InventoryManager().getCosmeticInventoryTitle());
        ItemStack pane = new InventoryManager().getCosmeticInventoryPane();
        for(int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, pane);
        }
        inventory.setItem(10, new ItemBuilder("§aDoppelsprung", new ItemStack(Material.LEATHER_BOOTS), "").buildItem());
        inventory.setItem(12, new ItemBuilder("§cLava Schuhe", new ItemStack(Material.GOLDEN_BOOTS), "").buildItem());
        inventory.setItem(14, new ItemBuilder("§bWasser Schuhe", new ItemStack(Material.DIAMOND_BOOTS), "").buildItem());
        inventory.setItem(16, new ItemBuilder("§fFliegen", new ItemStack(Material.FEATHER), "").buildItem());
        return inventory;
    }

}
