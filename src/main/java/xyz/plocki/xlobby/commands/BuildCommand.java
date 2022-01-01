package xyz.plocki.xlobby.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.plocki.xlobby.XLobby;
import xyz.plocki.xlobby.utils.inventory.InventoryManager;

import java.util.ArrayList;
import java.util.List;

public class BuildCommand implements CommandExecutor {

    public static final List<Player> players = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(player.hasPermission("xlobby.command.build")) {
            if(players.contains(player)) {
                player.getInventory().clear();
                player.setGameMode(GameMode.SURVIVAL);
                players.remove(player);
                player.sendMessage(XLobby.prefix + "Du kannst nun nicht mehr bauen.");
                player.getInventory().setItem(new InventoryManager().getInventoryItemLocation(), new InventoryManager().getInventoryItem());
            } else {
                player.getInventory().clear();
                player.setGameMode(GameMode.CREATIVE);
                players.add(player);
                player.sendMessage(XLobby.prefix + "Du kannst nun bauen.");
            }
        } else {
            sender.sendMessage(XLobby.prefix + XLobby.noPermissions);
        }
        return false;
    }

}
