Permissions:

xplugins.command.setlocation (/setlocation) - Zum setzen einer Location
xplugins.command.fly (/fly) - Zum Fliegen in der Lobby
xplugins.command.build (/build) - Zum Bauen in der Lobby




Configs:

items.yml: Konfiguriere das Inventar des Navigators und das Item des Navigators.



Beispiel:

---------------------------------------------------------

itemLocation: 4 #NAVIGATOR SLOT IM INVENTAR
itemAction: #ZUM SETZEN EINER TELEPORT AKTION
  '10': #SLOT
    location: location1 #LOCATION NAME
  '13':
    location: location2
  '16':
    location: location3
handItem: #NAMIGATOR ITEM
  ==: org.bukkit.inventory.ItemStack
  type: COMPASS #ITEM
  meta:
    ==: ItemMeta
    meta-type: UNSPECIFIC
    display-name: §a§lMenü   #ITEM NAME
inventory:
  size: 27
  title: §6§lXLobby §8» §a§lNavigator  #INVENTAR TITEL
  slot:
    '0': #SLOT
      ==: org.bukkit.inventory.ItemStack
      type: STAINED_GLASS_PANE #INVENTAR ITEM
      damage: 11 #SUBID (AUCH FÜR CUSTOM MODELS DIE MODEL-ID)
      meta:
        ==: ItemMeta
        meta-type: UNSPECIFIC
        display-name: §b #ITEM NAME

---------------------------------------------------------
