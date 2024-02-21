package com.rihejiu.nustarpet.menu;

import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.menu.specification.AbstractMenu;
import com.rihejiu.nustarpet.menu.specification.MenuHolder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;

public class TalentMenu extends AbstractMenu {
    public TalentMenu(Player player){
        this.owner = player;
        this.size = 9;
        this.title = "异兽天赋";
        this.components = Bukkit.createInventory(new MenuHolder(this,"异兽天赋"),this.size,this.title);
        build(this.components);
    }
    @Override
    public void build(Inventory components){
        ItemStack glass = MenuItem.menuItem(Material.STAINED_GLASS_PANE,"-",null);
        ItemStack flower = MenuItem.menuItem(Material.DOUBLE_PLANT,Utils.lineColor("&e开始培养"),null);
        ItemStack sign = MenuItem.menuItem(Material.SIGN,Utils.lineColor("&e玩法介绍"),new ArrayList<>(Collections.singleton(Utils.lineColor("&f放入宠物后点我获得介绍"))));
        for (int i = 0; i < 8; i++) {
            if (i != 4 && i != 7){
                components.setItem(i,glass);
            }

        }
        components.setItem(8,flower);
        components.setItem(7,sign);
    }
}
