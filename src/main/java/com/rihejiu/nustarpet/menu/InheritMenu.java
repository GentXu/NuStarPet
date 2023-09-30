package com.rihejiu.nustarpet.menu;

import com.rihejiu.nustarpet.command.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InheritMenu {
    public Inventory components;
    public Player owner;
    public static final String TITLE = "异兽传承";
    public InheritMenu(Player player){
        components = Bukkit.createInventory(player,45,TITLE);
        owner = player;
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("-");
        glass.setItemMeta(glassMeta);
        ItemStack flower = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta flowerMeta = flower.getItemMeta();
        flowerMeta.setDisplayName(ChatColor.YELLOW + "确认传承");
        flower.setItemMeta(flowerMeta);
        ItemStack sign = new ItemStack(Material.SIGN);
        ItemMeta signMeta = sign.getItemMeta();
        signMeta.setDisplayName(ChatColor.YELLOW + "玩法介绍");
        List<String> lore = new ArrayList<>();
        lore.add(Utils.msgColor("&f<-被传承异兽              传承异兽->"));
        lore.add(Utils.msgColor("&f将异兽指定的一条属性成长传承给另一只异兽"));
        lore.add(Utils.msgColor("&f，如指定属性成长为SS且被传承异兽存在一条"));
        lore.add(Utils.msgColor("&f不同的SS属性，则两条SS属性全部保留！"));
        signMeta.setLore(lore);
        sign.setItemMeta(signMeta);
        // 菜单绘制
        for (int i = 0; i < 45; i++) {
            if (i != 11 && i != 13 && i != 15 && i != 31){
                components.setItem(i,glass);
            }

        }
        components.setItem(13,sign);
        components.setItem(31,flower);
    }
    // 打开该菜单的方法
    public void open(){
        owner.openInventory(components);
    }
}
