package com.rihejiu.nustarpet.listens;

import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.menu.event.ClickEvent;
import com.rihejiu.nustarpet.menu.event.CloseEvent;
import com.rihejiu.nustarpet.pet.utils.FruitUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FruitListener implements Listener {
    @EventHandler
    public void click(ClickEvent event){
        if (event.getMenuType().equals("喂养果实")){
            int rawSlot = event.getOrigin().getRawSlot();
            if (rawSlot != 2 && rawSlot != 6 && rawSlot < 9) event.setCancelled(true);
            if (rawSlot < 0 || rawSlot > event.getOrigin().getInventory().getSize() || event.getCurrentItem() == null) return;
            Inventory inv = event.getOrigin().getView().getTopInventory();
            if (rawSlot == 8){
                ItemStack petItem = inv.getItem(2);
                ItemStack fruit = inv.getItem(6);
                Player player = event.getPlayer();
                if (Utils.checkNull(petItem) || Utils.checkNull(fruit)) return;
                if (Utils.checkPet(petItem) && FruitUtils.checkFruit(fruit)){
                    if (FruitUtils.checkCanEat(petItem)){
                        FruitUtils.petEatFruit(petItem,fruit,player);
                        fruit.setAmount((fruit.getAmount() - 1));
                    } else {
                        player.sendMessage(Utils.msgColor("&c[果实]该异兽不可以再喂养果实了！"));
                    }
                } else {
                    player.sendMessage(Utils.msgColor("&c[果实]请放入正确的异兽或果实！"));
                }
            }
        }
    }
    @EventHandler
    public void close(CloseEvent event){
        if (event.getMenuType().equals("喂养果实")){
            for(ItemStack item : event.getContents()) {
                // 判断item是不是空的物品
                if (item == null || item.getItemMeta() == null) {
                    continue;
                }
                // 获得物品堆的物品数据
                ItemMeta itemmeta = item.getItemMeta();
                if (itemmeta.getDisplayName() != null) {
                    if (!itemmeta.getDisplayName().contains("-") && !itemmeta.getDisplayName().contains("确定喂养") && !itemmeta.getDisplayName().contains("玩法介绍")) {
                        event.getPlayer().getInventory().addItem(item);
                    }
                } else {
                    event.getPlayer().getInventory().addItem(item);
                }
            }
        }
    }
}
