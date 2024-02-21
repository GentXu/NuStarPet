package com.rihejiu.nustarpet.menu.event;

import com.rihejiu.nustarpet.menu.specification.MenuHolder;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * 监听器代理触发
 */
public class Monitor implements Listener {
    @EventHandler
    public void click(InventoryClickEvent event){
        MenuHolder menuHolder = MenuHolder.formInventory(event.getView().getTopInventory());
        if (menuHolder != null){
            Bukkit.getServer().getPluginManager().callEvent(new ClickEvent(event,menuHolder.getMenuType()));
        }
    }
    @EventHandler
    public void close(InventoryCloseEvent event){
        MenuHolder menuHolder = MenuHolder.formInventory(event.getView().getTopInventory());
        if (menuHolder != null){
            Bukkit.getServer().getPluginManager().callEvent(new CloseEvent(event,menuHolder.getMenuType()));
        }
    }
}
