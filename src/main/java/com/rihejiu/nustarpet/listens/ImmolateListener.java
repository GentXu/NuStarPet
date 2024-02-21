package com.rihejiu.nustarpet.listens;

import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.menu.event.ClickEvent;
import com.rihejiu.nustarpet.menu.event.CloseEvent;
import com.rihejiu.nustarpet.pet.utils.ImmolateUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ImmolateListener implements Listener {
    @EventHandler
    public void click(ClickEvent event){
        if (event.getMenuType().equals("异兽献祭")){
            int rawSlot = event.getOrigin().getRawSlot();
            if (rawSlot != 4 && rawSlot < 9) event.setCancelled(true);
            if (rawSlot < 0 || rawSlot > event.getOrigin().getInventory().getSize() || event.getCurrentItem() == null) return;
            Inventory inv = event.getOrigin().getView().getTopInventory();
            if (rawSlot == 8) {
                ItemStack immolatePet = inv.getItem(4);
                if (Utils.checkNull(immolatePet)) return;
                if (Utils.checkPet(immolatePet)) {
                    Player player = event.getPlayer();
                    ImmolateUtils.immolatePet(immolatePet, player);
                    inv.removeItem(immolatePet);
                    Utils.command("chs run " + "任务检测.指引献祭异兽.ks " + player.getName());
                }
            }
        }
    }
    @EventHandler
    public void close(CloseEvent event){
        if (event.getMenuType().equals("异兽献祭")){
            for(ItemStack item : event.getContents()) {
                // 判断item是不是空的物品
                if (item == null || item.getItemMeta() == null) {
                    continue;
                }
                // 获得物品堆的物品数据
                ItemMeta itemmeta = item.getItemMeta();
                if (itemmeta.getDisplayName() != null) {
                    if (!itemmeta.getDisplayName().contains("-") && !itemmeta.getDisplayName().contains("确认献祭")) {
                        event.getPlayer().getInventory().addItem(item);
                    }
                } else {
                    event.getPlayer().getInventory().addItem(item);
                }
            }
        }
    }
}
