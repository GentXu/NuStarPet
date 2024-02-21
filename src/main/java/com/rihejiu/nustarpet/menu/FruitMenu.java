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


public class FruitMenu extends AbstractMenu {
    public FruitMenu(Player player){
        this.owner = player;
        this.size = 9;
        this.title = "喂养果实";
        this.components = Bukkit.createInventory(new MenuHolder(this,"喂养果实"),this.size,this.title);
        build(this.components);
    }
    @Override
    public void build(Inventory components){
        ItemStack glass = MenuItem.menuItem(Material.STAINED_GLASS_PANE,"-",null);
        ItemStack flower = MenuItem.menuItem(Material.DOUBLE_PLANT, Utils.lineColor("&e确定喂养"),null);
        ItemStack sign = MenuItem.menuItem(Material.SIGN,Utils.lineColor("&e玩法介绍"),new ArrayList<>(Collections.singletonList(Utils.lineColor("&f<- 异兽    果实 ->"))));
        for (int i = 0; i < 8; i++) {
            if (i != 2 && i != 4 && i != 6){
                components.setItem(i,glass);
            }
        }
        components.setItem(8,flower);
        components.setItem(4,sign);
    }
}
