package xyz.plocki.xlobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.plocki.xlobby.XLobby;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("xlobby.command.fly")) {
            Player player = (Player) sender;
            if(player.getAllowFlight()) {
                sender.sendMessage(XLobby.prefix + "Du kannst nun nicht mehr Fliegen.");
                player.setAllowFlight(false);
            } else {
                sender.sendMessage(XLobby.prefix + "Du kannst nun Fliegen.");
                player.setAllowFlight(true);
            }
        } else {
            sender.sendMessage(XLobby.prefix + XLobby.noPermissions);
        }
        return false;
    }

}
