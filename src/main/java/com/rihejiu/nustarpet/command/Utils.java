package com.rihejiu.nustarpet.command;

import com.rihejiu.nustarpet.attribute.AddSourceAttribute;
import com.rihejiu.nustarpet.hook.NeigeHook;
import com.rihejiu.nustarpet.pet.Pet;
import com.rihejiu.nustarpet.pet.PetSS;
import com.rihejiu.nustarpet.pet.PetUUID;
import com.rihejiu.nustarpet.random.pettalent.TalentReset;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static boolean shouldExecute(double probability) {
        Random random = new Random();
        return random.nextDouble() < probability; // 小于 probability 时返回 true，大于等于时返回 false
    }
    public static boolean petTalent2(ItemStack petItem,Player player){
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
        }
        return false;
    }
    /**
     * 培养天赋
     * @param petItem   宠物
     * @param player    玩家名
     */
    public static void petTalent(ItemStack petItem,Player player){
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
        }
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
            lore.add(msgColor("&f兽魂: ") + petTalentShouHun(petItem) + "/" + checkShouhun(player));
            lore.add(msgColor("&f天赋精华: 4") + "/" + checkTalent(player));
            lore.add(msgColor("&f成功率: ") + petTalentChance(petItem) * 100 + "%");
        } else {
            lore.add(msgColor("&f兽魂: ") + petTalentShouHun(petItem) + "/" + checkShouhun(player));
            lore.add(msgColor("&f高级天赋精华: 2") + "/" + checkAdvencedTalent(player));
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
        }
        return 0;
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
    public static String getDisplayNameWithoutColor(ItemStack itemStack) {
        if (itemStack.hasItemMeta()) {
            ItemMeta meta = itemStack.getItemMeta();
            if (meta.hasDisplayName()) {
                // 获取显示名称
                String displayName = meta.getDisplayName();
                // 去除颜色代码
                return ChatColor.stripColor(displayName);
            }
        }
        return null; // 如果没有显示名称或没有 ItemMeta
    }
    public static void follow(Player player){
        ItemStack pet = player.getInventory().getItem(8);
        if (pet == null || pet.getItemMeta() == null || !pet.getItemMeta().hasDisplayName() || !pet.getItemMeta().hasLore()){
            player.sendMessage(msgColor("&c[异兽]当前装备的宠物不正确！"));
            return;
        }
        if (!checkPet(pet)){
            player.sendMessage(msgColor("&c[异兽]当前装备的宠物不正确！"));
            return;
        }
        String petName = getDisplayNameWithoutColor(pet);
        NBTUtils.setStringTag(pet,"Player",player.getName());
        if (petName == null){return;}
        switch (petName){
            case "尖牙蝙蝠":
                pet = NBTUtils.setStringTag(pet,"Uuid",PetUUID.jyUUID);
                break;
            case "沙王蜘蛛":
                pet = NBTUtils.setStringTag(pet,"Uuid",PetUUID.zzUUID);
                break;
            case "萨满阿多":
                pet = NBTUtils.setStringTag(pet,"Uuid",PetUUID.smUUID);
                break;
            case "通红之翼":
                pet = NBTUtils.setStringTag(pet,"Uuid",PetUUID.thUUID);
                break;
            case "恐狼":
                pet = NBTUtils.setStringTag(pet,"Uuid",PetUUID.klUUID);
                break;
            case "战魂":
                pet = NBTUtils.setStringTag(pet,"Uuid",PetUUID.zhUUID);
                break;
        }
        player.getInventory().setItem(8,pet);
        player.sendMessage(msgColor("&a[异兽]异兽正在跟随于你。"));
    }
    public static void unFollow(Player player){
        ItemStack pet = player.getInventory().getItem(8);
        if (pet == null || pet.getItemMeta() == null || !pet.getItemMeta().hasDisplayName() || !pet.getItemMeta().hasLore()){
            player.sendMessage(msgColor("&c[异兽]当前装备的宠物不正确！"));
            return;
        }
        if (!checkPet(pet)){
            player.sendMessage(msgColor("&c[异兽]当前装备的宠物不正确！"));
            return;
        }
        pet = NBTUtils.removeTag(pet,"Uuid");
        player.getInventory().setItem(8,pet);
        player.sendMessage(msgColor("&a[异兽]异兽不会跟随你了。"));
    }
    /**
     *  异兽传承逻辑
     * @param inheritedpet  被传承异兽
     * @param inheritpet    传承异兽
     * @param player    玩家名
     * @param attr  传承什么天赋
     * @return  返回传承后的异兽
     */
    public static ItemStack petInherit(ItemStack inheritedpet, ItemStack inheritpet, Player player, int attr){
        String inheritpetpower = NBTUtils.getStringTag(inheritpet,"力量");
        String inheritpetspeed = NBTUtils.getStringTag(inheritpet,"敏捷");
        String inheritpetwisdom = NBTUtils.getStringTag(inheritpet,"智慧");
        String inheritpetspirit = NBTUtils.getStringTag(inheritpet,"体力");
        String inheritpetlevel = NBTUtils.getStringTag(inheritpet,"Level");
        player.sendMessage(msgColor("&7-------------------------------"));
        switch (attr){
            case 1:
                player.sendMessage(msgColor("&f[传承]异兽传承完毕"));
                player.sendMessage(msgColor("&f力量: " + NBTUtils.getStringTag(inheritedpet,"力量") + "->" + inheritpetpower));
                player.sendMessage(msgColor("&f等级: 1->" + inheritpetlevel));
                inheritedpet = NBTUtils.setStringTag(inheritedpet,"力量",inheritpetpower);
                break;
            case 2:
                player.sendMessage(msgColor("&f[传承]异兽传承完毕"));
                player.sendMessage(msgColor("&f敏捷: " + NBTUtils.getStringTag(inheritedpet,"敏捷") + "->" + inheritpetspeed));
                player.sendMessage(msgColor("&f等级: 1->" + inheritpetlevel));
                inheritedpet = NBTUtils.setStringTag(inheritedpet,"敏捷",inheritpetspeed);
                break;
            case 3:
                player.sendMessage(msgColor("&f[传承]异兽传承完毕"));
                player.sendMessage(msgColor("&f智慧: " + NBTUtils.getStringTag(inheritedpet,"智慧") + "->" + inheritpetwisdom));
                player.sendMessage(msgColor("&f等级: 1->" + inheritpetlevel));
                inheritedpet = NBTUtils.setStringTag(inheritedpet,"智慧",inheritpetwisdom);
                break;
            case 4:
                player.sendMessage(msgColor("&f[传承]异兽传承完毕"));
                player.sendMessage(msgColor("&f体力: " + NBTUtils.getStringTag(inheritedpet,"体力") + "->" + inheritpetspirit));
                player.sendMessage(msgColor("&f等级: 1->" + inheritpetlevel));
                inheritedpet = NBTUtils.setStringTag(inheritedpet,"体力",inheritpetspirit);
                break;
        }
        int exp = Utils.getPetExp(NBTUtils.getStringTag(inheritpet,"Level"));
        inheritedpet = Pet.PetLevelUp(player,exp,inheritedpet);
        return inheritedpet;
    }

    /**
     *
     * @param evolvepet 进化宠物
     * @param newpet    进化宠物需要new一个新的宠物对象，将新宠物对象的天赋信息给进化宠物
     * @param player    玩家名字
     * @return  按理说不需要返回
     */
    public static ItemStack randomUnlockedPetTalent(ItemStack evolvepet, Pet newpet, Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
        int newevolution = evolution + 1;
        String oldpower = NBTUtils.getStringTag(evolvepet,"力量");
        String oldspeed = NBTUtils.getStringTag(evolvepet,"敏捷");
        String oldspirit = NBTUtils.getStringTag(evolvepet,"体力");
        String oldwisdom = NBTUtils.getStringTag(evolvepet,"智慧");
        ItemMeta evolvepetmeta = evolvepet.getItemMeta();
        List<String> lore = evolvepetmeta.getLore();
        int petlevel = Integer.parseInt(NBTUtils.getStringTag(evolvepet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            Matcher powermatch = AddSourceAttribute.pattern.matcher(lore.get(6));
            if (powermatch.find()) {
                lore.set(6, Utils.msgColor("&6力量: " + powermatch.group(1) + "+" + newpet.getTalentPowerNum() * petlevel + "[" + newpet.getTalentPower() + "]"));
                player.sendMessage(msgColor("&f[异兽]力量: " + oldpower + "->" + newpet.getTalentPower()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            Matcher speedmatch = AddSourceAttribute.pattern.matcher(lore.get(7));
            if (speedmatch.find()) {
                lore.set(7, Utils.msgColor("&6敏捷: " + speedmatch.group(1) + "+" + newpet.getTalentSpeedNum() * petlevel + "[" + newpet.getTalentSpeed() + "]"));
                player.sendMessage(msgColor("&f[异兽]敏捷: " + oldspeed + "->" + newpet.getTalentSpeed()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            Matcher spiritmatch = AddSourceAttribute.pattern.matcher(lore.get(8));
            if (spiritmatch.find()) {
                lore.set(8, Utils.msgColor("&6体力: " + spiritmatch.group(1) + "+" + newpet.getTalentSpiritNum() * petlevel + "[" + newpet.getTalentSpirit() + "]"));
                player.sendMessage(msgColor("&f[异兽]体力: " + oldspirit + "->" + newpet.getTalentSpirit()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            Matcher wisdommatch = AddSourceAttribute.pattern.matcher(lore.get(9));
            if (wisdommatch.find()) {
                lore.set(9, Utils.msgColor("&6智慧: " + wisdommatch.group(1) + "+" + newpet.getTalentWisdomNum() * petlevel + "[" + newpet.getTalentWisdom() + "]"));
                player.sendMessage(msgColor("&f[异兽]智慧: " + oldwisdom + "->" + newpet.getTalentWisdom()));
            }
        }
        evolvepetmeta.setLore(lore);
        evolvepet.setItemMeta(evolvepetmeta);
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "力量", newpet.getTalentPower());
        }
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "敏捷", newpet.getTalentSpeed());
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "体力", newpet.getTalentSpirit());
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "智慧", newpet.getTalentWisdom());
        }
        evolvepet = NBTUtils.setStringTag(evolvepet,"Evolution",String.valueOf(newevolution));
        return evolvepet;
    }

    /**
     *  锁定力量天赋
     * @param evolvepet 进化宠物
     * @param newpet    新的宠物对象
     * @param player    玩家名字
     * @return  按理说不需要返回
     */
    public static ItemStack randomPowerlockedPetTalent(ItemStack evolvepet,Pet newpet,Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
        int newevolution = evolution + 1;
        String oldspeed = NBTUtils.getStringTag(evolvepet,"敏捷");
        String oldspirit = NBTUtils.getStringTag(evolvepet,"体力");
        String oldwisdom = NBTUtils.getStringTag(evolvepet,"智慧");
        ItemMeta evolvepetmeta = evolvepet.getItemMeta();
        List<String> lore = evolvepetmeta.getLore();
        int petlevel = Integer.parseInt(NBTUtils.getStringTag(evolvepet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            Matcher speedmatch = AddSourceAttribute.pattern.matcher(lore.get(7));
            if (speedmatch.find()) {
                lore.set(7, Utils.msgColor("&6敏捷: " + speedmatch.group(1) + "+" + newpet.getTalentSpeedNum() * petlevel + "[" + newpet.getTalentSpeed() + "]"));
                player.sendMessage(msgColor("&f[异兽]敏捷: " + oldspeed + "->" + newpet.getTalentSpeed()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            Matcher spiritmatch = AddSourceAttribute.pattern.matcher(lore.get(8));
            if (spiritmatch.find()) {
                lore.set(8, Utils.msgColor("&6体力: " + spiritmatch.group(1) + "+" + newpet.getTalentSpiritNum() * petlevel + "[" + newpet.getTalentSpirit() + "]"));
                player.sendMessage(msgColor("&f[异兽]体力: " + oldspirit + "->" + newpet.getTalentSpirit()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            Matcher wisdommatch = AddSourceAttribute.pattern.matcher(lore.get(9));
            if (wisdommatch.find()) {
                lore.set(9, Utils.msgColor("&6智慧: " + wisdommatch.group(1) + "+" + newpet.getTalentWisdomNum() * petlevel + "[" + newpet.getTalentWisdom() + "]"));
                player.sendMessage(msgColor("&f[异兽]智慧: " + oldwisdom + "->" + newpet.getTalentWisdom()));
            }
        }
        evolvepetmeta.setLore(lore);
        evolvepet.setItemMeta(evolvepetmeta);
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "敏捷", newpet.getTalentSpeed());
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "体力", newpet.getTalentSpirit());
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "智慧", newpet.getTalentWisdom());
        }
        evolvepet = NBTUtils.setStringTag(evolvepet,"Evolution",String.valueOf(newevolution));
        return evolvepet;
    }
    /**
     *  锁定速度天赋
     * @param evolvepet 进化宠物
     * @param newpet    新的宠物对象
     * @param player    玩家名字
     * @return  按理说不需要返回
     */
    public static ItemStack randomSpeedlockedPetTalent(ItemStack evolvepet,Pet newpet,Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
        int newevolution = evolution + 1;
        String oldpower = NBTUtils.getStringTag(evolvepet,"力量");
        String oldspirit = NBTUtils.getStringTag(evolvepet,"体力");
        String oldwisdom = NBTUtils.getStringTag(evolvepet,"智慧");
        ItemMeta evolvepetmeta = evolvepet.getItemMeta();
        List<String> lore = evolvepetmeta.getLore();
        int petlevel = Integer.parseInt(NBTUtils.getStringTag(evolvepet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            Matcher powermatch = AddSourceAttribute.pattern.matcher(lore.get(6));
            if (powermatch.find()) {
                lore.set(6, Utils.msgColor("&6力量: " + powermatch.group(1) + "+" + newpet.getTalentPowerNum() * petlevel + "[" + newpet.getTalentPower() + "]"));
                player.sendMessage(msgColor("&f[异兽]力量: " + oldpower + "->" + newpet.getTalentPower()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            Matcher spiritmatch = AddSourceAttribute.pattern.matcher(lore.get(8));
            if (spiritmatch.find()) {
                lore.set(8, Utils.msgColor("&6体力: " + spiritmatch.group(1) + "+" + newpet.getTalentSpiritNum() * petlevel + "[" + newpet.getTalentSpirit() + "]"));
                player.sendMessage(msgColor("&f[异兽]体力: " + oldspirit + "->" + newpet.getTalentSpirit()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            Matcher wisdommatch = AddSourceAttribute.pattern.matcher(lore.get(9));
            if (wisdommatch.find()) {
                lore.set(9, Utils.msgColor("&6智慧: " + wisdommatch.group(1) + "+" + newpet.getTalentWisdomNum() * petlevel + "[" + newpet.getTalentWisdom() + "]"));
                player.sendMessage(msgColor("&f[异兽]智慧: " + oldwisdom + "->" + newpet.getTalentWisdom()));
            }
        }
        evolvepetmeta.setLore(lore);
        evolvepet.setItemMeta(evolvepetmeta);
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "力量", newpet.getTalentPower());
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "体力", newpet.getTalentSpirit());
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "智慧", newpet.getTalentWisdom());
        }
        evolvepet = NBTUtils.setStringTag(evolvepet,"Evolution",String.valueOf(newevolution));
        return evolvepet;
    }
    /**
     *  锁定体力天赋
     * @param evolvepet 进化宠物
     * @param newpet    新的宠物对象
     * @param player    玩家名字
     * @return  按理说不需要返回
     */
    public static ItemStack randomSpiritlockedPetTalent(ItemStack evolvepet,Pet newpet,Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
        int newevolution = evolution + 1;
        String oldpower = NBTUtils.getStringTag(evolvepet,"力量");
        String oldspeed = NBTUtils.getStringTag(evolvepet,"敏捷");
        String oldwisdom = NBTUtils.getStringTag(evolvepet,"智慧");
        ItemMeta evolvepetmeta = evolvepet.getItemMeta();
        List<String> lore = evolvepetmeta.getLore();
        int petlevel = Integer.parseInt(NBTUtils.getStringTag(evolvepet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            Matcher powermatch = AddSourceAttribute.pattern.matcher(lore.get(6));
            if (powermatch.find()) {
                lore.set(6, Utils.msgColor("&6力量: " + powermatch.group(1) + "+" + newpet.getTalentPowerNum() * petlevel + "[" + newpet.getTalentPower() + "]"));
                player.sendMessage(msgColor("&f[异兽]力量: " + oldpower + "->" + newpet.getTalentPower()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            Matcher speedmatch = AddSourceAttribute.pattern.matcher(lore.get(7));
            if (speedmatch.find()) {
                lore.set(7, Utils.msgColor("&6敏捷: " + speedmatch.group(1) + "+" + newpet.getTalentSpeedNum() * petlevel + "[" + newpet.getTalentSpeed() + "]"));
                player.sendMessage(msgColor("&f[异兽]敏捷: " + oldspeed + "->" + newpet.getTalentSpeed()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            Matcher wisdommatch = AddSourceAttribute.pattern.matcher(lore.get(9));
            if (wisdommatch.find()) {
                lore.set(9, Utils.msgColor("&6智慧: " + wisdommatch.group(1) + "+" + newpet.getTalentWisdomNum() * petlevel + "[" + newpet.getTalentWisdom() + "]"));
                player.sendMessage(msgColor("&f[异兽]智慧: " + oldwisdom + "->" + newpet.getTalentWisdom()));
            }
        }
        evolvepetmeta.setLore(lore);
        evolvepet.setItemMeta(evolvepetmeta);
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "力量", newpet.getTalentPower());
        }
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "敏捷", newpet.getTalentSpeed());
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "智慧", newpet.getTalentWisdom());
        }
        evolvepet = NBTUtils.setStringTag(evolvepet,"Evolution",String.valueOf(newevolution));
        return evolvepet;
    }
    /**
     *  锁定智力天赋
     * @param evolvepet 进化宠物
     * @param newpet    新的宠物对象
     * @param player    玩家名字
     * @return  按理说不需要返回
     */
    public static ItemStack randomWisdomlockedPetTalent(ItemStack evolvepet,Pet newpet,Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
        int newevolution = evolution + 1;
        String oldpower = NBTUtils.getStringTag(evolvepet,"力量");
        String oldspeed = NBTUtils.getStringTag(evolvepet,"敏捷");
        String oldspirit = NBTUtils.getStringTag(evolvepet,"体力");
        ItemMeta evolvepetmeta = evolvepet.getItemMeta();
        List<String> lore = evolvepetmeta.getLore();
        int petlevel = Integer.parseInt(NBTUtils.getStringTag(evolvepet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            Matcher powermatch = AddSourceAttribute.pattern.matcher(lore.get(6));
            if (powermatch.find()) {
                lore.set(6, Utils.msgColor("&6力量: " + powermatch.group(1) + "+" + newpet.getTalentPowerNum() * petlevel + "[" + newpet.getTalentPower() + "]"));
                player.sendMessage(msgColor("&f[异兽]力量: " + oldpower + "->" + newpet.getTalentPower()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            Matcher speedmatch = AddSourceAttribute.pattern.matcher(lore.get(7));
            if (speedmatch.find()) {
                lore.set(7, Utils.msgColor("&6敏捷: " + speedmatch.group(1) + "+" + newpet.getTalentSpeedNum() * petlevel + "[" + newpet.getTalentSpeed() + "]"));
                player.sendMessage(msgColor("&f[异兽]敏捷: " + oldspeed + "->" + newpet.getTalentSpeed()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            Matcher spiritmatch = AddSourceAttribute.pattern.matcher(lore.get(8));
            if (spiritmatch.find()) {
                lore.set(8, Utils.msgColor("&6体力: " + spiritmatch.group(1) + "+" + newpet.getTalentSpiritNum() * petlevel + "[" + newpet.getTalentSpirit() + "]"));
                player.sendMessage(msgColor("&f[异兽]体力: " + oldspirit + "->" + newpet.getTalentSpirit()));
            }
        }
        evolvepetmeta.setLore(lore);
        evolvepet.setItemMeta(evolvepetmeta);
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "力量", newpet.getTalentPower());
        }
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "敏捷", newpet.getTalentSpeed());
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "体力", newpet.getTalentSpirit());
        }
        evolvepet = NBTUtils.setStringTag(evolvepet,"Evolution",String.valueOf(newevolution));
        return evolvepet;
    }
    public static ItemStack randomUnlockedPetTalent(ItemStack evolvepet, PetSS newpet,Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
        int newevolution = evolution + 1;
        String oldpower = NBTUtils.getStringTag(evolvepet,"力量");
        String oldspeed = NBTUtils.getStringTag(evolvepet,"敏捷");
        String oldspirit = NBTUtils.getStringTag(evolvepet,"体力");
        String oldwisdom = NBTUtils.getStringTag(evolvepet,"智慧");
        ItemMeta evolvepetmeta = evolvepet.getItemMeta();
        List<String> lore = evolvepetmeta.getLore();
        int petlevel = Integer.parseInt(NBTUtils.getStringTag(evolvepet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            Matcher powermatch = AddSourceAttribute.pattern.matcher(lore.get(6));
            if (powermatch.find()) {
                lore.set(6, Utils.msgColor("&6力量: " + powermatch.group(1) + "+" + newpet.getTalentPowerNum() * petlevel + "[" + newpet.getTalentPower() + "]"));
                player.sendMessage(msgColor("&f[异兽]力量: " + oldpower + "->" + newpet.getTalentPower()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            Matcher speedmatch = AddSourceAttribute.pattern.matcher(lore.get(7));
            if (speedmatch.find()) {
                lore.set(7, Utils.msgColor("&6敏捷: " + speedmatch.group(1) + "+" + newpet.getTalentSpeedNum() * petlevel + "[" + newpet.getTalentSpeed() + "]"));
                player.sendMessage(msgColor("&f[异兽]敏捷: " + oldspeed + "->" + newpet.getTalentSpeed()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            Matcher spiritmatch = AddSourceAttribute.pattern.matcher(lore.get(8));
            if (spiritmatch.find()) {
                lore.set(8, Utils.msgColor("&6体力: " + spiritmatch.group(1) + "+" + newpet.getTalentSpiritNum() * petlevel + "[" + newpet.getTalentSpirit() + "]"));
                player.sendMessage(msgColor("&f[异兽]体力: " + oldspirit + "->" + newpet.getTalentSpirit()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            Matcher wisdommatch = AddSourceAttribute.pattern.matcher(lore.get(9));
            if (wisdommatch.find()) {
                lore.set(9, Utils.msgColor("&6智慧: " + wisdommatch.group(1) + "+" + newpet.getTalentWisdomNum() * petlevel + "[" + newpet.getTalentWisdom() + "]"));
                player.sendMessage(msgColor("&f[异兽]智慧: " + oldwisdom + "->" + newpet.getTalentWisdom()));
            }
        }
        evolvepetmeta.setLore(lore);
        evolvepet.setItemMeta(evolvepetmeta);
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "力量", newpet.getTalentPower());
        }
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "敏捷", newpet.getTalentSpeed());
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "体力", newpet.getTalentSpirit());
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "智慧", newpet.getTalentWisdom());
        }
        evolvepet = NBTUtils.setStringTag(evolvepet,"Evolution",String.valueOf(newevolution));
        return evolvepet;
    }

    /**
     * 重载方法，当该宠物有SS天赋时
     * @param evolvepet 进化宠物
     * @param newpet    新的宠物对象
     * @param player    玩家名
     * @return  返回宠物
     */
    public static ItemStack randomPowerlockedPetTalent(ItemStack evolvepet, PetSS newpet, Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
        int newevolution = evolution + 1;
        String oldspeed = NBTUtils.getStringTag(evolvepet,"敏捷");
        String oldspirit = NBTUtils.getStringTag(evolvepet,"体力");
        String oldwisdom = NBTUtils.getStringTag(evolvepet,"智慧");
        ItemMeta evolvepetmeta = evolvepet.getItemMeta();
        List<String> lore = evolvepetmeta.getLore();
        int petlevel = Integer.parseInt(NBTUtils.getStringTag(evolvepet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            Matcher speedmatch = AddSourceAttribute.pattern.matcher(lore.get(7));
            if (speedmatch.find()) {
                lore.set(7, Utils.msgColor("&6敏捷: " + speedmatch.group(1) + "+" + newpet.getTalentSpeedNum() * petlevel + "[" + newpet.getTalentSpeed() + "]"));
                player.sendMessage(msgColor("&f[异兽]敏捷: " + oldspeed + "->" + newpet.getTalentSpeed()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            Matcher spiritmatch = AddSourceAttribute.pattern.matcher(lore.get(8));
            if (spiritmatch.find()) {
                lore.set(8, Utils.msgColor("&6体力: " + spiritmatch.group(1) + "+" + newpet.getTalentSpiritNum() * petlevel + "[" + newpet.getTalentSpirit() + "]"));
                player.sendMessage(msgColor("&f[异兽]体力: " + oldspirit + "->" + newpet.getTalentSpirit()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            Matcher wisdommatch = AddSourceAttribute.pattern.matcher(lore.get(9));
            if (wisdommatch.find()) {
                lore.set(9, Utils.msgColor("&6智慧: " + wisdommatch.group(1) + "+" + newpet.getTalentWisdomNum() * petlevel + "[" + newpet.getTalentWisdom() + "]"));
                player.sendMessage(msgColor("&f[异兽]智慧: " + oldwisdom + "->" + newpet.getTalentWisdom()));
            }
        }
        evolvepetmeta.setLore(lore);
        evolvepet.setItemMeta(evolvepetmeta);
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "敏捷", newpet.getTalentSpeed());
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "体力", newpet.getTalentSpirit());
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "智慧", newpet.getTalentWisdom());
        }
        evolvepet = NBTUtils.setStringTag(evolvepet,"Evolution",String.valueOf(newevolution));
        return evolvepet;
    }
    public static ItemStack randomSpeedlockedPetTalent(ItemStack evolvepet, PetSS newpet, Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
        int newevolution = evolution + 1;
        String oldpower = NBTUtils.getStringTag(evolvepet,"力量");
        String oldspirit = NBTUtils.getStringTag(evolvepet,"体力");
        String oldwisdom = NBTUtils.getStringTag(evolvepet,"智慧");
        ItemMeta evolvepetmeta = evolvepet.getItemMeta();
        List<String> lore = evolvepetmeta.getLore();
        int petlevel = Integer.parseInt(NBTUtils.getStringTag(evolvepet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            Matcher powermatch = AddSourceAttribute.pattern.matcher(lore.get(6));
            if (powermatch.find()) {
                lore.set(6, Utils.msgColor("&6力量: " + powermatch.group(1) + "+" + newpet.getTalentPowerNum() * petlevel + "[" + newpet.getTalentPower() + "]"));
                player.sendMessage(msgColor("&f[异兽]力量: " + oldpower + "->" + newpet.getTalentPower()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            Matcher spiritmatch = AddSourceAttribute.pattern.matcher(lore.get(8));
            if (spiritmatch.find()) {
                lore.set(8, Utils.msgColor("&6体力: " + spiritmatch.group(1) + "+" + newpet.getTalentSpiritNum() * petlevel + "[" + newpet.getTalentSpirit() + "]"));
                player.sendMessage(msgColor("&f[异兽]体力: " + oldspirit + "->" + newpet.getTalentSpirit()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            Matcher wisdommatch = AddSourceAttribute.pattern.matcher(lore.get(9));
            if (wisdommatch.find()) {
                lore.set(9, Utils.msgColor("&6智慧: " + wisdommatch.group(1) + "+" + newpet.getTalentWisdomNum() * petlevel + "[" + newpet.getTalentWisdom() + "]"));
                player.sendMessage(msgColor("&f[异兽]智慧: " + oldwisdom + "->" + newpet.getTalentWisdom()));
            }
        }
        evolvepetmeta.setLore(lore);
        evolvepet.setItemMeta(evolvepetmeta);
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "力量", newpet.getTalentPower());
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "体力", newpet.getTalentSpirit());
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "智慧", newpet.getTalentWisdom());
        }
        evolvepet = NBTUtils.setStringTag(evolvepet,"Evolution",String.valueOf(newevolution));
        return evolvepet;
    }
    public static ItemStack randomSpiritlockedPetTalent(ItemStack evolvepet, PetSS newpet, Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
        int newevolution = evolution + 1;
        String oldpower = NBTUtils.getStringTag(evolvepet,"力量");
        String oldspeed = NBTUtils.getStringTag(evolvepet,"敏捷");
        String oldwisdom = NBTUtils.getStringTag(evolvepet,"智慧");
        ItemMeta evolvepetmeta = evolvepet.getItemMeta();
        List<String> lore = evolvepetmeta.getLore();
        int petlevel = Integer.parseInt(NBTUtils.getStringTag(evolvepet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            Matcher powermatch = AddSourceAttribute.pattern.matcher(lore.get(6));
            if (powermatch.find()) {
                lore.set(6, Utils.msgColor("&6力量: " + powermatch.group(1) + "+" + newpet.getTalentPowerNum() * petlevel + "[" + newpet.getTalentPower() + "]"));
                player.sendMessage(msgColor("&f[异兽]力量: " + oldpower + "->" + newpet.getTalentPower()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            Matcher speedmatch = AddSourceAttribute.pattern.matcher(lore.get(7));
            if (speedmatch.find()) {
                lore.set(7, Utils.msgColor("&6敏捷: " + speedmatch.group(1) + "+" + newpet.getTalentSpeedNum() * petlevel + "[" + newpet.getTalentSpeed() + "]"));
                player.sendMessage(msgColor("&f[异兽]敏捷: " + oldspeed + "->" + newpet.getTalentSpeed()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            Matcher wisdommatch = AddSourceAttribute.pattern.matcher(lore.get(9));
            if (wisdommatch.find()) {
                lore.set(9, Utils.msgColor("&6智慧: " + wisdommatch.group(1) + "+" + newpet.getTalentWisdomNum() * petlevel + "[" + newpet.getTalentWisdom() + "]"));
                player.sendMessage(msgColor("&f[异兽]智慧: " + oldwisdom + "->" + newpet.getTalentWisdom()));
            }
        }
        evolvepetmeta.setLore(lore);
        evolvepet.setItemMeta(evolvepetmeta);
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "力量", newpet.getTalentPower());
        }
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "敏捷", newpet.getTalentSpeed());
        }
        if (!NBTUtils.getStringTag(evolvepet,"智慧").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "智慧", newpet.getTalentWisdom());
        }
        evolvepet = NBTUtils.setStringTag(evolvepet,"Evolution",String.valueOf(newevolution));
        return evolvepet;
    }
    public static ItemStack randomWisdomlockedPetTalent(ItemStack evolvepet, PetSS newpet, Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvepet,"Evolution"));
        int newevolution = evolution + 1;
        String oldpower = NBTUtils.getStringTag(evolvepet,"力量");
        String oldspeed = NBTUtils.getStringTag(evolvepet,"敏捷");
        String oldspirit = NBTUtils.getStringTag(evolvepet,"体力");
        ItemMeta evolvepetmeta = evolvepet.getItemMeta();
        List<String> lore = evolvepetmeta.getLore();
        int petlevel = Integer.parseInt(NBTUtils.getStringTag(evolvepet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            Matcher powermatch = AddSourceAttribute.pattern.matcher(lore.get(6));
            if (powermatch.find()) {
                lore.set(6, Utils.msgColor("&6力量: " + powermatch.group(1) + "+" + newpet.getTalentPowerNum() * petlevel + "[" + newpet.getTalentPower() + "]"));
                player.sendMessage(msgColor("&f[异兽]力量: " + oldpower + "->" + newpet.getTalentPower()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            Matcher speedmatch = AddSourceAttribute.pattern.matcher(lore.get(7));
            if (speedmatch.find()) {
                lore.set(7, Utils.msgColor("&6敏捷: " + speedmatch.group(1) + "+" + newpet.getTalentSpeedNum() * petlevel + "[" + newpet.getTalentSpeed() + "]"));
                player.sendMessage(msgColor("&f[异兽]敏捷: " + oldspeed + "->" + newpet.getTalentSpeed()));
            }
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            Matcher spiritmatch = AddSourceAttribute.pattern.matcher(lore.get(8));
            if (spiritmatch.find()) {
                lore.set(8, Utils.msgColor("&6体力: " + spiritmatch.group(1) + "+" + newpet.getTalentSpiritNum() * petlevel + "[" + newpet.getTalentSpirit() + "]"));
                player.sendMessage(msgColor("&f[异兽]体力: " + oldspirit + "->" + newpet.getTalentSpirit()));
            }
        }
        evolvepetmeta.setLore(lore);
        evolvepet.setItemMeta(evolvepetmeta);
        if (!NBTUtils.getStringTag(evolvepet,"力量").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "力量", newpet.getTalentPower());
        }
        if (!NBTUtils.getStringTag(evolvepet,"敏捷").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "敏捷", newpet.getTalentSpeed());
        }
        if (!NBTUtils.getStringTag(evolvepet,"体力").contains("SS")) {
            evolvepet = NBTUtils.setStringTag(evolvepet, "体力", newpet.getTalentSpirit());
        }
        evolvepet = NBTUtils.setStringTag(evolvepet,"Evolution",String.valueOf(newevolution));
        return evolvepet;
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

    /**
     * 献祭宠物
     * @param pet   宠物对象
     * @param player    玩家名
     */
    public static void immolatePet(ItemStack pet,Player player){
        ItemMeta petmeta = pet.getItemMeta();
        String petname = petmeta.getDisplayName();
        switch (petname){
            case "§f尖牙蝙蝠":
                NeigeHook.ShouhunCommand(player.getName(),"兽魂",5);
                NeigeHook.JinghuaCommand(player.getName(),"天赋精华",1,5);
                break;
            case "§f沙王蜘蛛":
                NeigeHook.ShouhunCommand(player.getName(),"兽魂",10);
                NeigeHook.JinghuaCommand(player.getName(),"天赋精华",1,5);
                break;
            case "§f萨满阿多":
                NeigeHook.ShouhunCommand(player.getName(),"兽魂",15);
                NeigeHook.JinghuaCommand(player.getName(),"天赋精华",1,5);
                break;
            case "§f通红之翼":
                NeigeHook.ShouhunCommand(player.getName(),"兽魂",20);
                NeigeHook.JinghuaCommand(player.getName(),"天赋精华",1,5);
                break;
            case "§f恐狼":
                NeigeHook.ShouhunCommand(player.getName(),"兽魂",30);
                NeigeHook.JinghuaCommand(player.getName(),"天赋精华",10,15);
                break;
            case "§f战魂":
                NeigeHook.ShouhunCommand(player.getName(),"兽魂",40);
                NeigeHook.JinghuaCommand(player.getName(),"天赋精华",20,25);
                break;
        }
    }

    /**
     * 检测是否含有SS天赋
     * @param pet   宠物
     * @return  是否
     */
    public static boolean checkSS(ItemStack pet){
        return !NBTUtils.getStringTag(pet, "力量").contains("SS") && !NBTUtils.getStringTag(pet, "敏捷").contains("SS")
                && !NBTUtils.getStringTag(pet, "体力").contains("SS") && !NBTUtils.getStringTag(pet, "智慧").contains("SS");
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
                return true;
        }
        return false;
    }

    /**
     * 检测物品是不是果实
     * @param fruit 果实
     * @return  是否
     */
    public static boolean checkFruit(ItemStack fruit){
        if (fruit == null || fruit.getItemMeta() == null || !fruit.getItemMeta().hasDisplayName()){return false;}
        String fruitName = ChatColor.stripColor(fruit.getItemMeta().getDisplayName());
        switch (fruitName){
            case "敏捷果实":
            case "力量果实":
            case "体力果实":
            case "智慧果实":
                return true;
        }
        return false;
    }

    /**
     * 检测该宠物是否可以喂养果实
     * @param petItem   宠物
     * @return  是否
     */
     public static boolean checkCanEat(ItemStack petItem){
        for (String line:petItem.getItemMeta().getLore()){
            if (line.contains("果实:")){
                Pattern guoShi = Pattern.compile("果实: (\\d+)\\(上限: (\\d+)\\)");
                Matcher matcher = guoShi.matcher(line);
                if (matcher.find()){
                    int num = Integer.parseInt(matcher.group(1));
                    int num1 = Integer.parseInt(matcher.group(2));
                    return num < num1;
                }
            }
        }
        return false;
     }

    /**
     * 喂养果实逻辑，先找属性再找果实词条
     * @param petItem   宠物
     * @param fruit 果实
     */
    public static void petEatFruit(ItemStack petItem,ItemStack fruit,Player player){
        String fruitName = ChatColor.stripColor(fruit.getItemMeta().getDisplayName());
        ItemMeta petItemMeta = petItem.getItemMeta();
        List<String> lore = petItemMeta.getLore();
        switch (fruitName){
            case "敏捷果实":
                for (String line:lore){
                    if (line.contains("敏捷:")){
                        Matcher matcher = AddSourceAttribute.pattern.matcher(line);
                        if (matcher.find()){
                            lore.set(7,msgColor("&6敏捷: " + (Integer.parseInt(matcher.group(1)) + 1) + "+" + matcher.group(2) + "[" + NBTUtils.getStringTag(petItem,"敏捷") + "]"));
                        }
                    }
                    if (line.contains("果实:")){
                        Pattern guoShi = Pattern.compile("果实: (\\d+)\\(上限: (\\d+)\\)");
                        Matcher matcher = guoShi.matcher(line);
                        if (matcher.find()){
                            lore.set(11,msgColor("&d果实: " + (Integer.parseInt(matcher.group(1)) + 1) + "(上限: " + matcher.group(2) + ")" ));
                        }
                    }
                }
                petItemMeta.setLore(lore);
                petItem.setItemMeta(petItemMeta);
                player.sendMessage(msgColor("&a[果实]异兽的敏捷属性上升了1点"));
                break;
            case "力量果实":
                for (String line:lore){
                    if (line.contains("力量:")){
                        Matcher matcher = AddSourceAttribute.pattern.matcher(line);
                        if (matcher.find()){
                            lore.set(6,msgColor("&6力量: " + (Integer.parseInt(matcher.group(1)) + 1) + "+" + matcher.group(2) + "[" + NBTUtils.getStringTag(petItem,"力量") + "]"));
                        }
                    }
                    if (line.contains("果实:")){
                        Pattern guoShi = Pattern.compile("果实: (\\d+)\\(上限: (\\d+)\\)");
                        Matcher matcher = guoShi.matcher(line);
                        if (matcher.find()){
                            lore.set(11,msgColor("&d果实: " + (Integer.parseInt(matcher.group(1)) + 1) + "(上限: " + matcher.group(2) + ")" ));
                        }
                    }
                }
                petItemMeta.setLore(lore);
                petItem.setItemMeta(petItemMeta);
                player.sendMessage(msgColor("&a[果实]异兽的力量属性上升了1点"));
                break;
            case "体力果实":
                for (String line:lore){
                    if (line.contains("体力:")){
                        Matcher matcher = AddSourceAttribute.pattern.matcher(line);
                        if (matcher.find()){
                            lore.set(8,msgColor("&6体力: " + (Integer.parseInt(matcher.group(1)) + 1) + "+" + matcher.group(2) + "[" + NBTUtils.getStringTag(petItem,"体力") + "]"));
                        }
                    }
                    if (line.contains("果实:")){
                        Pattern guoShi = Pattern.compile("果实: (\\d+)\\(上限: (\\d+)\\)");
                        Matcher matcher = guoShi.matcher(line);
                        if (matcher.find()){
                            lore.set(11,msgColor("&d果实: " + (Integer.parseInt(matcher.group(1)) + 1) + "(上限: " + matcher.group(2) + ")" ));
                        }
                    }
                }
                petItemMeta.setLore(lore);
                petItem.setItemMeta(petItemMeta);
                player.sendMessage(msgColor("&a[果实]异兽的体力属性上升了1点"));
                break;
            case "智慧果实":
                for (String line:lore){
                    if (line.contains("智慧:")){
                        Matcher matcher = AddSourceAttribute.pattern.matcher(line);
                        if (matcher.find()){
                            lore.set(9,msgColor("&6智慧: " + (Integer.parseInt(matcher.group(1)) + 1) + "+" + matcher.group(2) + "[" + NBTUtils.getStringTag(petItem,"智慧") + "]"));
                        }
                    }
                    if (line.contains("果实:")){
                        Pattern guoShi = Pattern.compile("果实: (\\d+)\\(上限: (\\d+)\\)");
                        Matcher matcher = guoShi.matcher(line);
                        if (matcher.find()){
                            lore.set(11,msgColor("&d果实: " + (Integer.parseInt(matcher.group(1)) + 1) + "(上限: " + matcher.group(2) + ")" ));
                        }
                    }
                }
                petItemMeta.setLore(lore);
                petItem.setItemMeta(petItemMeta);
                player.sendMessage(msgColor("&a[果实]异兽的智慧属性上升了1点"));
                break;
        }
    }
    /**
     * 检测是否含有SS天赋
     * @param inheritedpet  被传承宠物
     * @return  是否
     */
    public static boolean checkInheritSS(ItemStack inheritedpet){
        ItemMeta inheritedpetmeta = inheritedpet.getItemMeta();
        List<String> lore = inheritedpetmeta.getLore();
        int flag=0;
        for (String line:lore){
            if (line.contains("SS")){
                flag+=1;
            }
            if (flag>=2){
                break;
            }
        }
        return flag != 2;
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
    public static int checkShengmingjinghua(Player player){
        int count = 0;
        Inventory inv = player.getInventory();
        for (ItemStack itemStack : inv.getContents()) {
            if (itemStack != null) { // 检查物品是否为空
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().contains("生命精华")) {
                    count += itemStack.getAmount();
                }
            }
        }
        return count;
    }
    public static int checkAdvencedTalent(Player player){
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
}
