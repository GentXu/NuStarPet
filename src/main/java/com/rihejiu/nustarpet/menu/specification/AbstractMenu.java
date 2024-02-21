package com.rihejiu.nustarpet.menu.specification;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * 菜单抽象类，具有Menu接口的行为和菜单的特征
 */
public abstract class AbstractMenu implements Menu {
    protected Inventory components;
    protected Player owner;



    protected String title;
    protected int size;
    @Override
    public void open(){
        owner.openInventory(components);
    }
    @Override
    public Inventory toBukkitInventory(){
        if (components != null) return components;
        return null;
    }
}
