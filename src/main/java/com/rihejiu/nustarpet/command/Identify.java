package com.rihejiu.nustarpet.command;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Identify  {
    public static ItemMeta getPet(Player player, int slot){
        Inventory inv = player.getInventory();
        if (inv.getItem(slot) != null) {
            return inv.getItem(slot).getItemMeta();
        }
        return null;
    }
    public static ItemStack getPetStack(Player player, int slot){
        Inventory inv = player.getInventory();
        return inv.getItem(slot);
    }
}
