package com.rihejiu.nustarpet.listens;

import com.rihejiu.nustarpet.NuStarPet;
import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.menu.event.ClickEvent;
import com.rihejiu.nustarpet.menu.event.CloseEvent;
import com.rihejiu.nustarpet.pet.Pet;
import com.rihejiu.nustarpet.pet.PetWithoutSS;
import com.rihejiu.nustarpet.pet.utils.EvolveUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * 异兽进化
 */
public class EvolveListener implements Listener {
    @EventHandler
    public void onClick(ClickEvent event){
        if (event.getMenuType().equals("异兽进化")){
            int rawSlot = event.getOrigin().getRawSlot();
            if (rawSlot != 13 && rawSlot < 45) event.setCancelled(true);
            if (rawSlot < 0 || rawSlot > event.getOrigin().getInventory().getSize() || event.getCurrentItem() == null) return;
            Inventory inv = event.getOrigin().getView().getTopInventory();
            ItemStack evolvePet = inv.getItem(13);
            Player player = event.getPlayer();
            if (Utils.checkNull(evolvePet) || !Utils.checkPet(evolvePet)) return;
            int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvePet,"Evolution"));
            switch (rawSlot){
                case 17:
                    ItemStack sign = inv.getItem(17);
                    ItemMeta evolvePetMeta = evolvePet.getItemMeta();
                    String petName = evolvePetMeta.getDisplayName();
                    List<String> lore = new ArrayList<>();
                    lore.add(Utils.lineColor("&f你即将进化" + petName + ",请核对以下材料后确认进化"));
                    lore.add(Utils.lineColor("&f,花费100点券可以锁定1条属性进化(SS自动锁定):"));
                    lore.add(Utils.lineColor("&f生命精华: ") + EvolveUtils.checkSMJH(player) + "/1");
                    lore.add(Utils.lineColor("&f兽魂: ") + Utils.checkShouhun(player) + "/" + evolution);
                    lore.add(Utils.lineColor("&f力量[") + NBTUtils.getStringTag(evolvePet,"力量") + "]敏捷[" + NBTUtils.getStringTag(evolvePet,"敏捷") +
                            "]体力[" + NBTUtils.getStringTag(evolvePet,"体力") + "]智慧[" + NBTUtils.getStringTag(evolvePet,"智慧") + "]");
                    sign.setItemMeta(Utils.buildSign(sign,lore));
                    break;
                case 29:
                    if (EvolveUtils.checkSS(evolvePet)){
                        if (EvolveUtils.checkSMJH(player) >= 1 && Utils.takeItem(player,"兽魂",evolution)){
                            evolvePet = EvolveUtils.randomUnlockedPetTalent(evolvePet,new Pet(),player);
                            evolve(inv,evolvePet,player);
                        } else {
                            player.sendMessage(Utils.msgColor("&c[进化]道具不足！"));
                        }
                    } else {
                        if (EvolveUtils.checkSMJH(player) >= 1 && Utils.takeItem(player,"兽魂",evolution)){
                            evolvePet = EvolveUtils.randomUnlockedPetTalent(evolvePet,new PetWithoutSS(),player);
                            evolve(inv,evolvePet,player);
                        } else {
                            player.sendMessage(Utils.msgColor("&c[进化]道具不足！"));
                        }
                    }
                    break;
                case 30:
                    evolveWithPoints(player,evolvePet,inv,evolution,1);
                    break;
                case 31:
                    evolveWithPoints(player,evolvePet,inv,evolution,2);
                    break;
                case 32:
                    evolveWithPoints(player,evolvePet,inv,evolution,3);
                    break;
                case 33:
                    evolveWithPoints(player,evolvePet,inv,evolution,4);
                    break;
            }
        }
    }
    public void evolve(Inventory inv,ItemStack evolvePet,Player player){
        Utils.takeItem(player,"生命精华",1);
        inv.setItem(13,evolvePet);
        Utils.command("chs run " + "任务检测.指引异兽进化.ks " + player.getName());
    }
    public void evolveWithPoints(Player player,ItemStack evolvePet,Inventory inv, Integer evolution,Integer mode){
        if (!(NuStarPet.getPoints().look(player.getUniqueId()) >= 100)){
            player.sendMessage(Utils.msgColor("&c[进化]你的点券不足了。"));
            return;
        }
        if (EvolveUtils.checkSS(evolvePet)){
            if (EvolveUtils.checkSMJH(player) >= 1 && Utils.takeItem(player,"兽魂",evolution)){
                switch (mode){
                    case 1:
                        evolvePet = EvolveUtils.randomPowerLockedPetTalent(evolvePet, new Pet(), player);
                        break;
                    case 2:
                        evolvePet = EvolveUtils.randomSpeedLockedPetTalent(evolvePet, new Pet(), player);
                        break;
                    case 3:
                        evolvePet = EvolveUtils.randomSpiritLockedPetTalent(evolvePet, new Pet(), player);
                        break;
                    case 4:
                        evolvePet = EvolveUtils.randomWisdomLockedPetTalent(evolvePet, new Pet(), player);
                        break;
                }
                evolve(inv,evolvePet,player);
                NuStarPet.getPoints().take(player.getUniqueId(),100);
            } else {
                player.sendMessage(Utils.msgColor("&c[进化]道具不足！"));
            }
        } else {
            if (EvolveUtils.checkSMJH(player) >= 1 && Utils.takeItem(player,"兽魂",evolution)){
                switch (mode){
                    case 1:
                        evolvePet = EvolveUtils.randomPowerLockedPetTalent(evolvePet, new PetWithoutSS(), player);
                        break;
                    case 2:
                        evolvePet = EvolveUtils.randomSpeedLockedPetTalent(evolvePet, new PetWithoutSS(), player);
                        break;
                    case 3:
                        evolvePet = EvolveUtils.randomSpiritLockedPetTalent(evolvePet, new PetWithoutSS(), player);
                        break;
                    case 4:
                        evolvePet = EvolveUtils.randomWisdomLockedPetTalent(evolvePet, new PetWithoutSS(), player);
                        break;
                }
                evolve(inv,evolvePet,player);
                NuStarPet.getPoints().take(player.getUniqueId(),100);
            } else {
                player.sendMessage(Utils.msgColor("&c[进化]道具不足！"));
            }
        }
    }
    @EventHandler
    public void onClose(CloseEvent event){
        if (event.getMenuType().equals("异兽进化")){
            for(ItemStack item : event.getContents()) {
                if (item == null || item.getItemMeta() == null) {
                    continue;
                }
                ItemMeta itemmeta = item.getItemMeta();
                if (itemmeta.getDisplayName() != null) {
                    if (!itemmeta.getDisplayName().contains("-") && !itemmeta.getDisplayName().contains("玩法介绍") &&
                            !itemmeta.getDisplayName().contains("开始随机进化") && !itemmeta.getDisplayName().contains("锁定力量进化") &&
                            !itemmeta.getDisplayName().contains("锁定敏捷进化") && !itemmeta.getDisplayName().contains("锁定体力进化") &&
                            !itemmeta.getDisplayName().contains("锁定智慧进化")) {
                        event.getPlayer().getInventory().addItem(item);
                    }
                } else {
                    event.getPlayer().getInventory().addItem(item);
                }
            }
        }
    }

}
