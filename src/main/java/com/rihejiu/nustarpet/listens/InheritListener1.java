package com.rihejiu.nustarpet.listens;

import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.menu.event.ClickEvent;
import com.rihejiu.nustarpet.menu.event.CloseEvent;
import com.rihejiu.nustarpet.pet.utils.InheritUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InheritListener1 implements Listener {
    @EventHandler
    public void click(ClickEvent event){
        if (event.getMenuType().equals("确认传承")){
            int rawSlot = event.getOrigin().getRawSlot();
            if (rawSlot < 45) event.setCancelled(true);
            if (rawSlot < 0 || rawSlot > event.getOrigin().getInventory().getSize() || event.getCurrentItem() == null) return;
            Inventory inv = event.getOrigin().getView().getTopInventory();
            ItemStack inheritedPet = inv.getItem(11);
            ItemStack inheritPet = inv.getItem(15);
            Player player = event.getPlayer();
            if (Utils.checkNull(inheritPet) || Utils.checkNull(inheritedPet)) return;
            if (!Utils.checkPet(inheritedPet) || !Utils.checkPet(inheritPet)){
                player.sendMessage(Utils.msgColor("&c[传承]请放入正确的异兽。"));
                return;
            }
            if (!(Integer.parseInt(NBTUtils.getStringTag(inheritPet,"Level")) >= 25)){
                player.sendMessage(Utils.msgColor("&c[传承]传承异兽等级不满足要求(>=25)。"));
                return;
            }
            if (!(Integer.parseInt(NBTUtils.getStringTag(inheritedPet,"Level")) == 1)){
                player.sendMessage(Utils.msgColor("&c[传承]被传承异兽等级不满足要求(=1)"));
                return;
            }
            if (!InheritUtils.checkInheritSS(inheritedPet)){
                player.sendMessage(Utils.msgColor("&c[传承]被传异兽已经不能继续传承了！"));
                return;
            }
            switch (rawSlot){
                case 29:
                    inherit(player,1,inv,inheritedPet,inheritPet);
                    break;
                case 30:
                    inherit(player,2,inv,inheritedPet,inheritPet);
                    break;
                case 32:
                    inherit(player,3,inv,inheritedPet,inheritPet);
                    break;
                case 33:
                    inherit(player,4,inv,inheritedPet,inheritPet);
                    break;
            }
        }
    }
    public void inherit(Player player,int mode,Inventory inv,ItemStack inheritedPet,ItemStack inheritPet){
        if (Utils.checkQingseguoshi(player) >= 5 && Utils.checkKelongguoshi(player) >=1) {
            Utils.takeItem(player,"青涩果实",5);
            Utils.takeItem(player,"克隆果实",1);
            inheritedPet = InheritUtils.petInherit(inheritedPet, inheritPet, player, mode);
            inv.setItem(11, inheritedPet);
            inv.remove(inheritPet);
        } else {
            player.sendMessage(Utils.msgColor("&c[传承]道具不足。"));
        }
    }
    @EventHandler
    public void close(CloseEvent event){
        if (event.getMenuType().equals("确认传承")){
            for(ItemStack item : event.getContents()) {
                // 判断item是不是空的物品
                if (item == null || item.getItemMeta() == null) {
                    continue;
                }
                // 获得物品堆的物品数据
                ItemMeta itemmeta = item.getItemMeta();
                if (itemmeta.getDisplayName() != null) {
                    if (!itemmeta.getDisplayName().contains("-") && !itemmeta.getDisplayName().contains("确认传承") &&
                            !itemmeta.getDisplayName().contains("玩法介绍") && !itemmeta.getDisplayName().contains("力量")
                            && !itemmeta.getDisplayName().contains("敏捷") && !itemmeta.getDisplayName().contains("体力") && !itemmeta.getDisplayName().contains("智慧")) {
                        event.getPlayer().getInventory().addItem(item);
                    }
                } else {
                    event.getPlayer().getInventory().addItem(item);
                }
            }
        }
    }
}
