package com.rihejiu.nustarpet.menu;

import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.menu.specification.AbstractMenu;
import com.rihejiu.nustarpet.menu.specification.MenuHolder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ImmolateMenu extends AbstractMenu {
    public ImmolateMenu(Player player){
        this.owner = player;
        this.size = 9;
        this.title = "异兽献祭";
        this.components = Bukkit.createInventory(new MenuHolder(this,"异兽献祭"),this.size,this.title);
        build(this.components);
    }
    @Override
    public void build(Inventory components){
        ItemStack glass = MenuItem.menuItem(Material.STAINED_GLASS_PANE,"-",null);
        ItemStack flower = MenuItem.menuItem(Material.DOUBLE_PLANT, Utils.lineColor("&e确认献祭"),null);
        for (int i = 0; i < 8; i++) {
            if (i != 4){
                components.setItem(i,glass);
            }
        }
        components.setItem(8,flower);
    }
}
