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

public class FruitMakeMenu extends AbstractMenu {
    public FruitMakeMenu(Player player){
        this.owner = player;
        this.size = 9;
        this.title = "附魔果实";
        this.components = Bukkit.createInventory(new MenuHolder(this,"附魔果实"),this.size,this.title);
        build(this.components);
    }
    @Override
    public void build(Inventory components){
        ItemStack glass = MenuItem.menuItem(Material.STAINED_GLASS_PANE,"-",null);
        ItemStack sign = MenuItem.menuItem(Material.SIGN, Utils.lineColor("&e玩法介绍"),signLore());
        ItemStack flower = MenuItem.menuItem(Material.DOUBLE_PLANT,Utils.lineColor("&e附魔果实"),null);
        for (int i = 0;i < 8;i++){
            if (i != 4 && i != 7){
                components.setItem(i,glass);
            }
        }
        components.setItem(7,sign);
        components.setItem(8,flower);
    }
    public List<String> signLore(){
        List<String> signLore = new ArrayList<>();
        signLore.add(Utils.lineColor("&f将放入青涩果实,自动消耗背包内的&e创造宝石"));
        signLore.add(Utils.lineColor("&f随机合成四维中的一种果实,果实只可给异兽服用"));
        return signLore;
    }
}
