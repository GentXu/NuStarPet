package com.rihejiu.nustarpet.listens;

import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.menu.InheritMenu2;
import com.rihejiu.nustarpet.menu.event.ClickEvent;
import com.rihejiu.nustarpet.menu.event.CloseEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InheritListener implements Listener {
    @EventHandler
    public void click(ClickEvent event){
        if (event.getMenuType().equals("异兽传承")){
            int rawSlot = event.getOrigin().getRawSlot();
            if (rawSlot != 11 && rawSlot != 15 && rawSlot < 45) event.setCancelled(true);
            if (rawSlot < 0 || rawSlot > event.getOrigin().getInventory().getSize() || event.getCurrentItem() == null) return;
            Inventory inv = event.getOrigin().getView().getTopInventory();
            ItemStack inheritedPet = inv.getItem(11);
            ItemStack inheritPet = inv.getItem(15);
            Player player = event.getPlayer();
            if (Utils.checkNull(inheritPet) || Utils.checkNull(inheritedPet)) return;
            if (rawSlot == 31) {
                if (Utils.checkPet(inheritPet) && Utils.checkPet(inheritedPet)) {
                    if (Integer.parseInt(NBTUtils.getStringTag(inheritPet, "Level")) >= 25) {
                        if (Integer.parseInt(NBTUtils.getStringTag(inheritedPet, "Level")) == 1) {
                            inv.remove(inheritPet);
                            inv.remove(inheritedPet);
                            new InheritMenu2(player, inheritPet, inheritedPet).open();
                        } else {
                            player.sendMessage(Utils.msgColor("&c[传承]被传承异兽等级不满足要求(=1)"));
                        }
                    } else {
                        player.sendMessage(Utils.msgColor("&c[传承]传承异兽等级不满足要求(>=25)。"));
                    }
                } else {
                    player.sendMessage(Utils.msgColor("&c[传承]请放入正确的异兽。"));
                }
            }
        }
    }
    @EventHandler
    public void close(CloseEvent event){
        if (event.getMenuType().equals("异兽传承")){
            for(ItemStack item : event.getContents()) {
                if (item == null || item.getItemMeta() == null) {
                    continue;
                }
                ItemMeta itemmeta = item.getItemMeta();
                if (itemmeta.getDisplayName() != null) {
                    if (!itemmeta.getDisplayName().contains("-") && !itemmeta.getDisplayName().contains("确认传承") && !itemmeta.getDisplayName().contains("玩法介绍")) {
                        event.getPlayer().getInventory().addItem(item);
                    }
                } else {
                    event.getPlayer().getInventory().addItem(item);
                }
            }
        }
    }
}
