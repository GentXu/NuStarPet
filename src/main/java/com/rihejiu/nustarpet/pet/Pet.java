package com.rihejiu.nustarpet.pet;

import com.rihejiu.nustarpet.NuStarPet;
import com.rihejiu.nustarpet.attribute.AddSourceAttribute;
import com.rihejiu.nustarpet.command.Identify;
import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.random.RandomTalent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pet {
    public static final Pattern pattern = Pattern.compile("(\\d+)\\(上限: (\\d+)");
    public static final Pattern pattern2 = Pattern.compile("(\\d+)\\((\\d+)");
    public static final Pattern pattern3 = Pattern.compile("(\\d+)/(\\d+)");

    /**
     *
     * @param player 玩家名
     * @param exp   升级经验值
     * @param pet   宠物对象
     * @return  返回宠物对象，按理说不需要返回
     */
    public static ItemStack PetLevelUp(Player player,int exp, ItemStack pet) {
        // 异兽当前最大经验和当前经验
        int maxexp = getPetMaxExp(pet);
        int oldexp = getPetExp(pet);
        int level = Integer.parseInt(NBTUtils.getStringTag(pet,"Level"));
        // 当此次喂养的经验值大于异兽当前最大经验值 或 大于升级经验时执行升级逻辑
        if (level <= player.getLevel()) {
            if (level < 90) {
                if (exp > maxexp || exp > maxexp - oldexp) {
                    pet = recursionLevelUp(exp, maxexp, pet, oldexp);
                } else {
                    pet = NBTUtils.setStringTag(pet, "Exp", String.valueOf(oldexp + exp));
                    setPetExp(pet);
                }
            } else {
                player.sendMessage(Utils.msgColor("&c[异兽]该异兽已到达等级上限"));
            }
        } else {
            player.sendMessage(Utils.msgColor("&c[异兽]异兽等级不能大于角色等级！"));
        }
        return pet;
    }

    /**
     *
     * @param exp   传入的生命值
     * @param maxexp    该宠物当前经验值上限
     * @param pet   宠物对象
     * @param oldexp    该宠物当前经验值
     * @return  按理说不需要返回东西
     */
    public static ItemStack recursionLevelUp(int exp, int maxexp, ItemStack pet,int oldexp){
        // 如果喂养经验值大于升级经验 则执行升级逻辑
        if (exp > maxexp - oldexp){
            oldexp = Integer.parseInt(NBTUtils.getStringTag(pet,"Exp"));
            // 调用lore修改方法
            loreLevelUp(pet);
            // 当前异兽等级
            int level = Integer.parseInt(NBTUtils.getStringTag(pet,"Level"));
            // 升级后计算喂养经验还剩多少
            exp -= maxexp - oldexp;
            // 当前异兽等级+1
            level++;
            // 设置异兽升级后的最大经验值和等级
            pet = NBTUtils.setStringTag(pet,"Level",String.valueOf(level));
            pet = NBTUtils.setStringTag(pet,"MaxExp",String.valueOf(getPetNextLevelMaxExp(pet)));
            // 如果现在喂养经验大于异兽最大经验值执行 递归 反之设置异兽当前经验值
            int newmaxexp = getPetMaxExp(pet);
            if (exp > newmaxexp) {
                return recursionLevelUp(exp,newmaxexp,pet,oldexp);
            } else {
                pet = NBTUtils.setStringTag(pet,"Exp",String.valueOf(exp));
                setPetExp(pet);
            }
        }
        return pet;
    }

    /**
     *
     * @param pet   设置宠物当前经验值
     */
    public static void setPetExp(ItemStack pet){
        // lore行数
        int index = 0;
        List<String> lores = pet.getItemMeta().getLore();
        ItemMeta itemMeta = pet.getItemMeta();
        // 遍历lore 修改物品经验值
        for (String line:lores){
            double loreexp;
            double loreexp2;
            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setMaximumFractionDigits(2);
            if (line.contains("经验: ")){
                Matcher expmatcher = pattern2.matcher(line);
                if (expmatcher.find()){
                    loreexp = Integer.parseInt(NBTUtils.getStringTag(pet,"Exp"));
                    double maxexp = Integer.parseInt(NBTUtils.getStringTag(pet,"MaxExp"));
                    loreexp2 = Double.parseDouble(formatter.format(loreexp / maxexp * 100));
                    lores.set(index,ChatColor.translateAlternateColorCodes('&',"&7经验: ") + loreexp + "(" + loreexp2 + "%)");
                }
                break;
            }
            index++;
        }
        itemMeta.setLore(lores);
        pet.setItemMeta(itemMeta);
    }

    /**
     * 花费金币恢复宠物生命值
     *
     * @param pet   宠物对象
     * @param player    玩家名称
     */
    public static void setPetHealth(ItemStack pet,Player player){
        int index = 0;
        List<String> lores = pet.getItemMeta().getLore();
        ItemMeta itemMeta = pet.getItemMeta();
        // 遍历lore 修改物品经验值
        for (String line:lores){
            int lorehealth;
            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setMaximumFractionDigits(2);
            if (line.contains("异兽生命值: ")){
                Matcher healthmatcher = pattern3.matcher(line);
                if (healthmatcher.find()){
                    lorehealth = Integer.parseInt(healthmatcher.group(2)) - Integer.parseInt(healthmatcher.group(1));
                    double money = lorehealth * 50;
                    if (NuStarPet.getEconomy().getBalance(player) > money) {
                        NuStarPet.getEconomy().withdrawPlayer(player, money);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[异兽]你花费&e" + money + "金币&f治愈了你的异兽。"));
                        lores.set(index, ChatColor.translateAlternateColorCodes('&', "&c异兽生命值: ") + healthmatcher.group(2) + "/" + healthmatcher.group(2));
                    } else {
                        player.sendMessage(Utils.msgColor("&c[异兽]你的金币不足以治愈异兽了"));
                    }
                }
                break;
            }
            index++;
        }
        itemMeta.setLore(lores);
        pet.setItemMeta(itemMeta);
    }

    /**
     *  宠物升级时需要修改的Lore
     *
     * @param pet   传入宠物对象
     */
    public static void loreLevelUp (ItemStack pet){
        // 遍历到哪一行的Lore
        int index = 0;
        List<String> lores = pet.getItemMeta().getLore();
        ItemMeta itemMeta = pet.getItemMeta();
        // 重置属性变量
        int power;
        int speed;
        int spirit;
        int wisdom;
        // 遍历lore查找对应lore进行修改
        for (String line:lores){
            if (line.contains("力量: ")) {
                Matcher powermatcher = AddSourceAttribute.pattern.matcher(line);
                String powertalent;
                if (powermatcher.find()) {
                    powertalent = NBTUtils.getStringTag(pet, "力量");
                    power = Integer.parseInt(powermatcher.group(2)) + NBTUtils.getTalentNum(powertalent);
                    lores.set(index, ChatColor.translateAlternateColorCodes('&',"&6力量: ") + Integer.parseInt(powermatcher.group(1)) + "+" + power + "[" + powertalent + "]");
                }
                index++;
                continue;
            }
            if (line.contains("敏捷: ")) {
                Matcher speedmatcher = AddSourceAttribute.pattern.matcher(line);
                String speedtalent;
                if (speedmatcher.find()){
                    speedtalent = NBTUtils.getStringTag(pet, "敏捷");
                    speed = Integer.parseInt(speedmatcher.group(2)) + NBTUtils.getTalentNum(speedtalent);
                    lores.set(index, ChatColor.translateAlternateColorCodes('&',"&6敏捷: ") + Integer.parseInt(speedmatcher.group(1)) + "+" + speed + "[" + speedtalent + "]");
                }
                index++;
                continue;
            }
            if (line.contains("体力: ")) {
                Matcher spiritmatcher = AddSourceAttribute.pattern.matcher(line);
                String spirittalent;
                if (spiritmatcher.find()){
                    spirittalent = NBTUtils.getStringTag(pet, "体力");
                    spirit = Integer.parseInt(spiritmatcher.group(2)) + NBTUtils.getTalentNum(spirittalent);
                    lores.set(index, ChatColor.translateAlternateColorCodes('&',"&6体力: ") + Integer.parseInt(spiritmatcher.group(1)) + "+" + spirit + "[" + spirittalent + "]");
                }
                index++;
                continue;
            }
            if (line.contains("智慧: ")){
                Matcher wisdommatcher = AddSourceAttribute.pattern.matcher(line);
                String wisdomtalent;
                if (wisdommatcher.find()) {
                    wisdomtalent = NBTUtils.getStringTag(pet, "智慧");
                    wisdom = Integer.parseInt(wisdommatcher.group(2)) + NBTUtils.getTalentNum(wisdomtalent);
                    lores.set(index, ChatColor.translateAlternateColorCodes('&',"&6智慧: ") + Integer.parseInt(wisdommatcher.group(1)) + "+" + wisdom + "[" + wisdomtalent + "]");
                }
                index++;
                continue;
            }
            lores.set(0,ChatColor.translateAlternateColorCodes('&',"&6") + (Integer.parseInt(NBTUtils.getStringTag(pet,"Level")) + 1) + "级" + itemMeta.getDisplayName().split("f")[1]);
            if (line.contains("异兽生命值: ")){
                Matcher pethealthmatcher = AddSourceAttribute.pattern2.matcher(line);
                if (pethealthmatcher.find()) {
                    String entry = pethealthmatcher.group();
                    int totalMin = Integer.parseInt(entry.split("/")[0]);
                    int spirit2 = 0;
                    for (String line2:lores){
                        if (line2.contains("体力: ")){
                            Matcher petspiritmather = AddSourceAttribute.pattern.matcher(line2);
                            if (petspiritmather.find()){
                                spirit2 = Integer.parseInt(petspiritmather.group(1)) + Integer.parseInt(petspiritmather.group(2));
                            }
                        }
                    }
                    lores.set(index, ChatColor.translateAlternateColorCodes('&',"&c异兽生命值: ") + totalMin + "/" + (20 + spirit2 * 10));
                }
                index++;
                continue;
            }
            if (line.contains("果实: ")){
                Matcher fruitmatcher = pattern.matcher(line);
                if (fruitmatcher.find()) {
                    int oldfruit = Integer.parseInt(fruitmatcher.group(2));
                    int nowfruit = Integer.parseInt(fruitmatcher.group(1));
                    lores.set(index,ChatColor.translateAlternateColorCodes('&',"&d果实: ") + nowfruit + "(上限: " + (oldfruit + 1) + ")" );
                }
            }
            index++;
        }
        itemMeta.setLore(lores);
        pet.setItemMeta(itemMeta);
    }
    public static int getPetLevel(Player player){
        return Integer.parseInt(NBTUtils.getStringTag(Identify.getPetStack(player,8),"Level"));
    }
    public static int getPetLevel(ItemStack pet){
        return Integer.parseInt(NBTUtils.getStringTag(pet,"Level"));
    }
    public static int getPetExp(ItemStack pet){
        return Integer.parseInt(NBTUtils.getStringTag(pet,"Exp"));
    }
    public static int getPetMaxExp(ItemStack pet){
        return Integer.parseInt(NBTUtils.getStringTag(pet,"MaxExp"));
    }
    public static int getPetNextLevelMaxExp(ItemStack pet){
        int petlevel = getPetLevel(pet);
        return 110 * petlevel * petlevel + 25 * petlevel + 30;
    }
    public String getTalentPower() {
        return TalentPower;
    }

    public String getTalentSpeed() {
        return TalentSpeed;
    }

    public String getTalentSpirit() {
        return TalentSpirit;
    }

    public String getTalentWisdom() {
        return TalentWisdom;
    }

    public int getTalentPowerNum() {
        return TalentPowerNum;
    }

    public int getTalentSpeedNum() {
        return TalentSpeedNum;
    }

    public int getTalentSpiritNum() {
        return TalentSpiritNum;
    }

    public int getTalentWisdomNum() {
        return TalentWisdomNum;
    }

    public int getMaxHealth() {
        return MaxHealth;
    }
    String TalentPower =  RandomTalent.generateRandomCharacter();
    String TalentSpeed =  RandomTalent.generateRandomCharacter();
    String TalentSpirit =  RandomTalent.generateRandomCharacter();
    String TalentWisdom =  RandomTalent.generateRandomCharacter();
    int TalentPowerNum = NBTUtils.getTalentNum(TalentPower);
    int TalentSpeedNum = NBTUtils.getTalentNum(TalentSpeed);
    int TalentSpiritNum = NBTUtils.getTalentNum(TalentSpirit);
    int TalentWisdomNum = NBTUtils.getTalentNum(TalentWisdom);
    int MaxHealth = 20;
}
