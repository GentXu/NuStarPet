package com.rihejiu.nustarpet.listens;

import com.rihejiu.nustarpet.Menu;
import com.rihejiu.nustarpet.NuStarPet;
import com.rihejiu.nustarpet.command.Identify;
import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.menu.*;
import com.rihejiu.nustarpet.pet.Pet;
import com.rihejiu.nustarpet.pet.PetSS;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class PetLevelUp implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e)  {
        // 获得玩家实例，该玩家为触发该事件的玩家
        Player player = (Player) e.getWhoClicked();
        // 获得GUI界面实例，该界面为玩家打开的界面
        InventoryView inv = player.getOpenInventory();
        ItemStack pet = Identify.getPetStack(player,8);
        if (inv.getTopInventory().getTitle().equals(Menu.TITLE)){
           if (e.getRawSlot() == 89){
                e.setCancelled(true);
           }
        }
        // 如果该界面标题是Menu类中的TITLE 则执行下面的代码
        if (inv.getTitle().equals(Menu.TITLE)){
            // 存入放玻璃板的物品槽
            int[] slot = {0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,46,47,48,49,50,51,52,53};
            for (int j : slot) {
                // 禁止对玻璃板等物品进行操作
                if (e.getRawSlot() == j) {
                    e.setCancelled(true);
                }
            }
            // 确保点的地方肯定有物品
            if (e.getRawSlot() < 0 || e.getRawSlot() > e.getInventory().getSize()){
                return;
            }
            // 获取被点的格子的物品
            ItemStack clickedItem = e.getCurrentItem();
            // 如果被点的物品是null则返回
            if (clickedItem == null){
                return;
            }
            if (!Utils.checkPet(pet)){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&f[异兽]你必须先出战一只异兽！"));
                return;
            }
            // 回收物品
            if (e.getRawSlot() == 49){
                // 遍历GUI中的每个物品堆然后存到 item中
                int amount = 0;
                int sumexp = 0;
                for(ItemStack item : inv.getTopInventory().getContents()){
                    // 判断item是不是空的物品
                    if (item == null || item.getItemMeta() == null){
                        continue;
                    }
                    // 获得物品堆的物品数据
                    ItemMeta itemmeta = item.getItemMeta();
                    int flag = 0;
                    int flag2 = 0;
                    if (itemmeta.hasDisplayName() && itemmeta.hasLore() && !itemmeta.getDisplayName().equals("-")){
                        for (int i = 0; i < itemmeta.getLore().size(); i++) {
                            // 遍历每一行lore 如果有符合条件的lore 则出售该物品 否则 物品保留不动
                            String lore = itemmeta.getLore().get(i);
                            if (lore.contains("喂养异兽")){
                                flag2 = 1;
                            }
                            if (lore.contains("售价: ") && flag2 == 1){
                                String[] split = lore.split("\\s*:\\s*");
                                if (split.length == 2){
                                    // 可能出现类型转换出错的报错
                                    try {
                                        double exp = Double.parseDouble(split[1]);
                                        double allexp = exp * item.getAmount();
                                        flag += 1;
                                        amount += item.getAmount();
                                        sumexp += allexp;
                                    } catch (NumberFormatException ne) {
                                        player.sendMessage(ChatColor.RED + "你的物品中出现了非法字符，如 售价: abc123");
                                        return;
                                    }
                                    break;
                                }
                            }
                        }
                        if (flag != 0){
                            inv.getTopInventory().removeItem(item);
                        }
                    }
                }
                if (amount != 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[异兽]喂养了" + amount + "个物品，异兽获得了" + sumexp + "点经验"));
                    player.getInventory().setItem(8,Pet.PetLevelUp(player, sumexp, Identify.getPetStack(player, 8)));
                }
            }
        }
        if (inv.getTitle().equals(ImmolateMenu.TITLE)){
            int[] slot = {0,1,2,3,5,6,7,8};
            for (int j : slot) {
                // 禁止对玻璃板等物品进行操作
                if (e.getRawSlot() == j) {
                    e.setCancelled(true);
                }
            }
            // 确保点的地方肯定有物品
            if (e.getRawSlot() < 0 || e.getRawSlot() > e.getInventory().getSize()){
                return;
            }
            // 获取被点的格子的物品
            ItemStack clickedItem = e.getCurrentItem();
            // 如果被点的物品是null则返回
            if (clickedItem == null){
                return;
            }
            if (e.getRawSlot() == 8){
                ItemStack immolatepet = inv.getTopInventory().getItem(4);
                if (immolatepet == null || immolatepet.getItemMeta() == null || !immolatepet.getItemMeta().hasDisplayName()){return;}
                if (Utils.checkPet(immolatepet)){
                    Utils.immolatePet(immolatepet,player);
                    inv.getTopInventory().removeItem(immolatepet);
                }
            }
        }
        if (inv.getTitle().equals(ReviveMenu.TITLE)){
            int[] slot = {0,1,2,3,5,6,7,8};
            for (int j : slot) {
                // 禁止对玻璃板等物品进行操作
                if (e.getRawSlot() == j) {
                    e.setCancelled(true);
                }
            }
            // 确保点的地方肯定有物品
            if (e.getRawSlot() < 0 || e.getRawSlot() > e.getInventory().getSize()){
                return;
            }
            // 获取被点的格子的物品
            ItemStack clickedItem = e.getCurrentItem();
            // 如果被点的物品是null则返回
            if (clickedItem == null){
                return;
            }
            if (e.getRawSlot() == 8){
                ItemStack revivepet = inv.getTopInventory().getItem(4);
                if (revivepet == null || revivepet.getItemMeta() == null || !revivepet.getItemMeta().hasDisplayName()){return;}
                if (Utils.checkPet(revivepet)) {
                    Pet.setPetHealth(revivepet, player);
                }
            }
        }
        if (inv.getTitle().equals(EvolveMenu.TITLE)){
            if (e.getRawSlot() != 13 && e.getRawSlot() < 45 ) {
                e.setCancelled(true);
            }
            // 确保点的地方肯定有物品
            if (e.getRawSlot() < 0 || e.getRawSlot() > e.getInventory().getSize()){
                return;
            }
            // 获取被点的格子的物品
            ItemStack clickedItem = e.getCurrentItem();
            // 如果被点的物品是null则返回
            if (clickedItem == null){
                return;
            }
            PetSS newpet = new PetSS();
            Pet newpet2 = new Pet();
            if (e.getRawSlot() == 17){
                ItemStack sign = inv.getTopInventory().getItem(17);
                ItemMeta signmeta = sign.getItemMeta();
                ItemStack evolvepet = inv.getTopInventory().getItem(13);
                String petname;
                if (evolvepet == null || evolvepet.getItemMeta() == null || !evolvepet.getItemMeta().hasDisplayName()){return;}
                ItemMeta evolvepetmeta = evolvepet.getItemMeta();
                if (Utils.checkPet(evolvepet)){
                    petname = evolvepetmeta.getDisplayName();
                    List<String> lore = new ArrayList<>();
                    int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
                    lore.add(Utils.msgColor("&f你即将进化" + petname + ",请核对以下材料后确认进化"));
                    lore.add(Utils.msgColor("&f,花费100点券可以锁定1条属性进化(SS自动锁定):"));
                    lore.add(Utils.msgColor("&f生命精华: ") + Utils.checkShengmingjinghua(player) + "/1");
                    lore.add(Utils.msgColor("&f兽魂: ") + Utils.checkShouhun(player) + "/" + evolution);
                    lore.add(Utils.msgColor("&f力量[") + NBTUtils.getStringTag(evolvepet,"力量") + "]敏捷[" + NBTUtils.getStringTag(evolvepet,"敏捷") +
                    "]体力[" + NBTUtils.getStringTag(evolvepet,"体力") + "]智慧[" + NBTUtils.getStringTag(evolvepet,"智慧") + "]");
                    signmeta.setLore(lore);
                    sign.setItemMeta(signmeta);
                } else {
                    List<String> lore = new ArrayList<>();
                    lore.add(Utils.msgColor("&f请放入异兽"));
                    signmeta.setLore(lore);
                    sign.setItemMeta(signmeta);
                }
            }
            if (e.getRawSlot() == 29){
                ItemStack evolvepet = inv.getTopInventory().getItem(13);
                if (evolvepet == null || evolvepet.getItemMeta() == null || !evolvepet.getItemMeta().hasDisplayName()){return;}
                if (Utils.checkPet(evolvepet)){
                    int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
                    if (Utils.checkSS(evolvepet)) {
                        if (Utils.checkShengmingjinghua(player) >= 1 && Utils.takeItem(player, "兽魂", evolution)) {
                            Utils.takeItem(player, "生命精华", 1);
                            evolvepet = Utils.randomUnlockedPetTalent(evolvepet, newpet, player);
                            inv.getTopInventory().setItem(13, evolvepet);
                            Utils.command("chs run " + "任务检测.指引异兽进化.ks " + player);
                        } else {
                            player.sendMessage(Utils.msgColor("&c[进化]道具不足！"));
                        }
                    } else {
                        player.sendMessage(Utils.msgColor("&c[进化]该异兽已经具有一条SS属性，接下来不会再出现SS属性。"));
                        if (Utils.checkShengmingjinghua(player) >= 1 && Utils.takeItem(player, "兽魂", evolution)) {
                            Utils.takeItem(player, "生命精华", 1);
                            evolvepet = Utils.randomUnlockedPetTalent(evolvepet, newpet2, player);
                            inv.getTopInventory().setItem(13, evolvepet);
                        } else {
                            player.sendMessage(Utils.msgColor("&c[进化]进化道具不足！"));
                        }
                    }
                }
            }
            if (e.getRawSlot() == 30){
                ItemStack evolvepet = inv.getTopInventory().getItem(13);
                if (evolvepet == null || evolvepet.getItemMeta() == null || !evolvepet.getItemMeta().hasDisplayName()){return;}
                if (Utils.checkPet(evolvepet)){
                    if (NuStarPet.getPoints().look(player.getUniqueId()) >= 100) {
                        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
                        if (Utils.checkSS(evolvepet)) {
                            if (Utils.checkShengmingjinghua(player) >= 1 && Utils.takeItem(player, "兽魂", evolution)) {
                                Utils.takeItem(player, "生命精华", 1);
                                NuStarPet.getPoints().take(player.getUniqueId(), 100);
                                player.sendMessage(Utils.msgColor("&f[异兽]消耗100点券锁定力量进化，剩余点券" + NuStarPet.getPoints().look(player.getUniqueId())));
                                evolvepet = Utils.randomPowerlockedPetTalent(evolvepet, newpet, player);
                                inv.getTopInventory().setItem(13, evolvepet);
                            } else {
                                player.sendMessage(Utils.msgColor("&c[进化]道具不足！"));
                            }
                        } else {
                            player.sendMessage(Utils.msgColor("&c[进化]该异兽已经具有一条SS属性，接下来不会再出现SS属性。"));
                            if (Utils.checkShengmingjinghua(player) >= 1 && Utils.takeItem(player, "兽魂", evolution)) {
                                Utils.takeItem(player, "生命精华", 1);
                                NuStarPet.getPoints().take(player.getUniqueId(), 100);
                                player.sendMessage(Utils.msgColor("&f[进化]消耗100点券锁定力量进化，剩余点券" + NuStarPet.getPoints().look(player.getUniqueId())));
                                evolvepet = Utils.randomPowerlockedPetTalent(evolvepet, newpet2, player);
                                inv.getTopInventory().setItem(13, evolvepet);
                            } else {
                                player.sendMessage(Utils.msgColor("&c[进化]进化道具不足！"));
                            }
                        }
                    } else {
                        player.sendMessage(Utils.msgColor("&c[进化]你的点券不足了。"));
                    }
                }
            }
            if (e.getRawSlot() == 31){
                ItemStack evolvepet = inv.getTopInventory().getItem(13);
                if (evolvepet == null || evolvepet.getItemMeta() == null || !evolvepet.getItemMeta().hasDisplayName()){return;}
                if (Utils.checkPet(evolvepet)){
                    if (NuStarPet.getPoints().look(player.getUniqueId()) >= 100) {
                        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
                        if (Utils.checkSS(evolvepet)) {
                            if (Utils.checkShengmingjinghua(player) >= 1 && Utils.takeItem(player, "兽魂", evolution)) {
                                Utils.takeItem(player, "生命精华", 1);
                                NuStarPet.getPoints().take(player.getUniqueId(), 100);
                                player.sendMessage(Utils.msgColor("&f[进化]消耗100点券锁定敏捷进化，剩余点券" + NuStarPet.getPoints().look(player.getUniqueId())));
                                evolvepet = Utils.randomSpeedlockedPetTalent(evolvepet, newpet, player);
                                inv.getTopInventory().setItem(13, evolvepet);
                            } else {
                                player.sendMessage(Utils.msgColor("&c[进化]道具不足！"));
                            }
                        } else {
                            player.sendMessage(Utils.msgColor("&c[进化]该异兽已经具有一条SS属性，接下来不会再出现SS属性。"));
                            if (Utils.checkShengmingjinghua(player) >= 1 && Utils.takeItem(player, "兽魂", evolution)) {
                                Utils.takeItem(player, "生命精华", 1);
                                NuStarPet.getPoints().take(player.getUniqueId(), 100);
                                player.sendMessage(Utils.msgColor("&f[进化]消耗100点券锁定敏捷进化，剩余点券" + NuStarPet.getPoints().look(player.getUniqueId())));
                                evolvepet = Utils.randomSpeedlockedPetTalent(evolvepet, newpet2, player);
                                inv.getTopInventory().setItem(13, evolvepet);
                            } else {
                                player.sendMessage(Utils.msgColor("&c[进化]道具不足！"));
                            }
                        }
                    } else {
                        player.sendMessage(Utils.msgColor("&c[进化]你的点券不足了。"));
                    }
                }
            }
            if (e.getRawSlot() == 32){
                ItemStack evolvepet = inv.getTopInventory().getItem(13);
                if (evolvepet == null || evolvepet.getItemMeta() == null || !evolvepet.getItemMeta().hasDisplayName()){return;}
                if (Utils.checkPet(evolvepet)){
                    if (NuStarPet.getPoints().look(player.getUniqueId()) >= 100) {
                        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
                        if (Utils.checkSS(evolvepet)) {
                            if (Utils.checkShengmingjinghua(player) >= 1 && Utils.takeItem(player, "兽魂", evolution)) {
                                Utils.takeItem(player, "生命精华", 1);
                                NuStarPet.getPoints().take(player.getUniqueId(), 100);
                                player.sendMessage(Utils.msgColor("&f[进化]消耗100点券锁定体力进化，剩余点券" + NuStarPet.getPoints().look(player.getUniqueId())));
                                evolvepet = Utils.randomSpiritlockedPetTalent(evolvepet, newpet, player);
                                inv.getTopInventory().setItem(13, evolvepet);
                            } else {
                                player.sendMessage(Utils.msgColor("&c[进化]道具不足！"));
                            }
                        } else {
                            player.sendMessage(Utils.msgColor("&c[进化]该异兽已经具有一条SS属性，接下来不会再出现SS属性。"));
                            if (Utils.checkShengmingjinghua(player) >= 1 && Utils.takeItem(player, "兽魂", evolution)) {
                                Utils.takeItem(player, "生命精华", 1);
                                NuStarPet.getPoints().take(player.getUniqueId(), 100);
                                player.sendMessage(Utils.msgColor("&f[进化]消耗100点券锁定体力进化，剩余点券" + NuStarPet.getPoints().look(player.getUniqueId())));
                                evolvepet = Utils.randomSpiritlockedPetTalent(evolvepet, newpet2, player);
                                inv.getTopInventory().setItem(13, evolvepet);
                            } else {
                                player.sendMessage(Utils.msgColor("&c[进化]道具不足！"));
                            }
                        }
                    } else {
                        player.sendMessage(Utils.msgColor("&c[进化]你的点券不足了。"));
                    }
                }
            }
            if (e.getRawSlot() == 33){
                ItemStack evolvepet = inv.getTopInventory().getItem(13);
                if (evolvepet == null || evolvepet.getItemMeta() == null || !evolvepet.getItemMeta().hasDisplayName()){return;}
                if (Utils.checkPet(evolvepet)){
                    if (NuStarPet.getPoints().look(player.getUniqueId()) >= 100) {
                        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
                        if (Utils.checkSS(evolvepet)) {
                            if (Utils.checkShengmingjinghua(player) >= 1 && Utils.takeItem(player, "兽魂", evolution)) {
                                Utils.takeItem(player, "生命精华", 1);
                                NuStarPet.getPoints().take(player.getUniqueId(), 100);
                                player.sendMessage(Utils.msgColor("&f[进化]消耗100点券锁定智慧进化，剩余点券" + NuStarPet.getPoints().look(player.getUniqueId())));
                                evolvepet = Utils.randomWisdomlockedPetTalent(evolvepet, newpet, player);
                                inv.getTopInventory().setItem(13, evolvepet);
                            } else {
                                player.sendMessage(Utils.msgColor("&c[进化]道具不足！"));
                            }
                        } else {
                            player.sendMessage(Utils.msgColor("&c[进化]该异兽已经具有一条SS属性，接下来不会再出现SS属性。"));
                            if (Utils.checkShengmingjinghua(player) >= 1 && Utils.takeItem(player, "兽魂", evolution)) {
                                Utils.takeItem(player, "生命精华", 1);
                                NuStarPet.getPoints().take(player.getUniqueId(), 100);
                                player.sendMessage(Utils.msgColor("&f[进化]消耗100点券锁定智慧进化，剩余点券" + NuStarPet.getPoints().look(player.getUniqueId())));
                                evolvepet = Utils.randomWisdomlockedPetTalent(evolvepet, newpet2, player);
                                inv.getTopInventory().setItem(13, evolvepet);
                            } else {
                                player.sendMessage(Utils.msgColor("&c[进化]道具不足！"));
                            }
                        }
                    } else {
                        player.sendMessage(Utils.msgColor("&c[进化]你的点券不足了。"));
                    }
                }
            }
        }
        if (inv.getTitle().equals(InheritMenu.TITLE)){
            if (e.getRawSlot() != 11 && e.getRawSlot() != 15 && e.getRawSlot() < 45 ) {
                e.setCancelled(true);
            }
            // 确保点的地方肯定有物品
            if (e.getRawSlot() < 0 || e.getRawSlot() > e.getInventory().getSize()){
                return;
            }
            // 获取被点的格子的物品
            ItemStack clickedItem = e.getCurrentItem();
            // 如果被点的物品是null则返回
            if (clickedItem == null){
                return;
            }
            if (e.getRawSlot() == 31){
                ItemStack inheritedpet = inv.getTopInventory().getItem(11);
                ItemStack inheritpet = inv.getTopInventory().getItem(15);
                if (inheritpet == null || inheritpet.getItemMeta() == null || !inheritpet.getItemMeta().hasDisplayName()){return;}
                if (inheritedpet == null || inheritedpet.getItemMeta() == null || !inheritedpet.getItemMeta().hasDisplayName()){return;}
                if (Utils.checkPet(inheritpet) && Utils.checkPet(inheritedpet)){
                    if (Integer.parseInt(NBTUtils.getStringTag(inheritpet,"Level")) >= 35){
                        if (Integer.parseInt(NBTUtils.getStringTag(inheritedpet,"Level")) == 1){
                            inv.getTopInventory().remove(inheritpet);
                            inv.getTopInventory().remove(inheritedpet);
                            new InheritMenu2(player,inheritpet,inheritedpet).open();
                        } else {
                            player.sendMessage(Utils.msgColor("&c[传承]被传承异兽等级不满足要求(=1)"));
                        }
                    } else {
                        player.sendMessage(Utils.msgColor("&c[传承]传承异兽等级不满足要求(>=35)。"));
                    }
                } else {
                    player.sendMessage(Utils.msgColor("&c[传承]请放入正确的异兽。"));
                }
            }
        }
        if (inv.getTitle().equals(InheritMenu2.TITLE)){
            if ( e.getRawSlot() < 45 ) {
                e.setCancelled(true);
            }
            // 确保点的地方肯定有物品
            if (e.getRawSlot() < 0 || e.getRawSlot() > e.getInventory().getSize()){
                return;
            }
            // 获取被点的格子的物品
            ItemStack clickedItem = e.getCurrentItem();
            // 如果被点的物品是null则返回
            if (clickedItem == null){
                return;
            }
            if (e.getRawSlot() == 29){
                ItemStack inheritedpet = inv.getTopInventory().getItem(11);
                ItemStack inheritpet = inv.getTopInventory().getItem(15);
                if (inheritpet == null || inheritpet.getItemMeta() == null || !inheritpet.getItemMeta().hasDisplayName()){return;}
                if (inheritedpet == null || inheritedpet.getItemMeta() == null || !inheritedpet.getItemMeta().hasDisplayName()){return;}
                if (Utils.checkPet(inheritpet) && Utils.checkPet(inheritedpet)){
                    if (Integer.parseInt(NBTUtils.getStringTag(inheritpet,"Level")) >= 35){
                        if (Integer.parseInt(NBTUtils.getStringTag(inheritedpet,"Level")) == 1){
                            if (Utils.checkInheritSS(inheritedpet)) {
                                if (Utils.checkQingseguoshi(player) >= 5 && Utils.checkKelongguoshi(player) >=1) {
                                    Utils.takeItem(player,"青涩果实",5);
                                    Utils.takeItem(player,"克隆果实",1);
                                    inheritedpet = Utils.petInherit(inheritedpet, inheritpet, player, 1);
                                    inv.getTopInventory().setItem(11, inheritedpet);
                                    inv.getTopInventory().remove(inheritpet);
                                } else {
                                    player.sendMessage(Utils.msgColor("&c[传承]道具不足。"));
                                }
                            } else {
                                player.sendMessage(Utils.msgColor("&c[传承]被传异兽已经不能继续传承了！"));
                            }
                        } else {
                            player.sendMessage(Utils.msgColor("&c[传承]被传承异兽等级不满足要求(=1)"));
                        }
                    } else {
                        player.sendMessage(Utils.msgColor("&c[传承]传承异兽等级不满足要求(>=35)。"));
                    }
                } else {
                    player.sendMessage(Utils.msgColor("&c[传承]请放入正确的异兽。"));
                }
            }
            if (e.getRawSlot() == 30){
                ItemStack inheritedpet = inv.getTopInventory().getItem(11);
                ItemStack inheritpet = inv.getTopInventory().getItem(15);
                if (inheritpet == null || inheritpet.getItemMeta() == null || !inheritpet.getItemMeta().hasDisplayName()){return;}
                if (inheritedpet == null || inheritedpet.getItemMeta() == null || !inheritedpet.getItemMeta().hasDisplayName()){return;}
                if (Utils.checkPet(inheritpet) && Utils.checkPet(inheritedpet)){
                    if (Integer.parseInt(NBTUtils.getStringTag(inheritpet,"Level")) >= 35){
                        if (Integer.parseInt(NBTUtils.getStringTag(inheritedpet,"Level")) == 1){
                            if (Utils.checkInheritSS(inheritedpet)) {
                                if (Utils.checkQingseguoshi(player) >= 5 && Utils.checkKelongguoshi(player) >=1) {
                                    Utils.takeItem(player,"青涩果实",5);
                                    Utils.takeItem(player,"克隆果实",1);
                                    inheritedpet = Utils.petInherit(inheritedpet, inheritpet, player, 2);
                                    inv.getTopInventory().setItem(11, inheritedpet);
                                    inv.getTopInventory().remove(inheritpet);
                                } else {
                                    player.sendMessage(Utils.msgColor("&c[传承]道具不足。"));
                                }
                            } else {
                                player.sendMessage(Utils.msgColor("&c[传承]被传异兽已经不能继续传承了！"));
                            }
                        } else {
                            player.sendMessage(Utils.msgColor("&c[传承]被传承异兽等级不满足要求(=1)"));
                        }
                    } else {
                        player.sendMessage(Utils.msgColor("&c[传承]传承异兽等级不满足要求(>=35)。"));
                    }
                } else {
                    player.sendMessage(Utils.msgColor("&c[传承]请放入正确的异兽。"));
                }
            }
            if (e.getRawSlot() == 32){
                ItemStack inheritedpet = inv.getTopInventory().getItem(11);
                ItemStack inheritpet = inv.getTopInventory().getItem(15);
                if (inheritpet == null || inheritpet.getItemMeta() == null || !inheritpet.getItemMeta().hasDisplayName()){return;}
                if (inheritedpet == null || inheritedpet.getItemMeta() == null || !inheritedpet.getItemMeta().hasDisplayName()){return;}
                if (Utils.checkPet(inheritpet) && Utils.checkPet(inheritedpet)){
                    if (Integer.parseInt(NBTUtils.getStringTag(inheritpet,"Level")) >= 35){
                        if (Integer.parseInt(NBTUtils.getStringTag(inheritedpet,"Level")) == 1){
                            if (Utils.checkInheritSS(inheritedpet)) {
                                if (Utils.checkQingseguoshi(player) >= 5 && Utils.checkKelongguoshi(player) >=1) {
                                    Utils.takeItem(player,"青涩果实",5);
                                    Utils.takeItem(player,"克隆果实",1);
                                    inheritedpet = Utils.petInherit(inheritedpet, inheritpet, player, 4);
                                    inv.getTopInventory().setItem(11, inheritedpet);
                                    inv.getTopInventory().remove(inheritpet);
                                } else {
                                    player.sendMessage(Utils.msgColor("&c[传承]道具不足。"));
                                }
                            } else {
                                player.sendMessage(Utils.msgColor("&c[传承]被传异兽已经不能继续传承了！"));
                            }
                        } else {
                            player.sendMessage(Utils.msgColor("&c[传承]被传承异兽等级不满足要求(=1)"));
                        }
                    } else {
                        player.sendMessage(Utils.msgColor("&c[传承]传承异兽等级不满足要求(>=35)。"));
                    }
                } else {
                    player.sendMessage(Utils.msgColor("&c[传承]请放入正确的异兽。"));
                }
            }
            if (e.getRawSlot() == 33){
                ItemStack inheritedpet = inv.getTopInventory().getItem(11);
                ItemStack inheritpet = inv.getTopInventory().getItem(15);
                if (inheritpet == null || inheritpet.getItemMeta() == null || !inheritpet.getItemMeta().hasDisplayName()){return;}
                if (inheritedpet == null || inheritedpet.getItemMeta() == null || !inheritedpet.getItemMeta().hasDisplayName()){return;}
                if (Utils.checkPet(inheritpet) && Utils.checkPet(inheritedpet)){
                    if (Integer.parseInt(NBTUtils.getStringTag(inheritpet,"Level")) >= 35){
                        if (Integer.parseInt(NBTUtils.getStringTag(inheritedpet,"Level")) == 1){
                            if (Utils.checkInheritSS(inheritedpet)) {
                                if (Utils.checkQingseguoshi(player) >= 5 && Utils.checkKelongguoshi(player) >=1) {
                                    Utils.takeItem(player,"青涩果实",5);
                                    Utils.takeItem(player,"克隆果实",1);
                                    inheritedpet = Utils.petInherit(inheritedpet, inheritpet, player, 3);
                                    inv.getTopInventory().setItem(11, inheritedpet);
                                    inv.getTopInventory().remove(inheritpet);
                                } else {
                                    player.sendMessage(Utils.msgColor("&c[传承]道具不足。"));
                                }
                            } else {
                                player.sendMessage(Utils.msgColor("&c[传承]被传异兽已经不能继续传承了！"));
                            }
                        } else {
                            player.sendMessage(Utils.msgColor("&c[传承]被传承异兽等级不满足要求(=1)"));
                        }
                    } else {
                        player.sendMessage(Utils.msgColor("&c[传承]传承异兽等级不满足要求(>=35)。"));
                    }
                } else {
                    player.sendMessage(Utils.msgColor("&c[传承]请放入正确的异兽。"));
                }
            }
        }
    }


    @EventHandler
    public void OnClose(InventoryCloseEvent e){
        Player player = (Player)e.getPlayer();
        InventoryView inv = e.getView();
        if (inv.getTitle().equals(Menu.TITLE)){
            for(ItemStack item : inv.getTopInventory().getContents()) {
                // 判断item是不是空的物品
                if (item == null || item.getItemMeta() == null) {
                    continue;
                }
                // 获得物品堆的物品数据
                ItemMeta itemmeta = item.getItemMeta();
                if (itemmeta.getDisplayName() != null) {
                    if (!itemmeta.getDisplayName().contains("-") && !itemmeta.getDisplayName().contains("确认喂养")) {
                        player.getInventory().addItem(item);
                    }
                }
            }
        }
        if (inv.getTitle().equals(ImmolateMenu.TITLE)){
            for(ItemStack item : inv.getTopInventory().getContents()) {
                // 判断item是不是空的物品
                if (item == null || item.getItemMeta() == null) {
                    continue;
                }
                // 获得物品堆的物品数据
                ItemMeta itemmeta = item.getItemMeta();
                if (itemmeta.getDisplayName() != null) {
                    if (!itemmeta.getDisplayName().contains("-") && !itemmeta.getDisplayName().contains("确认献祭")) {
                        player.getInventory().addItem(item);
                    }
                } else {
                    player.getInventory().addItem(item);
                }
            }
        }
        if (inv.getTitle().equals(ReviveMenu.TITLE)){
            for(ItemStack item : inv.getTopInventory().getContents()) {
                // 判断item是不是空的物品
                if (item == null || item.getItemMeta() == null) {
                    continue;
                }
                // 获得物品堆的物品数据
                ItemMeta itemmeta = item.getItemMeta();
                if (itemmeta.getDisplayName() != null) {
                    if (!itemmeta.getDisplayName().contains("-") && !itemmeta.getDisplayName().contains("确认复活")) {
                        player.getInventory().addItem(item);
                    }
                } else {
                    player.getInventory().addItem(item);
                }
            }
        }
        if (inv.getTitle().equals(EvolveMenu.TITLE)){
            for(ItemStack item : inv.getTopInventory().getContents()) {
                // 判断item是不是空的物品
                if (item == null || item.getItemMeta() == null) {
                    continue;
                }
                // 获得物品堆的物品数据
                ItemMeta itemmeta = item.getItemMeta();
                if (itemmeta.getDisplayName() != null) {
                    if (!itemmeta.getDisplayName().contains("-") && !itemmeta.getDisplayName().contains("玩法介绍") &&
                            !itemmeta.getDisplayName().contains("开始随机进化") && !itemmeta.getDisplayName().contains("锁定力量进化") &&
                            !itemmeta.getDisplayName().contains("锁定敏捷进化") && !itemmeta.getDisplayName().contains("锁定体力进化") &&
                            !itemmeta.getDisplayName().contains("锁定智慧进化")) {
                        player.getInventory().addItem(item);
                    }
                } else {
                    player.getInventory().addItem(item);
                }
            }
        }
        if (inv.getTitle().equals(InheritMenu.TITLE)){
            for(ItemStack item : inv.getTopInventory().getContents()) {
                // 判断item是不是空的物品
                if (item == null || item.getItemMeta() == null) {
                    continue;
                }
                // 获得物品堆的物品数据
                ItemMeta itemmeta = item.getItemMeta();
                if (itemmeta.getDisplayName() != null) {
                    if (!itemmeta.getDisplayName().contains("-") && !itemmeta.getDisplayName().contains("确认传承") && !itemmeta.getDisplayName().contains("玩法介绍")) {
                        player.getInventory().addItem(item);
                    }
                } else {
                    player.getInventory().addItem(item);
                }
            }
        }
        if (inv.getTitle().equals(InheritMenu2.TITLE)){
            for(ItemStack item : inv.getTopInventory().getContents()) {
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
                        player.getInventory().addItem(item);
                    }
                } else {
                    player.getInventory().addItem(item);
                }
            }
        }
    }
}
