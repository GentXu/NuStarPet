package com.rihejiu.nustarpet.listens;

import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.menu.event.ClickEvent;
import com.rihejiu.nustarpet.menu.event.CloseEvent;
import com.rihejiu.nustarpet.pet.utils.TalentUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TaltenListener implements Listener {
    @EventHandler
    public void click(ClickEvent event){
        if (event.getMenuType().equals("异兽天赋")){
            int rawSlot = event.getOrigin().getRawSlot();
            if (rawSlot != 4 && rawSlot < 9) event.setCancelled(true);
            if (rawSlot < 0 || rawSlot > event.getOrigin().getInventory().getSize() || event.getCurrentItem() == null) return;
            Inventory inv = event.getOrigin().getView().getTopInventory();
            switch (rawSlot){
                case 7:
                    ItemStack sign = inv.getItem(7);
                    ItemStack petItem = inv.getItem(4);
                    if (Utils.checkNull(petItem)) return;
                    sign.setItemMeta(TalentUtils.talentSign(petItem,event.getPlayer()));
                    break;
                case 8:
                    talent(event.getPlayer(), inv);
                    break;
            }
        }
    }
    public void talent(Player player,Inventory inv){
        ItemStack petItem = inv.getItem(4);
        if (petItem == null || petItem.getItemMeta() == null || !petItem.getItemMeta().hasDisplayName()){return;}
        if (Utils.checkPet(petItem)){
            if (TalentUtils.chooseTalentPlan(petItem)){
                if (Utils.checkItem(player,"兽魂") >= TalentUtils.petTalentShouHun(petItem) && TalentUtils.checkTalent(player) >= 4){
                    if (TalentUtils.checkPetTalent(petItem,player)) {
                        Utils.takeItem(player, "兽魂", TalentUtils.petTalentShouHun(petItem));
                        Utils.takeItem(player, "天赋精华", 4);
                        if (Utils.shouldExecute(TalentUtils.petTalentChance(petItem))) {
                            TalentUtils.petTalent(petItem, player);
                        } else {
                            player.sendMessage(Utils.msgColor("&c[培养]很遗憾这次什么天赋都没获得。"));
                        }
                    }
                } else {
                    player.sendMessage(Utils.msgColor("&c[培养]你的材料不足！"));
                }
            } else {
                if (Utils.checkItem(player,"兽魂") >= TalentUtils.petTalentShouHun(petItem) && TalentUtils.checkAdvancedTalent(player) >= 2){
                    if (TalentUtils.checkPetTalent(petItem,player)) {
                        Utils.takeItem(player, "兽魂", TalentUtils.petTalentShouHun(petItem));
                        Utils.takeItem(player, "高级精华", 2);
                        if (Utils.shouldExecute(TalentUtils.petTalentChance(petItem))) {
                            TalentUtils.petTalent(petItem, player);
                        } else {
                            player.sendMessage(Utils.msgColor("&c[培养]很遗憾这次什么天赋都没获得。"));
                        }
                    }
                } else {
                    player.sendMessage(Utils.msgColor("&c[培养]你的材料不足！"));
                }
            }
        } else {
            player.sendMessage(Utils.msgColor("&c[培养]请放入正确的异兽！"));
        }
    }
    @EventHandler
    public void close(CloseEvent event){
        if (event.getMenuType().equals("异兽天赋")){
            for(ItemStack item : event.getContents()) {
                // 判断item是不是空的物品
                if (item == null || item.getItemMeta() == null) {
                    continue;
                }
                // 获得物品堆的物品数据
                ItemMeta itemmeta = item.getItemMeta();
                if (itemmeta.getDisplayName() != null) {
                    if (!itemmeta.getDisplayName().contains("-") && !itemmeta.getDisplayName().contains("开始培养") && !itemmeta.getDisplayName().contains("玩法介绍")) {
                        event.getPlayer().getInventory().addItem(item);
                    }
                } else {
                    event.getPlayer().getInventory().addItem(item);
                }
            }
        }
    }
}
