package xyz.plocki.xlobby.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
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
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import xyz.plocki.xlobby.XLobby;
import xyz.plocki.xlobby.commands.BuildCommand;
import xyz.plocki.xlobby.utils.inventory.CosmeticInventory;
import xyz.plocki.xlobby.utils.inventory.InventoryManager;
import xyz.plocki.xlobby.utils.inventory.LocationManager;

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
        CosmeticInventory.waterBoots.put(event.getPlayer(), false);
        CosmeticInventory.fly.put(event.getPlayer(), false);
        CosmeticInventory.lavaBoots.put(event.getPlayer(), false);
        CosmeticInventory.doubleJump.put(event.getPlayer(), false);
    }

    @EventHandler
    public void onJump(PlayerToggleFlightEvent event) {
        if(!event.isFlying() || !event.getPlayer().getGameMode().equals(GameMode.SPECTATOR) || !event.getPlayer().getGameMode().equals(GameMode.CREATIVE) || !event.getPlayer().getAllowFlight()) {
            if(CosmeticInventory.fly.getOrDefault(event.getPlayer(), false)) {
                event.getPlayer().setVelocity(event.getPlayer().getLocation().clone().getDirection().multiply(1.6d).setY(1.0d));
            }
        }
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
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(new InventoryManager().getCosmeticItem().getItemMeta().getDisplayName())) {
                event.getWhoClicked().openInventory(new CosmeticInventory().getCosmeticInventory());
            }
            if(event.getInventory().getTitle().equalsIgnoreCase(new InventoryManager().getInventory().getTitle())) {
                if(new InventoryManager().getItemLocation(event.getSlot()) != null) {
                    event.getWhoClicked().teleport(new InventoryManager().getItemLocation(event.getSlot()));
                }
            } else if(event.getClickedInventory().getTitle().equalsIgnoreCase(new InventoryManager().getCosmeticInventoryTitle())) {
                if(event.getCurrentItem().getType().equals(Material.LEATHER_BOOTS)) {
                    if(event.getWhoClicked().hasPermission("xlobby.cosmetic.doublejump")) {
                        event.getWhoClicked().closeInventory();
                        if(CosmeticInventory.doubleJump.getOrDefault(((Player) event.getWhoClicked()).getPlayer(), false)) {
                            CosmeticInventory.doubleJump.put(((Player) event.getWhoClicked()).getPlayer(), false);
                            event.getWhoClicked().sendMessage(XLobby.prefix + "Doppelsprung wurde deaktiviert.");
                        } else {
                            CosmeticInventory.doubleJump.put(((Player) event.getWhoClicked()).getPlayer(), true);
                            event.getWhoClicked().sendMessage(XLobby.prefix + "Doppelsprung wurde aktiviert.");
                        }
                    } else {
                        event.getWhoClicked().sendMessage(XLobby.prefix + XLobby.noPermissions);
                    }
                } else if(event.getCurrentItem().getType().equals(Material.GOLDEN_BOOTS)) {
                    if(event.getWhoClicked().hasPermission("xlobby.cosmetic.lavaboots")) {
                        event.getWhoClicked().closeInventory();
                        if(CosmeticInventory.lavaBoots.getOrDefault(((Player) event.getWhoClicked()).getPlayer(), false)) {
                            CosmeticInventory.lavaBoots.put(((Player) event.getWhoClicked()).getPlayer(), false);
                            event.getWhoClicked().sendMessage(XLobby.prefix + "Lava Schuhe wurden deaktiviert.");
                        } else {
                            CosmeticInventory.lavaBoots.put(((Player) event.getWhoClicked()).getPlayer(), true);
                            event.getWhoClicked().sendMessage(XLobby.prefix + "Lava Schuhe wurden aktiviert.");
                            CosmeticInventory.waterBoots.put(((Player) event.getWhoClicked()).getPlayer(), false);
                        }
                    } else {
                        event.getWhoClicked().sendMessage(XLobby.prefix + XLobby.noPermissions);
                    }
                } else if(event.getCurrentItem().getType().equals(Material.DIAMOND_BOOTS)) {
                    if(event.getWhoClicked().hasPermission("xlobby.cosmetic.waterboots")) {
                        event.getWhoClicked().closeInventory();
                        if(CosmeticInventory.waterBoots.getOrDefault(((Player) event.getWhoClicked()).getPlayer(), false)) {
                            CosmeticInventory.waterBoots.put(((Player) event.getWhoClicked()).getPlayer(), false);
                            event.getWhoClicked().sendMessage(XLobby.prefix + "Wasser Schuhe wurden deaktiviert.");
                        } else {
                            CosmeticInventory.waterBoots.put(((Player) event.getWhoClicked()).getPlayer(), true);
                            event.getWhoClicked().sendMessage(XLobby.prefix + "Wasser Schuhe wurden aktiviert.");
                            CosmeticInventory.lavaBoots.put(((Player) event.getWhoClicked()).getPlayer(), false);
                        }
                    } else {
                        event.getWhoClicked().sendMessage(XLobby.prefix + XLobby.noPermissions);
                    }
                } else if(event.getCurrentItem().getType().equals(Material.FEATHER)) {
                    if(event.getWhoClicked().hasPermission("xlobby.cosmetic.fly")) {
                        event.getWhoClicked().closeInventory();
                        if(CosmeticInventory.fly.getOrDefault(((Player) event.getWhoClicked()).getPlayer(), false)) {
                            CosmeticInventory.fly.put(((Player) event.getWhoClicked()).getPlayer(), false);
                            event.getWhoClicked().sendMessage(XLobby.prefix + "Fly wurde deaktiviert.");
                            ((Player) event.getWhoClicked()).setAllowFlight(false);
                        } else {
                            CosmeticInventory.fly.put(((Player) event.getWhoClicked()).getPlayer(), true);
                            event.getWhoClicked().sendMessage(XLobby.prefix + "Fly wurde aktiviert.");
                            ((Player) event.getWhoClicked()).setAllowFlight(true);
                        }
                    } else {
                        event.getWhoClicked().sendMessage(XLobby.prefix + XLobby.noPermissions);
                    }
                }
            }
        }
    }

}
