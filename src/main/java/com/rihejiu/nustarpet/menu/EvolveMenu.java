package com.rihejiu.nustarpet.menu;

import com.rihejiu.nustarpet.menu.specification.AbstractMenu;
import com.rihejiu.nustarpet.menu.specification.MenuHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

import java.util.ArrayList;
import java.util.Collections;

public class EvolveMenu extends AbstractMenu {
    public EvolveMenu(Player player){
        this.owner = player;
        this.size = 45;
        this.title = "异兽进化";
        this.components = Bukkit.createInventory(new MenuHolder(this,"异兽进化"),this.size,this.title);
        build(this.components);
    }
    @Override
    public void build(Inventory inventory){
        Dye dye = new Dye();
        DyeColor gray = DyeColor.WHITE;
        DyeColor orange = DyeColor.RED;
        DyeColor green = DyeColor.MAGENTA;
        DyeColor red = DyeColor.ORANGE;
        DyeColor blue = DyeColor.YELLOW;
        ItemStack glass = MenuItem.menuItem(Material.STAINED_GLASS_PANE,"-",null);
        for (int i = 0; i < 45; i++) {
            if (i != 13 && i != 17 && i != 29 && i != 30 && i != 31 && i != 32 && i != 33){
                inventory.setItem(i,glass);
            }
        }
        inventory.setItem(17,MenuItem.menuItem(Material.SIGN,ChatColor.YELLOW + "玩法介绍",new ArrayList<>(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', "&f请放入异兽")))));
        inventory.setItem(29,MenuItem.menuItemWithColor(dye,gray,Material.STAINED_GLASS_PANE,ChatColor.YELLOW + "开始随机进化",null));
        inventory.setItem(30,MenuItem.menuItemWithColor(dye,orange,Material.STAINED_GLASS_PANE,ChatColor.YELLOW + "锁定力量进化",null));
        inventory.setItem(31,MenuItem.menuItemWithColor(dye,green,Material.STAINED_GLASS_PANE,ChatColor.YELLOW + "锁定敏捷进化",null));
        inventory.setItem(32,MenuItem.menuItemWithColor(dye,red,Material.STAINED_GLASS_PANE,ChatColor.YELLOW + "锁定体力进化",null));
        inventory.setItem(33,MenuItem.menuItemWithColor(dye,blue,Material.STAINED_GLASS_PANE,ChatColor.YELLOW + "锁定智慧进化",null));
    }
}
