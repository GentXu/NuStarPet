package com.rihejiu.nustarpet.menu;

import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.menu.specification.AbstractMenu;
import com.rihejiu.nustarpet.menu.specification.MenuHolder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LevelUpMenu extends AbstractMenu {
    public LevelUpMenu(Player player){
        this.owner = player;
        this.size = 54;
        this.title = "异兽升级";
        this.components = Bukkit.createInventory(new MenuHolder(this,"异兽升级"),this.size,this.title);
        build(this.components);
    }
    @Override
    public void build(Inventory components){
        ItemStack glass = MenuItem.menuItem(Material.STAINED_GLASS_PANE,"-",null);
        ItemStack flower = MenuItem.menuItem(Material.DOUBLE_PLANT, Utils.lineColor("&e确认喂养"),null);
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
}
