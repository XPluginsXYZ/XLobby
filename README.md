Permissions:</br>
</br>
xplugins.command.setlocation (/setlocation) - Zum setzen einer Location</br>
xplugins.command.fly (/fly) - Zum Fliegen in der Lobby</br>
xplugins.command.build (/build) - Zum Bauen in der Lobby</br>
</br>
</br>
</br>
</br>
Configs:</br>
</br>
items.yml: Konfiguriere das Inventar des Navigators und das Item des Navigators.</br>
</br>
</br>
</br>
Beispiel:</br>
</br>
---------------------------------------------------------</br>
</br>
itemLocation: 4 #NAVIGATOR SLOT IM INVENTAR</br>
itemAction: #ZUM SETZEN EINER TELEPORT AKTION</br>
  '10': #SLOT</br>
    location: location1 #LOCATION NAME</br>
  '13':</br>
    location: location2</br>
  '16':</br>
    location: location3</br>
handItem: #NAMIGATOR ITEM</br>
  ==: org.bukkit.inventory.ItemStack</br>
  type: COMPASS #ITEM</br>
  meta:</br>
    ==: ItemMeta</br>
    meta-type: UNSPECIFIC</br>
    display-name: §a§lMenü   #ITEM NAME</br>
inventory:</br>
  size: 27</br>
  title: §6§lXLobby §8» §a§lNavigator  #INVENTAR TITEL</br>
  slot:</br>
    '0': #SLOT</br>
      ==: org.bukkit.inventory.ItemStack</br>
      type: STAINED_GLASS_PANE #INVENTAR ITEM</br>
      damage: 11 #SUBID (AUCH FÜR CUSTOM MODELS DIE MODEL-ID)</br>
      meta:</br>
        ==: ItemMeta</br>
        meta-type: UNSPECIFIC</br>
        display-name: §b #ITEM NAME</br>
</br>
---------------------------------------------------------</br>




Locations.yml: Speichert die Locations


Config.yml: Editiere den Prefix und die NoPermissions Nachricht
