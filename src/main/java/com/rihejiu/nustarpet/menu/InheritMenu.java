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
import java.util.List;

public class InheritMenu extends AbstractMenu {
    public InheritMenu(Player player){
        this.owner = player;
        this.size = 45;
        this.title = "异兽传承";
        this.components = Bukkit.createInventory(new MenuHolder(this,"异兽传承"),this.size,this.title);
        build(this.components);
    }
    @Override
    public void build(Inventory components){
        ItemStack glass = MenuItem.menuItem(Material.STAINED_GLASS_PANE,"-",null);
        ItemStack flower = MenuItem.menuItem(Material.DOUBLE_PLANT, Utils.lineColor("&e确认传承"),null);
        ItemStack sign = MenuItem.menuItem(Material.SIGN,Utils.lineColor("&e玩法介绍"),signLore());
        for (int i = 0; i < 45; i++) {
            if (i != 11 && i != 13 && i != 15 && i != 31){
                components.setItem(i,glass);
            }
        }
        components.setItem(13,sign);
        components.setItem(31,flower);
    }
    public List<String> signLore(){
        List<String> lore = new ArrayList<>();
        lore.add(Utils.lineColor("&f<-被传承异兽              传承异兽->"));
        lore.add(Utils.lineColor("&f将异兽指定的一条属性成长传承给另一只异兽"));
        lore.add(Utils.lineColor("&f，如指定属性成长为SS且被传承异兽存在一条"));
        lore.add(Utils.lineColor("&f不同的SS属性，则两条SS属性全部保留！"));
        return lore;
    }
}
