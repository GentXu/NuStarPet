package com.rihejiu.nustarpet.listens;

import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.menu.event.ClickEvent;
import com.rihejiu.nustarpet.menu.event.CloseEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FruitMakeListener implements Listener {
    @EventHandler
    public void click(ClickEvent event){
        if (event.getMenuType().equals("附魔果实")){
            int rawSlot = event.getOrigin().getRawSlot();
            if (rawSlot != 4 && rawSlot < 9) event.setCancelled(true);
            if (rawSlot < 0 || rawSlot > event.getOrigin().getInventory().getSize() || event.getCurrentItem() == null) return;
            Inventory inv = event.getOrigin().getView().getTopInventory();
            Player player = event.getPlayer();
            if (rawSlot == 8){
                ItemStack fruit = inv.getItem(4);
                if (!checkFruit(fruit)){
                    player.sendMessage(Utils.msgColor("&c[果实]你放入的物品不正确!"));
                    return;
                }
                if (!(Utils.checkItem(player,"创造宝石") >= 1)){
                    player.sendMessage(Utils.msgColor("&c[果实]你身上没有创造宝石!"));
                    return;
                }
                Utils.takeItem(player,"创造宝石",1);
                fruit.setAmount(fruit.getAmount() - 1);
                giveFruit(player);
            }
        }
    }
    public boolean checkFruit(ItemStack fruit){
        if (Utils.checkNull(fruit)) return false;
        return (fruit.getItemMeta().getDisplayName().contains("青涩果实") && Utils.checkLore(fruit,"售价"));
    }
    public void giveFruit(Player player){
        if (Utils.shouldExecute(0.25)){
            Utils.command("ni give " + player.getName() + " 敏捷果实");
            player.sendMessage(Utils.msgColor("&f宝石在接触果实后，青涩的果实慢慢转变为绿色。"));
            return;
        }
        if (Utils.shouldExecute(0.25)){
            Utils.command("ni give " + player.getName() + " 力量果实");
            player.sendMessage(Utils.msgColor("&f宝石在接触果实后，青涩的果实慢慢转变为黄色。"));
            return;
        }
        if (Utils.shouldExecute(0.25)){
            Utils.command("ni give " + player.getName() + " 智慧果实");
            player.sendMessage(Utils.msgColor("&f宝石在接触果实后，青涩的果实慢慢转变为蓝色。"));
            return;
        }
        if (Utils.shouldExecute(0.25)){
            Utils.command("ni give " + player.getName() + " 体力果实");
            player.sendMessage(Utils.msgColor("&f宝石在接触果实后，青涩的果实慢慢转变为红色。"));
            return;
        }
        giveFruit(player);
    }
    @EventHandler
    public void close(CloseEvent event){
        if (event.getMenuType().equals("附魔果实")){
            for(ItemStack item : event.getContents()) {
                // 判断item是不是空的物品
                if (item == null || item.getItemMeta() == null) {
                    continue;
                }
                // 获得物品堆的物品数据
                ItemMeta itemmeta = item.getItemMeta();
                if (itemmeta.getDisplayName() != null) {
                    if (!itemmeta.getDisplayName().contains("-") && !itemmeta.getDisplayName().contains("附魔果实") && !itemmeta.getDisplayName().contains("玩法介绍")) {
                        event.getPlayer().getInventory().addItem(item);
                    }
                } else {
                    event.getPlayer().getInventory().addItem(item);
                }
            }
        }
    }
}
