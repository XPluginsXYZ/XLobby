package xyz.plocki.xlobby.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import xyz.plocki.xlobby.XLobby;
import xyz.plocki.xlobby.commands.BuildCommand;
import xyz.plocki.xlobby.utils.InventoryManager;
import xyz.plocki.xlobby.utils.LocationManager;

public class PlayerListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        if(!event.getEntityType().equals(EntityType.ARMOR_STAND) || !event.getEntityType().equals(EntityType.ITEM_FRAME)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        if(new LocationManager().getLocation("spawn") != null) {
            event.getPlayer().teleport(new LocationManager().getLocation("spawn"));
        } else {
            event.getPlayer().sendMessage(XLobby.prefix + "Der Spawn wurde noch nicht gesetzt.");
        }
        event.getPlayer().getInventory().clear();
        event.getPlayer().getInventory().setItem(new InventoryManager().getInventoryItemLocation(), new InventoryManager().getInventoryItem());
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getCurrentItem() != null) {
            event.setCancelled(!BuildCommand.players.contains((Player) event.getWhoClicked()));
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(new InventoryManager().getInventoryItem().getItemMeta().getDisplayName())) {
                event.getWhoClicked().openInventory(new InventoryManager().getInventory());
                return;
            }
            if(event.getInventory().getTitle().equalsIgnoreCase(new InventoryManager().getInventory().getTitle())) {
                if(new InventoryManager().getItemLocation(event.getSlot()) != null) {
                    event.getWhoClicked().teleport(new InventoryManager().getItemLocation(event.getSlot()));
                }
            }
        }
    }

}
