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

public class TalentMenu {
    public Inventory components;
    public Player owner;
    public static final String TITLE = "异兽天赋";
    public TalentMenu(Player player){
        components = Bukkit.createInventory(player,9,TITLE);
        owner = player;
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("-");
        glass.setItemMeta(glassMeta);
        ItemStack flower = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta flowerMeta = flower.getItemMeta();
        flowerMeta.setDisplayName(ChatColor.YELLOW + "开始培养");
        flower.setItemMeta(flowerMeta);
        ItemStack sign = new ItemStack(Material.SIGN);
        ItemMeta signMeta = sign.getItemMeta();
        signMeta.setDisplayName(ChatColor.YELLOW + "玩法介绍");
        List<String> lore = new ArrayList<>();
        lore.add(Utils.msgColor("&f放入宠物后点我获得介绍"));
        signMeta.setLore(lore);
        sign.setItemMeta(signMeta);
        // 菜单绘制
        for (int i = 0; i < 8; i++) {
            if (i != 4 && i != 7){
                components.setItem(i,glass);
            }

        }
        components.setItem(8,flower);
        components.setItem(7,sign);
    }
    // 打开该菜单的方法
    public void open(){
        owner.openInventory(components);
    }
}
