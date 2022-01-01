package xyz.plocki.xlobby.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import xyz.plocki.xlobby.commands.BuildCommand;
import xyz.plocki.xlobby.utils.inventory.InventoryManager;

public class BuildListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        event.setCancelled(!BuildCommand.players.contains(event.getPlayer()));
    }

    @EventHandler
    public void onInteractAtBlock(PlayerInteractEvent event) {
        if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            event.setCancelled(!BuildCommand.players.contains(event.getPlayer()));
        }
        if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(new InventoryManager().getInventoryItem().getItemMeta().getDisplayName())) {
            event.getPlayer().openInventory(new InventoryManager().getInventory());
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        event.setCancelled(!BuildCommand.players.contains(event.getPlayer()));
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        event.setCancelled(!BuildCommand.players.contains(event.getPlayer()));
    }

}
