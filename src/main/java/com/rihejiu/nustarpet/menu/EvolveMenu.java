package com.rihejiu.nustarpet.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

import java.util.ArrayList;
import java.util.List;

public class EvolveMenu {
    public Inventory components;
    public Player owner;
    public static final String TITLE = "异兽进化";
    public EvolveMenu(Player player){
        Dye dye = new Dye();
        components = Bukkit.createInventory(player,45,TITLE);
        owner = player;
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("-");
        glass.setItemMeta(glassMeta);

        DyeColor gray = DyeColor.WHITE;
        dye.setColor(gray);
        ItemStack unlocked = dye.toItemStack(1);
        unlocked.setType(Material.STAINED_GLASS_PANE);
        ItemMeta unlockedMeta = unlocked.getItemMeta();
        unlockedMeta.setDisplayName(ChatColor.YELLOW + "开始随机进化");
        unlocked.setItemMeta(unlockedMeta);

        DyeColor orange = DyeColor.RED;
        dye.setColor(orange);
        ItemStack powerlocked = dye.toItemStack(1);
        powerlocked.setType(Material.STAINED_GLASS_PANE);
        ItemMeta powerlockedMeta = powerlocked.getItemMeta();
        powerlockedMeta.setDisplayName(ChatColor.YELLOW + "锁定力量进化");
        powerlocked.setItemMeta(powerlockedMeta);

        DyeColor green = DyeColor.MAGENTA;
        dye.setColor(green);
        ItemStack speedlocked = dye.toItemStack(1);
        speedlocked.setType(Material.STAINED_GLASS_PANE);
        ItemMeta speedlockedMeta = speedlocked.getItemMeta();
        speedlockedMeta.setDisplayName(ChatColor.YELLOW + "锁定敏捷进化");
        speedlocked.setItemMeta(speedlockedMeta);

        DyeColor red = DyeColor.ORANGE;
        dye.setColor(red);
        ItemStack spiritlocked = dye.toItemStack(1);
        spiritlocked.setType(Material.STAINED_GLASS_PANE);
        ItemMeta spiritlockedMeta = spiritlocked.getItemMeta();
        spiritlockedMeta.setDisplayName(ChatColor.YELLOW + "锁定体力进化");
        spiritlocked.setItemMeta(spiritlockedMeta);

        DyeColor blue = DyeColor.YELLOW;
        dye.setColor(blue);
        ItemStack wisdomlocked = dye.toItemStack(1);
        wisdomlocked.setType(Material.STAINED_GLASS_PANE);
        ItemMeta wisdomlockedMeta = wisdomlocked.getItemMeta();
        wisdomlockedMeta.setDisplayName(ChatColor.YELLOW + "锁定智慧进化");
        wisdomlocked.setItemMeta(wisdomlockedMeta);

        ItemStack flower = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta flowerMeta = flower.getItemMeta();
        flowerMeta.setDisplayName(ChatColor.YELLOW + "确认复活");
        flower.setItemMeta(flowerMeta);

        ItemStack sign = new ItemStack(Material.SIGN);
        ItemMeta signMeta = sign.getItemMeta();
        signMeta.setDisplayName(ChatColor.YELLOW + "玩法介绍");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&',"&f请放入异兽"));
        signMeta.setLore(lore);
        sign.setItemMeta(signMeta);
        // 菜单绘制
        for (int i = 0; i < 45; i++) {
            if (i != 13 && i != 17 && i != 29 && i != 30 && i != 31 && i != 32 && i != 33){
                components.setItem(i,glass);
            }
        }
        components.setItem(17,sign);
        components.setItem(29,unlocked);
        components.setItem(30,powerlocked);
        components.setItem(31,speedlocked);
        components.setItem(32,spiritlocked);
        components.setItem(33,wisdomlocked);
    }
    // 打开该菜单的方法
    public void open(){
        owner.openInventory(components);
    }
}
