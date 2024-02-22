package com.rihejiu.nustarpet.pet.utils;

import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.random.pettalent.TalentReset;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static com.rihejiu.nustarpet.command.Utils.msgColor;

public class TalentUtils {
    /**
     * 培养天赋
     * @param petItem   宠物
     * @param player    玩家名
     */
    public static void petTalent(ItemStack petItem, Player player){
        if (petItem == null || petItem.getItemMeta() == null || !petItem.getItemMeta().hasDisplayName()){return;}
        ItemMeta petItemMeta = petItem.getItemMeta();
        String petid = NBTUtils.getStringTag(petItem,"PetId");
        List<String> petLore = petItemMeta.getLore();
        String newTalent = null;
        if (petid == null){return;}
        switch (petid){
            case "尖牙蝙蝠":
                for (int i = 0;i < petLore.size();i++){
                    String line = petLore.get(i);
                    if (line.contains("携带天赋")){
                        // 记录下原始天赋列表
                        String talentLine = petLore.get(i+1);
                        // 遍历该宠物所有天赋
                        for (String talent: TalentReset.shiXue){
                            // 如果原始天赋不包含则新增该天赋后退出该循环
                            if (!talentLine.contains(talent)){
                                talentLine = talentLine + talent;
                                newTalent = talent;
                                break;
                            }
                        }
                        // 将新天赋写回lore
                        petLore.set(i+1,talentLine);
                        petItemMeta.setLore(petLore);
                        petItem.setItemMeta(petItemMeta);
                    }
                }
                if (newTalent == null){
                    player.sendMessage(msgColor("&c[培养]这只异兽已经掌握了全部的种族天赋！"));
                } else {
                    player.sendMessage(msgColor("&3[培养]你的异兽觉醒了新天赋") + newTalent);
                }
                break;
            case "沙王蜘蛛":
                for (int i = 0;i < petLore.size();i++){
                    String line = petLore.get(i);
                    if (line.contains("携带天赋")){
                        String talentLine = petLore.get(i+1);
                        for (String talent: TalentReset.zhuWang){
                            if (!talentLine.contains(talent)){
                                talentLine = talentLine + talent;
                                newTalent = talent;
                                break;
                            }
                        }
                        petLore.set(i+1,talentLine);
                        petItemMeta.setLore(petLore);
                        petItem.setItemMeta(petItemMeta);
                    }
                }
                if (newTalent == null){
                    player.sendMessage(msgColor("&c[培养]这只异兽已经掌握了全部的种族天赋！"));
                } else {
                    player.sendMessage(msgColor("&3[培养]你的异兽觉醒了新天赋") + newTalent);
                }
                break;
            case "萨满阿多":
                for (int i = 0;i < petLore.size();i++){
                    String line = petLore.get(i);
                    if (line.contains("携带天赋")){
                        String talentLine = petLore.get(i+1);
                        for (String talent: TalentReset.saMan){
                            if (!talentLine.contains(talent)){
                                talentLine = talentLine + talent;
                                newTalent = talent;
                                break;
                            }
                        }
                        petLore.set(i+1,talentLine);
                        petItemMeta.setLore(petLore);
                        petItem.setItemMeta(petItemMeta);
                    }
                }
                if (newTalent == null){
                    player.sendMessage(msgColor("&c[培养]这只异兽已经掌握了全部的种族天赋！"));
                } else {
                    player.sendMessage(msgColor("&3[培养]你的异兽觉醒了新天赋") + newTalent);
                }
                break;
            case "通红之翼":
                for (int i = 0;i < petLore.size();i++){
                    String line = petLore.get(i);
                    if (line.contains("携带天赋")){
                        String talentLine = petLore.get(i+1);
                        for (String talent: TalentReset.feiE){
                            if (!talentLine.contains(talent)){
                                talentLine = talentLine + talent;
                                newTalent = talent;
                                break;
                            }
                        }
                        petLore.set(i+1,talentLine);
                        petItemMeta.setLore(petLore);
                        petItem.setItemMeta(petItemMeta);
                    }
                }
                if (newTalent == null){
                    player.sendMessage(msgColor("&c[培养]这只异兽已经掌握了全部的种族天赋！"));
                } else {
                    player.sendMessage(msgColor("&3[培养]你的异兽觉醒了新天赋") + newTalent);
                }
                break;
            case "战魂":
                for (int i = 0;i < petLore.size();i++){
                    String line = petLore.get(i);
                    if (line.contains("携带天赋")){
                        String talentLine = petLore.get(i+1);
                        for (String talent: TalentReset.zhanHun){
                            if (!talentLine.contains(talent)){
                                talentLine = talentLine + talent;
                                newTalent = talent;
                                break;
                            }
                        }
                        petLore.set(i+1,talentLine);
                        petItemMeta.setLore(petLore);
                        petItem.setItemMeta(petItemMeta);
                    }
                }
                if (newTalent == null){
                    player.sendMessage(msgColor("&c[培养]这只异兽已经掌握了全部的种族天赋！"));
                } else {
                    player.sendMessage(msgColor("&3[培养]你的异兽觉醒了新天赋") + newTalent);
                }
                break;
            case "恐狼":
                for (int i = 0;i < petLore.size();i++){
                    String line = petLore.get(i);
                    if (line.contains("携带天赋")){
                        String talentLine = petLore.get(i+1);
                        for (String talent: TalentReset.kongLang){
                            if (!talentLine.contains(talent)){
                                talentLine = talentLine + talent;
                                newTalent = talent;
                                break;
                            }
                        }
                        petLore.set(i+1,talentLine);
                        petItemMeta.setLore(petLore);
                        petItem.setItemMeta(petItemMeta);
                    }
                }
                if (newTalent == null){
                    player.sendMessage(msgColor("&c[培养]这只异兽已经掌握了全部的种族天赋！"));
                } else {
                    player.sendMessage(msgColor("&3[培养]你的异兽觉醒了新天赋") + newTalent);
                }
                break;
            case "战马":
                for (int i = 0;i < petLore.size();i++){
                    String line = petLore.get(i);
                    if (line.contains("携带天赋")){
                        String talentLine = petLore.get(i+1);
                        for (String talent: TalentReset.zhanMa){
                            if (!talentLine.contains(talent)){
                                talentLine = talentLine + talent;
                                newTalent = talent;
                                break;
                            }
                        }
                        petLore.set(i+1,talentLine);
                        petItemMeta.setLore(petLore);
                        petItem.setItemMeta(petItemMeta);
                    }
                }
                if (newTalent == null){
                    player.sendMessage(msgColor("&c[培养]这只异兽已经掌握了全部的种族天赋！"));
                } else {
                    player.sendMessage(msgColor("&3[培养]你的异兽觉醒了新天赋") + newTalent);
                }
                break;
        }
    }
    /**
     * 检测异兽天赋是否已经全部觉醒
     * @param petItem   异兽
     * @param player    玩家
     * @return  返回是否全部觉醒
     */
    public static boolean checkPetTalent(ItemStack petItem,Player player){
        if (petItem == null || petItem.getItemMeta() == null || !petItem.getItemMeta().hasDisplayName()){return false;}
        ItemMeta petItemMeta = petItem.getItemMeta();
        String petid = NBTUtils.getStringTag(petItem,"PetId");
        List<String> petLore = petItemMeta.getLore();
        String newTalent = null;
        if (petid == null){return false;}
        switch (petid){
            case "尖牙蝙蝠":
                for (int i = 0;i < petLore.size();i++){
                    String line = petLore.get(i);
                    if (line.contains("携带天赋")){
                        // 记录下原始天赋列表
                        String talentLine = petLore.get(i+1);
                        // 遍历该宠物所有天赋
                        for (String talent: TalentReset.shiXue){
                            // 如果原始天赋不包含则新增该天赋后退出该循环
                            if (!talentLine.contains(talent)){
                                newTalent = talent;
                                break;
                            }
                        }
                    }
                }
                if (newTalent == null){
                    player.sendMessage(msgColor("&c[培养]这只异兽已经掌握了全部的种族天赋！"));
                    return false;
                } else {
                    return true;
                }
            case "沙王蜘蛛":
                for (int i = 0;i < petLore.size();i++){
                    String line = petLore.get(i);
                    if (line.contains("携带天赋")){
                        // 记录下原始天赋列表
                        String talentLine = petLore.get(i+1);
                        // 遍历该宠物所有天赋
                        for (String talent: TalentReset.zhuWang){
                            // 如果原始天赋不包含则新增该天赋后退出该循环
                            if (!talentLine.contains(talent)){
                                newTalent = talent;
                                break;
                            }
                        }
                    }
                }
                if (newTalent == null){
                    player.sendMessage(msgColor("&c[培养]这只异兽已经掌握了全部的种族天赋！"));
                    return false;
                } else {
                    return true;
                }
            case "萨满阿多":
                for (int i = 0;i < petLore.size();i++){
                    String line = petLore.get(i);
                    if (line.contains("携带天赋")){
                        // 记录下原始天赋列表
                        String talentLine = petLore.get(i+1);
                        // 遍历该宠物所有天赋
                        for (String talent: TalentReset.saMan){
                            // 如果原始天赋不包含则新增该天赋后退出该循环
                            if (!talentLine.contains(talent)){
                                newTalent = talent;
                                break;
                            }
                        }
                    }
                }
                if (newTalent == null){
                    player.sendMessage(msgColor("&c[培养]这只异兽已经掌握了全部的种族天赋！"));
                    return false;
                } else {
                    return true;
                }
            case "通红之翼":
                for (int i = 0;i < petLore.size();i++){
                    String line = petLore.get(i);
                    if (line.contains("携带天赋")){
                        // 记录下原始天赋列表
                        String talentLine = petLore.get(i+1);
                        // 遍历该宠物所有天赋
                        for (String talent: TalentReset.feiE){
                            // 如果原始天赋不包含则新增该天赋后退出该循环
                            if (!talentLine.contains(talent)){
                                newTalent = talent;
                                break;
                            }
                        }
                    }
                }
                if (newTalent == null){
                    player.sendMessage(msgColor("&c[培养]这只异兽已经掌握了全部的种族天赋！"));
                    return false;
                } else {
                    return true;
                }
            case "战魂":
                for (int i = 0;i < petLore.size();i++){
                    String line = petLore.get(i);
                    if (line.contains("携带天赋")){
                        // 记录下原始天赋列表
                        String talentLine = petLore.get(i+1);
                        // 遍历该宠物所有天赋
                        for (String talent: TalentReset.zhanHun){
                            // 如果原始天赋不包含则新增该天赋后退出该循环
                            if (!talentLine.contains(talent)){
                                newTalent = talent;
                                break;
                            }
                        }
                    }
                }
                if (newTalent == null){
                    player.sendMessage(msgColor("&c[培养]这只异兽已经掌握了全部的种族天赋！"));
                    return false;
                } else {
                    return true;
                }
            case "恐狼":
                for (int i = 0;i < petLore.size();i++){
                    String line = petLore.get(i);
                    if (line.contains("携带天赋")){
                        // 记录下原始天赋列表
                        String talentLine = petLore.get(i+1);
                        // 遍历该宠物所有天赋
                        for (String talent: TalentReset.kongLang){
                            // 如果原始天赋不包含则新增该天赋后退出该循环
                            if (!talentLine.contains(talent)){
                                newTalent = talent;
                                break;
                            }
                        }
                    }
                }
                if (newTalent == null){
                    player.sendMessage(msgColor("&c[培养]这只异兽已经掌握了全部的种族天赋！"));
                    return false;
                } else {
                    return true;
                }
            case "战马":
                for (int i = 0;i < petLore.size();i++){
                    String line = petLore.get(i);
                    if (line.contains("携带天赋")){
                        // 记录下原始天赋列表
                        String talentLine = petLore.get(i+1);
                        // 遍历该宠物所有天赋
                        for (String talent: TalentReset.zhanMa){
                            // 如果原始天赋不包含则新增该天赋后退出该循环
                            if (!talentLine.contains(talent)){
                                newTalent = talent;
                                break;
                            }
                        }
                    }
                }
                if (newTalent == null){
                    player.sendMessage(msgColor("&c[培养]这只异兽已经掌握了全部的种族天赋！"));
                    return false;
                } else {
                    return true;
                }
        }
        return false;
    }
    /**
     * 天赋玩法木牌
     * @param petItem   宠物
     * @param player    玩家名
     * @return  返回木牌
     */
    public static ItemMeta talentSign(ItemStack petItem, Player player){
        ItemStack sign = new ItemStack(Material.SIGN);
        ItemMeta signMeta = sign.getItemMeta();
        signMeta.setDisplayName(ChatColor.YELLOW + "玩法介绍");
        List<String> lore = new ArrayList<>();
        lore.add(msgColor("&f你即将培养") + petItem.getItemMeta().getDisplayName() + "，请核对材料信息后确认");
        lore.add(msgColor("&f培养:"));
        lore.add(msgColor("&8&m一一一一一一一一一一一一一一一一"));
        if (chooseTalentPlan(petItem)) {
            lore.add(msgColor("&f兽魂: ") + petTalentShouHun(petItem) + "/" + Utils.checkItem(player,"兽魂"));
            lore.add(msgColor("&f天赋精华: 4") + "/" + checkTalent(player));
            lore.add(msgColor("&f成功率: ") + petTalentChance(petItem) * 100 + "%");
        } else {
            lore.add(msgColor("&f兽魂: ") + petTalentShouHun(petItem) + "/" + Utils.checkItem(player,"兽魂"));
            lore.add(msgColor("&f高级天赋精华: 2") + "/" + checkAdvancedTalent(player));
            lore.add(msgColor("&f成功率: ") + petTalentChance(petItem) * 100 + "%");
        }
        lore.add(msgColor("&8&m一一一一一一一一一一一一一一一一"));
        signMeta.setLore(lore);
        return signMeta;
    }
    /**
     * 两种方案，一个是普通天赋精华一个是高级天赋精华
     * @param petItem  宠物
     * @return  返回方案
     */
    public static boolean chooseTalentPlan(ItemStack petItem){
        if (petItem == null || petItem.getItemMeta() == null || !petItem.getItemMeta().hasDisplayName()){return true;}
        String petid = NBTUtils.getStringTag(petItem,"PetId");
        if (petid == null){return true;}
        switch (petid){
            case "尖牙蝙蝠":
            case "沙王蜘蛛":
            case "萨满阿多":
            case "通红之翼":
                return true;
            case "战魂":
            case "恐狼":
            case "战马":
                return false;
        }
        return true;
    }
    /**
     * 返回进化天赋消耗的兽魂数量
     * @param petItem   宠物
     * @return  返回数量
     */
    public static int petTalentShouHun(ItemStack petItem){
        if (petItem == null || petItem.getItemMeta() == null || !petItem.getItemMeta().hasDisplayName()){return 0;}
        String petid = NBTUtils.getStringTag(petItem,"PetId");
        if (petid == null){return 0;}
        switch (petid){
            case "尖牙蝙蝠":
                return 1;
            case "沙王蜘蛛":
                return 2;
            case "萨满阿多":
                return 3;
            case "通红之翼":
                return 4;
            case "战魂":
                return 6;
            case "恐狼":
                return 5;
            case "战马":
                return 7;
        }
        return 0;
    }
    /**
     * 返回天赋进化的概率
     * @param petItem   宠物
     * @return  返回概率
     */
    public static double petTalentChance(ItemStack petItem){
        if (petItem == null || petItem.getItemMeta() == null || !petItem.getItemMeta().hasDisplayName()){return 0;}
        String petid = NBTUtils.getStringTag(petItem,"PetId");
        if (petid == null){return 0;}
        switch (petid){
            case "尖牙蝙蝠":
            case "沙王蜘蛛":
            case "萨满阿多":
            case "通红之翼":
                return 0.5;
            case "战魂":
                return 0.4;
            case "恐狼":
                return 0.45;
            case "战马":
                return 0.35;
        }
        return 0;
    }
    public static int checkTalent(Player player){
        int count = 0;
        Inventory inv = player.getInventory();
        for (ItemStack itemStack : inv.getContents()) {
            if (itemStack != null) { // 检查物品是否为空
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().contains("天赋精华")) {
                    count += itemStack.getAmount();
                }
            }
        }
        return count;
    }
    public static int checkAdvancedTalent(Player player){
        int count = 0;
        Inventory inv = player.getInventory();
        for (ItemStack itemStack : inv.getContents()) {
            if (itemStack != null) { // 检查物品是否为空
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().contains("高级精华")) {
                    count += itemStack.getAmount();
                }
            }
        }
        return count;
    }
}
