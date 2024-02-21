package com.rihejiu.nustarpet.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;

public class Utils {
    public static boolean shouldExecute(double probability) {
        Random random = new Random();
        return random.nextDouble() < probability; // 小于 probability 时返回 true，大于等于时返回 false
    }
    public static void command(String command){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command);
    }
    public static int getPetExp(String level){
        int petlevel = Integer.parseInt(level);
        int numa = 0;
        for (int i = 1;i<petlevel;i++){
            int num = 110*i*i+25*i+30;
            numa += num;
        }
        return numa;
    }
    public static ItemMeta getPet(Player player, int slot){
        Inventory inv = player.getInventory();
        if (inv.getItem(slot) != null) {
            return inv.getItem(slot).getItemMeta();
        }
        return null;
    }
    /**
     * 取走物品
     * @param player    玩家名
     * @param item  物品名称
     * @param amount    物品数量
     * @return  是否执行成功
     */
    public static boolean takeItem(Player player,String item,int amount){
        Inventory inv = player.getInventory();
        int amount2 = 0;
        for (ItemStack itemStack : inv.getContents()){
            if (itemStack != null) {
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().contains(item)){
                    int itemAmount = itemStack.getAmount();
                    if (itemAmount >= amount){
                        itemStack.setAmount(itemAmount - amount);
                        return true;
                    } else {
                        amount2 += itemAmount;
                    }
                }
            }
        }
        if (amount2 >= amount){
            for (ItemStack itemStack : inv.getContents()){
                if (itemStack != null){
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().contains(item)){
                        int itemAmount = itemStack.getAmount();
                        if (amount >= itemAmount) {
                            amount -= itemAmount;
                            itemStack.setAmount(0);
                        } else {
                            itemStack.setAmount(itemAmount-amount);
                            break;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
    public static String msgColor(String msg){
        return ChatColor.translateAlternateColorCodes('&',msg);
    }

    /**
     * 检测物品是否是宠物
     * @param item  物品
     * @return  是否
     */
    public static boolean checkPet(ItemStack item){
        if (item == null || item.getItemMeta() == null || !item.getItemMeta().hasDisplayName()){return false;}
        String petid = NBTUtils.getStringTag(item,"PetId");
        if (petid == null){return false;}
        switch (petid){
            case "尖牙蝙蝠":
            case "沙王蜘蛛":
            case "萨满阿多":
            case "通红之翼":
            case "战魂":
            case "恐狼":
            case "战马":
                return true;
        }
        return false;
    }
    public static int checkKelongguoshi(Player player){
        int count = 0;
        Inventory inv = player.getInventory();
        for (ItemStack itemStack : inv.getContents()) {
            if (itemStack != null) { // 检查物品是否为空
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().contains("克隆果实")) {
                    count += itemStack.getAmount();
                }
            }
        }
        return count;
    }
    public static int checkQingseguoshi(Player player){
        int count = 0;
        Inventory inv = player.getInventory();
        for (ItemStack itemStack : inv.getContents()) {
            if (itemStack != null) { // 检查物品是否为空
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().contains("青涩果实")) {
                    count += itemStack.getAmount();
                }
            }
        }
        return count;
    }
    public static int checkShouhun(Player player){
        int count = 0;
        Inventory inv = player.getInventory();
        for (ItemStack itemStack: inv .getContents()){
            if (itemStack != null){
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().contains("兽魂")){
                    count += itemStack.getAmount();
                }
            }
        }
        return count;
    }
    public static ItemMeta buildSign(ItemStack sign,List<String> lore){
        ItemMeta signMeta = sign.getItemMeta();
        signMeta.setLore(lore);
        return signMeta;
    }
    public static String lineColor(String line){
        return line.replaceAll("&","§");
    }
    public static boolean checkNull(ItemStack item){
        return item == null || item.getItemMeta() == null || !item.getItemMeta().hasDisplayName();
    }
}
