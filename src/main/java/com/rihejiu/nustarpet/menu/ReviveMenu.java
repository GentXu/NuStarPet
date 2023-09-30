package com.rihejiu.nustarpet.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ReviveMenu {
    public Inventory components;
    public Player owner;
    public static final String TITLE = "异兽复活";
    public ReviveMenu(Player player){
        components = Bukkit.createInventory(player,9,TITLE);
        owner = player;
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("-");
        glass.setItemMeta(glassMeta);
        ItemStack flower = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta flowerMeta = flower.getItemMeta();
        flowerMeta.setDisplayName(ChatColor.YELLOW + "确认复活");
        flower.setItemMeta(flowerMeta);
        // 菜单绘制
        for (int i = 0; i < 8; i++) {
            if (i != 4){
                components.setItem(i,glass);
            }

        }
        components.setItem(8,flower);
    }
    // 打开该菜单的方法
    public void open(){
        owner.openInventory(components);
    }
}
