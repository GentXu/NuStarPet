package com.rihejiu.nustarpet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu {
    public Inventory components;
    public Player owner;
    public static final String TITLE = "异兽升级";
    public Menu(Player player){
        components = Bukkit.createInventory(player,54,TITLE);
        owner = player;
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("-");
        glass.setItemMeta(glassMeta);
        ItemStack flower = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta flowerMeta = flower.getItemMeta();
        flowerMeta.setDisplayName(ChatColor.YELLOW + "确认喂养");
        flower.setItemMeta(flowerMeta);
        // 菜单绘制
        for (int i = 0; i < 10; i++) {
            components.setItem(i,glass);
        }
        components.setItem(17,glass);
        components.setItem(18,glass);
        components.setItem(26,glass);
        components.setItem(27,glass);
        components.setItem(35,glass);
        components.setItem(36,glass);
        components.setItem(44,glass);
        for (int i = 45; i < 54 ; i++) {
            if (i == 49){
                components.setItem(49,flower);
                continue;
            }
            components.setItem(i,glass);
        }
    }
    // 打开该菜单的方法
    public void open(){
        owner.openInventory(components);
    }
}
