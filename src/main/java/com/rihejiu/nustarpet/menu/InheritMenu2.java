package com.rihejiu.nustarpet.menu;

import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.command.Utils;
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

public class InheritMenu2 {
    public Inventory components;
    public Player owner;
    public static final String TITLE = "确认传承";
    public InheritMenu2(Player player,ItemStack inheritpet,ItemStack inheritedpet){
        Dye dye = new Dye();
        components = Bukkit.createInventory(player,45,TITLE);
        owner = player;
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("-");
        glass.setItemMeta(glassMeta);
        ItemStack sign = new ItemStack(Material.SIGN);
        ItemMeta signMeta = sign.getItemMeta();
        signMeta.setDisplayName(ChatColor.YELLOW + "玩法介绍");
        List<String> lore = new ArrayList<>();
        String inheritpetname = inheritpet.getItemMeta().getDisplayName();
        String inheritpetpower = NBTUtils.getStringTag(inheritpet,"力量");
        String inheritpetspeed = NBTUtils.getStringTag(inheritpet,"敏捷");
        String inheritpetspirit = NBTUtils.getStringTag(inheritpet,"体力");
        String inheritpetwisdom = NBTUtils.getStringTag(inheritpet,"智慧");
        String inheritedpetname = inheritedpet.getItemMeta().getDisplayName();
        lore.add(Utils.msgColor("&f请选择即将传承成长属性"));
        lore.add(Utils.msgColor("&f被传异兽: " + inheritedpetname));
        lore.add(Utils.msgColor("&f传承异兽: " + inheritpetname));
        lore.add(Utils.msgColor("&f克隆果实: " + Utils.checkKelongguoshi(player) + "/" + "1"));
        lore.add(Utils.msgColor("&f青涩果实: " + Utils.checkQingseguoshi(player) + "/" + "5"));
        signMeta.setLore(lore);
        sign.setItemMeta(signMeta);

        DyeColor orange = DyeColor.RED;
        dye.setColor(orange);
        ItemStack powerlocked = dye.toItemStack(1);
        powerlocked.setType(Material.STAINED_GLASS_PANE);
        ItemMeta powerlockedMeta = powerlocked.getItemMeta();
        powerlockedMeta.setDisplayName(ChatColor.YELLOW + "传承属性: " + "力量[" + inheritpetpower + "]");
        powerlocked.setItemMeta(powerlockedMeta);

        DyeColor green = DyeColor.MAGENTA;
        dye.setColor(green);
        ItemStack speedlocked = dye.toItemStack(1);
        speedlocked.setType(Material.STAINED_GLASS_PANE);
        ItemMeta speedlockedMeta = speedlocked.getItemMeta();
        speedlockedMeta.setDisplayName(ChatColor.YELLOW + "传承属性: " + "敏捷[" + inheritpetspeed + "]");
        speedlocked.setItemMeta(speedlockedMeta);

        DyeColor red = DyeColor.ORANGE;
        dye.setColor(red);
        ItemStack spiritlocked = dye.toItemStack(1);
        spiritlocked.setType(Material.STAINED_GLASS_PANE);
        ItemMeta spiritlockedMeta = spiritlocked.getItemMeta();
        spiritlockedMeta.setDisplayName(ChatColor.YELLOW + "传承属性: " + "智慧[" + inheritpetwisdom + "]");
        spiritlocked.setItemMeta(spiritlockedMeta);

        DyeColor blue = DyeColor.YELLOW;
        dye.setColor(blue);
        ItemStack wisdomlocked = dye.toItemStack(1);
        wisdomlocked.setType(Material.STAINED_GLASS_PANE);
        ItemMeta wisdomlockedMeta = wisdomlocked.getItemMeta();
        wisdomlockedMeta.setDisplayName(ChatColor.YELLOW + "传承属性: " + "体力[" + inheritpetspirit + "]");
        wisdomlocked.setItemMeta(wisdomlockedMeta);

        // 菜单绘制
        for (int i = 0; i < 45; i++) {
            if (i != 11 && i != 13 && i != 15 && i != 29 && i != 30 && i != 32 && i != 33){
                components.setItem(i,glass);
            }

        }
        components.setItem(11,inheritedpet);
        components.setItem(15,inheritpet);
        components.setItem(13,sign);
        components.setItem(29,powerlocked);
        components.setItem(30,speedlocked);
        components.setItem(32,wisdomlocked);
        components.setItem(33,spiritlocked);
    }
    // 打开该菜单的方法
    public void open(){
        owner.openInventory(components);
    }
}
