package com.rihejiu.nustarpet.listens;

import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.menu.event.ClickEvent;
import com.rihejiu.nustarpet.menu.event.CloseEvent;
import com.rihejiu.nustarpet.pet.utils.LevelUpUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LevelUpListener implements Listener {
    @EventHandler
    public void click(ClickEvent event){
        if (event.getMenuType().equals("异兽升级")){
            int rawSlot = event.getOrigin().getRawSlot();
            int[] slot = {0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,46,47,48,49,50,51,52,53};
            for (int j : slot) {
                if (rawSlot == j) {
                    event.setCancelled(true);
                }
            }
            if (rawSlot == 89) event.setCancelled(true);
            if (rawSlot < 0 || rawSlot > event.getOrigin().getInventory().getSize() || event.getCurrentItem() == null) return;
            Player player = event.getPlayer();
            Inventory inv = event.getOrigin().getView().getTopInventory();
            ItemStack pet = player.getInventory().getItem(8);
            if (!Utils.checkPet(pet)){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&f[异兽]你必须先出战一只异兽！"));
                return;
            }
            if (rawSlot == 49){
                levelUp(inv,player);
            }
        }
    }
    public void levelUp(Inventory inv,Player player){
        int amount = 0;
        int sumExp = 0;
        outLoop:
        for(ItemStack item : inv.getContents()){
            if (item == null || item.getItemMeta() == null){
                continue;
            }
            ItemMeta itemmeta = item.getItemMeta();
            int flag = 0;
            int flag2 = 0;
            int exp = 0;
            if (itemmeta.hasDisplayName() && itemmeta.hasLore() && !itemmeta.getDisplayName().equals("-")){
                for (int i = 0; i < itemmeta.getLore().size(); i++) {
                    String lore = itemmeta.getLore().get(i);
                    if (lore.contains("喂养异兽")){
                        flag2 = 1;
                    }
                    if (lore.contains("售价: ") && (flag2 == 1)){
                        String[] split = lore.split("\\s*:\\s*");
                        if (split.length == 2){
                            exp = Integer.parseInt(split[1]);
                            flag = 1;
                            break;
                        }
                    }
                }
                // 检测宠物等级是否大于玩家等级，如果大于则退出整个循环
                if ( flag2 == 1 && flag == 1){
                    while (item.getAmount() != 0){
                        int level = Integer.parseInt(NBTUtils.getStringTag(player.getInventory().getItem(8),"Level"));
                        if (player.getLevel() > level) {
                            player.getInventory().setItem(8, LevelUpUtils.petLevelUp(player, exp, player.getInventory().getItem(8)));
                            item.setAmount(item.getAmount() - 1);
                            amount += 1;
                            sumExp += exp;
                        } else {
                            player.sendMessage(Utils.msgColor("&c[异兽]异兽等级不能大于角色等级！"));
                            break outLoop;
                        }
                    }
                }
            }
        }
        if (amount != 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[异兽]喂养了" + amount + "个物品，异兽获得了" + sumExp + "点经验"));
            //player.getInventory().setItem(8, LevelUpUtils.petLevelUp(player, sumExp, Identify.getPetStack(player, 8)));
            Utils.command("chs run " + "任务检测.指引喂养异兽.ks " + player.getName());
        }
    }
    @EventHandler
    public void close(CloseEvent event){
        if (event.getMenuType().equals("异兽升级")){
            for(ItemStack item : event.getContents()) {
                // 判断item是不是空的物品
                if (item == null || item.getItemMeta() == null) {
                    continue;
                }
                // 获得物品堆的物品数据
                ItemMeta itemmeta = item.getItemMeta();
                if (itemmeta.getDisplayName() != null) {
                    if (!itemmeta.getDisplayName().contains("-") && !itemmeta.getDisplayName().contains("确认喂养")) {
                        event.getPlayer().getInventory().addItem(item);
                    }
                }
            }
        }
    }
}
