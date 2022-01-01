package xyz.plocki.xlobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.plocki.xlobby.XLobby;
import xyz.plocki.xlobby.utils.inventory.LocationManager;

public class SetLocationCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
          Player player = (Player) sender;
          if(player.hasPermission("xlobby.command.setlocation")) {
              if(!(args.length == 1)) {
                  player.sendMessage(XLobby.prefix + "Du musst einen Namen für die Location angeben!");
              } else {
                  player.sendMessage(XLobby.prefix + "Du hast die Location " + args[0] + " gesetzt.");
                  new LocationManager().saveLocation(args[0], player.getLocation());
              }
          } else {
              player.sendMessage(XLobby.prefix + XLobby.noPermissions);
          }
        return false;
    }

}
