package com.rihejiu.nustarpet.menu.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

/**
 * Close代理事件
 */
public class CloseEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final String menuType;
    private final Player player;

    public InventoryView getView() {
        return view;
    }

    private final InventoryView view;

    public String getMenuType() {
        return menuType;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack[] getContents() {
        return contents;
    }

    private final ItemStack[] contents;
    public CloseEvent(InventoryCloseEvent origin,String menuType){
        this.menuType = menuType;
        this.contents = origin.getView().getTopInventory().getContents();
        this.player = (Player) origin.getPlayer();
        this.view = origin.getView();
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
