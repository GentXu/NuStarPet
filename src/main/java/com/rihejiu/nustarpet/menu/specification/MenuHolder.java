package com.rihejiu.nustarpet.menu.specification;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * 自定义InventoryHolder，用于判断菜单是否是本插件的
 */
public class MenuHolder implements InventoryHolder {
    private final Menu menu;

    public String getMenuType() {
        return menuType;
    }

    private final String menuType;
    public MenuHolder(Menu menu,String menuType){
        this.menu = menu;
        this.menuType = menuType;
    }
    public static MenuHolder formInventory(Inventory inventory){
        InventoryHolder holder = inventory.getHolder();
        if (holder instanceof MenuHolder){
            return (MenuHolder) holder;
        }
        return null;
    }
    @Override
    public Inventory getInventory() {
        return menu.toBukkitInventory();
    }
}
