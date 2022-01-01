package xyz.plocki.xlobby.utils.inventory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class ItemBuilder {
    private final String displayName;
    private Material material;
    private final List<String> lore = new ArrayList<>();
    private int amount = 1;
    boolean enchanted = false;
    private ItemStack item;

    public ItemBuilder(final String displayName, ItemStack item, final String lore) {
        this.displayName = displayName;
        this.item = item;
        this.lore.add(lore);
    }

    public ItemBuilder(final String displayName, final Material material, final String lore) {
        this.displayName = displayName;
        this.material = material;
        this.lore.add(lore);
    }

    public ItemBuilder setAmount(int count) {
        amount = count;
        return this;
    }

    public ItemBuilder setEnchanted(boolean enabled) {
        enchanted = enabled;
        return this;
    }

    public ItemStack buildItem() {
        ItemStack itemstack;
        if(item == null) {
            itemstack = new ItemStack(this.material);
        } else {
            itemstack = item;
        }
        final ItemMeta itemMeta = itemstack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        if(enchanted) {
            itemMeta.addEnchant(Enchantment.DURABILITY,0,true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemstack.setItemMeta(itemMeta);
        itemstack.setAmount(amount);
        return itemstack;
    }

}
