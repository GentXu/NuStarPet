package com.rihejiu.nustarpet.pet.utils;

import com.rihejiu.nustarpet.attribute.AddSourceAttribute;
import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.pet.Pet;
import com.rihejiu.nustarpet.pet.PetWithoutSS;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.regex.Matcher;

import static com.rihejiu.nustarpet.command.Utils.msgColor;
public class EvolveUtils {
    /**
     *
     * @param evolvePet 进化宠物
     * @param newPet    进化宠物需要new一个新的宠物对象，将新宠物对象的天赋信息给进化宠物
     * @param player    玩家名字
     * @return  按理说不需要返回
     */
    public static ItemStack randomUnlockedPetTalent(ItemStack evolvePet, Pet newPet, Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvePet,"Evolution"));
        int newEvolution = evolution + 1;
        String oldPower = NBTUtils.getStringTag(evolvePet,"力量");
        String oldSpeed = NBTUtils.getStringTag(evolvePet,"敏捷");
        String oldSpirit = NBTUtils.getStringTag(evolvePet,"体力");
        String oldWisdom = NBTUtils.getStringTag(evolvePet,"智慧");
        ItemMeta evolvePetMeta = evolvePet.getItemMeta();
        List<String> lore = evolvePetMeta.getLore();
        int petLevel = Integer.parseInt(NBTUtils.getStringTag(evolvePet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (newPet instanceof PetWithoutSS) player.sendMessage(Utils.msgColor("&c[进化]该异兽已经具有一条SS属性，接下来不会再出现SS属性。"));
        if (!NBTUtils.getStringTag(evolvePet,"力量").contains("SS")) {
            Matcher powerMatch = AddSourceAttribute.pattern.matcher(lore.get(6));
            if (powerMatch.find()) {
                lore.set(6, Utils.msgColor("&6力量: " + powerMatch.group(1) + "+" + newPet.getTalentPowerNum() * petLevel + "[" + newPet.getTalentPower() + "]"));
                player.sendMessage(msgColor("&f[异兽]力量: " + oldPower + "->" + newPet.getTalentPower()));
            }
        }
        if (!NBTUtils.getStringTag(evolvePet,"敏捷").contains("SS")) {
            Matcher speedMatch = AddSourceAttribute.pattern.matcher(lore.get(7));
            if (speedMatch.find()) {
                lore.set(7, Utils.msgColor("&6敏捷: " + speedMatch.group(1) + "+" + newPet.getTalentSpeedNum() * petLevel + "[" + newPet.getTalentSpeed() + "]"));
                player.sendMessage(msgColor("&f[异兽]敏捷: " + oldSpeed + "->" + newPet.getTalentSpeed()));
            }
        }
        if (!NBTUtils.getStringTag(evolvePet,"体力").contains("SS")) {
            Matcher spiritMatch = AddSourceAttribute.pattern.matcher(lore.get(8));
            if (spiritMatch.find()) {
                lore.set(8, Utils.msgColor("&6体力: " + spiritMatch.group(1) + "+" + newPet.getTalentSpiritNum() * petLevel + "[" + newPet.getTalentSpirit() + "]"));
                player.sendMessage(msgColor("&f[异兽]体力: " + oldSpirit + "->" + newPet.getTalentSpirit()));
            }
        }
        if (!NBTUtils.getStringTag(evolvePet,"智慧").contains("SS")) {
            Matcher wisdomMatch = AddSourceAttribute.pattern.matcher(lore.get(9));
            if (wisdomMatch.find()) {
                lore.set(9, Utils.msgColor("&6智慧: " + wisdomMatch.group(1) + "+" + newPet.getTalentWisdomNum() * petLevel + "[" + newPet.getTalentWisdom() + "]"));
                player.sendMessage(msgColor("&f[异兽]智慧: " + oldWisdom + "->" + newPet.getTalentWisdom()));
            }
        }
        evolvePetMeta.setLore(lore);
        evolvePet.setItemMeta(evolvePetMeta);
        if (!NBTUtils.getStringTag(evolvePet,"力量").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "力量", newPet.getTalentPower());
        }
        if (!NBTUtils.getStringTag(evolvePet,"敏捷").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "敏捷", newPet.getTalentSpeed());
        }
        if (!NBTUtils.getStringTag(evolvePet,"体力").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "体力", newPet.getTalentSpirit());
        }
        if (!NBTUtils.getStringTag(evolvePet,"智慧").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "智慧", newPet.getTalentWisdom());
        }
        evolvePet = NBTUtils.setStringTag(evolvePet,"Evolution",String.valueOf(newEvolution));
        return evolvePet;
    }
    /**
     *  锁定力量天赋
     * @param evolvePet 进化宠物
     * @param newPet    新的宠物对象
     * @param player    玩家名字
     * @return  按理说不需要返回
     */
    public static ItemStack randomPowerLockedPetTalent(ItemStack evolvePet,Pet newPet,Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvePet,"Evolution"));
        int newEvolution = evolution + 1;
        String oldSpeed = NBTUtils.getStringTag(evolvePet,"敏捷");
        String oldSpirit = NBTUtils.getStringTag(evolvePet,"体力");
        String oldWisdom = NBTUtils.getStringTag(evolvePet,"智慧");
        ItemMeta evolvePetMeta = evolvePet.getItemMeta();
        List<String> lore = evolvePetMeta.getLore();
        int petLevel = Integer.parseInt(NBTUtils.getStringTag(evolvePet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (newPet instanceof PetWithoutSS) player.sendMessage(Utils.msgColor("&c[进化]该异兽已经具有一条SS属性，接下来不会再出现SS属性。"));
        if (!NBTUtils.getStringTag(evolvePet,"敏捷").contains("SS")) {
            Matcher speedMatch = AddSourceAttribute.pattern.matcher(lore.get(7));
            if (speedMatch.find()) {
                lore.set(7, Utils.msgColor("&6敏捷: " + speedMatch.group(1) + "+" + newPet.getTalentSpeedNum() * petLevel + "[" + newPet.getTalentSpeed() + "]"));
                player.sendMessage(msgColor("&f[异兽]敏捷: " + oldSpeed + "->" + newPet.getTalentSpeed()));
            }
        }
        if (!NBTUtils.getStringTag(evolvePet,"体力").contains("SS")) {
            Matcher spiritMatch = AddSourceAttribute.pattern.matcher(lore.get(8));
            if (spiritMatch.find()) {
                lore.set(8, Utils.msgColor("&6体力: " + spiritMatch.group(1) + "+" + newPet.getTalentSpiritNum() * petLevel + "[" + newPet.getTalentSpirit() + "]"));
                player.sendMessage(msgColor("&f[异兽]体力: " + oldSpirit + "->" + newPet.getTalentSpirit()));
            }
        }
        if (!NBTUtils.getStringTag(evolvePet,"智慧").contains("SS")) {
            Matcher wisdomMatch = AddSourceAttribute.pattern.matcher(lore.get(9));
            if (wisdomMatch.find()) {
                lore.set(9, Utils.msgColor("&6智慧: " + wisdomMatch.group(1) + "+" + newPet.getTalentWisdomNum() * petLevel + "[" + newPet.getTalentWisdom() + "]"));
                player.sendMessage(msgColor("&f[异兽]智慧: " + oldWisdom + "->" + newPet.getTalentWisdom()));
            }
        }
        evolvePetMeta.setLore(lore);
        evolvePet.setItemMeta(evolvePetMeta);
        if (!NBTUtils.getStringTag(evolvePet,"敏捷").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "敏捷", newPet.getTalentSpeed());
        }
        if (!NBTUtils.getStringTag(evolvePet,"体力").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "体力", newPet.getTalentSpirit());
        }
        if (!NBTUtils.getStringTag(evolvePet,"智慧").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "智慧", newPet.getTalentWisdom());
        }
        evolvePet = NBTUtils.setStringTag(evolvePet,"Evolution",String.valueOf(newEvolution));
        return evolvePet;
    }
    /**
     *  锁定速度天赋
     * @param evolvePet 进化宠物
     * @param newPet    新的宠物对象
     * @param player    玩家名字
     * @return  按理说不需要返回
     */
    public static ItemStack randomSpeedLockedPetTalent(ItemStack evolvePet,Pet newPet,Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvePet,"Evolution"));
        int newEvolution = evolution + 1;
        String oldPower = NBTUtils.getStringTag(evolvePet,"力量");
        String oldSpirit = NBTUtils.getStringTag(evolvePet,"体力");
        String oldWisdom = NBTUtils.getStringTag(evolvePet,"智慧");
        ItemMeta evolvePetMeta = evolvePet.getItemMeta();
        List<String> lore = evolvePetMeta.getLore();
        int petLevel = Integer.parseInt(NBTUtils.getStringTag(evolvePet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (newPet instanceof PetWithoutSS) player.sendMessage(Utils.msgColor("&c[进化]该异兽已经具有一条SS属性，接下来不会再出现SS属性。"));
        if (!NBTUtils.getStringTag(evolvePet,"力量").contains("SS")) {
            Matcher powerMatch = AddSourceAttribute.pattern.matcher(lore.get(6));
            if (powerMatch.find()) {
                lore.set(6, Utils.msgColor("&6力量: " + powerMatch.group(1) + "+" + newPet.getTalentPowerNum() * petLevel + "[" + newPet.getTalentPower() + "]"));
                player.sendMessage(msgColor("&f[异兽]力量: " + oldPower + "->" + newPet.getTalentPower()));
            }
        }
        if (!NBTUtils.getStringTag(evolvePet,"体力").contains("SS")) {
            Matcher spiritMatch = AddSourceAttribute.pattern.matcher(lore.get(8));
            if (spiritMatch.find()) {
                lore.set(8, Utils.msgColor("&6体力: " + spiritMatch.group(1) + "+" + newPet.getTalentSpiritNum() * petLevel + "[" + newPet.getTalentSpirit() + "]"));
                player.sendMessage(msgColor("&f[异兽]体力: " + oldSpirit + "->" + newPet.getTalentSpirit()));
            }
        }
        if (!NBTUtils.getStringTag(evolvePet,"智慧").contains("SS")) {
            Matcher wisdomMatch = AddSourceAttribute.pattern.matcher(lore.get(9));
            if (wisdomMatch.find()) {
                lore.set(9, Utils.msgColor("&6智慧: " + wisdomMatch.group(1) + "+" + newPet.getTalentWisdomNum() * petLevel + "[" + newPet.getTalentWisdom() + "]"));
                player.sendMessage(msgColor("&f[异兽]智慧: " + oldWisdom + "->" + newPet.getTalentWisdom()));
            }
        }
        evolvePetMeta.setLore(lore);
        evolvePet.setItemMeta(evolvePetMeta);
        if (!NBTUtils.getStringTag(evolvePet,"力量").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "力量", newPet.getTalentPower());
        }
        if (!NBTUtils.getStringTag(evolvePet,"体力").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "体力", newPet.getTalentSpirit());
        }
        if (!NBTUtils.getStringTag(evolvePet,"智慧").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "智慧", newPet.getTalentWisdom());
        }
        evolvePet = NBTUtils.setStringTag(evolvePet,"Evolution",String.valueOf(newEvolution));
        return evolvePet;
    }
    /**
     *  锁定体力天赋
     * @param evolvePet 进化宠物
     * @param newPet    新的宠物对象
     * @param player    玩家名字
     * @return  按理说不需要返回
     */
    public static ItemStack randomSpiritLockedPetTalent(ItemStack evolvePet,Pet newPet,Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvePet,"Evolution"));
        int newEvolution = evolution + 1;
        String oldPower = NBTUtils.getStringTag(evolvePet,"力量");
        String oldSpeed = NBTUtils.getStringTag(evolvePet,"敏捷");
        String oldWisdom = NBTUtils.getStringTag(evolvePet,"智慧");
        ItemMeta evolvePetMeta = evolvePet.getItemMeta();
        List<String> lore = evolvePetMeta.getLore();
        int petLevel = Integer.parseInt(NBTUtils.getStringTag(evolvePet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (newPet instanceof PetWithoutSS) player.sendMessage(Utils.msgColor("&c[进化]该异兽已经具有一条SS属性，接下来不会再出现SS属性。"));
        if (!NBTUtils.getStringTag(evolvePet,"力量").contains("SS")) {
            Matcher powerMatch = AddSourceAttribute.pattern.matcher(lore.get(6));
            if (powerMatch.find()) {
                lore.set(6, Utils.msgColor("&6力量: " + powerMatch.group(1) + "+" + newPet.getTalentPowerNum() * petLevel + "[" + newPet.getTalentPower() + "]"));
                player.sendMessage(msgColor("&f[异兽]力量: " + oldPower + "->" + newPet.getTalentPower()));
            }
        }
        if (!NBTUtils.getStringTag(evolvePet,"敏捷").contains("SS")) {
            Matcher speedMatch = AddSourceAttribute.pattern.matcher(lore.get(7));
            if (speedMatch.find()) {
                lore.set(7, Utils.msgColor("&6敏捷: " + speedMatch.group(1) + "+" + newPet.getTalentSpeedNum() * petLevel + "[" + newPet.getTalentSpeed() + "]"));
                player.sendMessage(msgColor("&f[异兽]敏捷: " + oldSpeed + "->" + newPet.getTalentSpeed()));
            }
        }
        if (!NBTUtils.getStringTag(evolvePet,"智慧").contains("SS")) {
            Matcher wisdomMatch = AddSourceAttribute.pattern.matcher(lore.get(9));
            if (wisdomMatch.find()) {
                lore.set(9, Utils.msgColor("&6智慧: " + wisdomMatch.group(1) + "+" + newPet.getTalentWisdomNum() * petLevel + "[" + newPet.getTalentWisdom() + "]"));
                player.sendMessage(msgColor("&f[异兽]智慧: " + oldWisdom + "->" + newPet.getTalentWisdom()));
            }
        }
        evolvePetMeta.setLore(lore);
        evolvePet.setItemMeta(evolvePetMeta);
        if (!NBTUtils.getStringTag(evolvePet,"力量").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "力量", newPet.getTalentPower());
        }
        if (!NBTUtils.getStringTag(evolvePet,"敏捷").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "敏捷", newPet.getTalentSpeed());
        }
        if (!NBTUtils.getStringTag(evolvePet,"智慧").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "智慧", newPet.getTalentWisdom());
        }
        evolvePet = NBTUtils.setStringTag(evolvePet,"Evolution",String.valueOf(newEvolution));
        return evolvePet;
    }
    /**
     *  锁定智力天赋
     * @param evolvePet 进化宠物
     * @param newPet    新的宠物对象
     * @param player    玩家名字
     * @return  按理说不需要返回
     */
    public static ItemStack randomWisdomLockedPetTalent(ItemStack evolvePet,Pet newPet,Player player){
        int evolution = Integer.parseInt(NBTUtils.getStringTag(evolvePet,"Evolution"));
        int newEvolution = evolution + 1;
        String oldPower = NBTUtils.getStringTag(evolvePet,"力量");
        String oldSpeed = NBTUtils.getStringTag(evolvePet,"敏捷");
        String oldSpirit = NBTUtils.getStringTag(evolvePet,"体力");
        ItemMeta evolvePetMeta = evolvePet.getItemMeta();
        List<String> lore = evolvePetMeta.getLore();
        int petLevel = Integer.parseInt(NBTUtils.getStringTag(evolvePet, "Level"));
        player.sendMessage(msgColor("&7-------------------------------"));
        if (newPet instanceof PetWithoutSS) player.sendMessage(Utils.msgColor("&c[进化]该异兽已经具有一条SS属性，接下来不会再出现SS属性。"));
        if (!NBTUtils.getStringTag(evolvePet,"力量").contains("SS")) {
            Matcher powerMatch = AddSourceAttribute.pattern.matcher(lore.get(6));
            if (powerMatch.find()) {
                lore.set(6, Utils.msgColor("&6力量: " + powerMatch.group(1) + "+" + newPet.getTalentPowerNum() * petLevel + "[" + newPet.getTalentPower() + "]"));
                player.sendMessage(msgColor("&f[异兽]力量: " + oldPower + "->" + newPet.getTalentPower()));
            }
        }
        if (!NBTUtils.getStringTag(evolvePet,"敏捷").contains("SS")) {
            Matcher speedMatch = AddSourceAttribute.pattern.matcher(lore.get(7));
            if (speedMatch.find()) {
                lore.set(7, Utils.msgColor("&6敏捷: " + speedMatch.group(1) + "+" + newPet.getTalentSpeedNum() * petLevel + "[" + newPet.getTalentSpeed() + "]"));
                player.sendMessage(msgColor("&f[异兽]敏捷: " + oldSpeed + "->" + newPet.getTalentSpeed()));
            }
        }
        if (!NBTUtils.getStringTag(evolvePet,"体力").contains("SS")) {
            Matcher spiritMatch = AddSourceAttribute.pattern.matcher(lore.get(8));
            if (spiritMatch.find()) {
                lore.set(8, Utils.msgColor("&6体力: " + spiritMatch.group(1) + "+" + newPet.getTalentSpiritNum() * petLevel + "[" + newPet.getTalentSpirit() + "]"));
                player.sendMessage(msgColor("&f[异兽]体力: " + oldSpirit + "->" + newPet.getTalentSpirit()));
            }
        }
        evolvePetMeta.setLore(lore);
        evolvePet.setItemMeta(evolvePetMeta);
        if (!NBTUtils.getStringTag(evolvePet,"力量").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "力量", newPet.getTalentPower());
        }
        if (!NBTUtils.getStringTag(evolvePet,"敏捷").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "敏捷", newPet.getTalentSpeed());
        }
        if (!NBTUtils.getStringTag(evolvePet,"体力").contains("SS")) {
            evolvePet = NBTUtils.setStringTag(evolvePet, "体力", newPet.getTalentSpirit());
        }
        evolvePet = NBTUtils.setStringTag(evolvePet,"Evolution",String.valueOf(newEvolution));
        return evolvePet;
    }
    /**
     * 检测玩家背包里生命精华的数量
     * @param player 玩家
     * @return 生命精华数量
     */
    public static int checkSMJH(Player player){
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
    /**
     * 检测是否含有SS天赋
     * @param pet   宠物
     * @return  是否
     */
    public static boolean checkSS(ItemStack pet){
        return !NBTUtils.getStringTag(pet, "力量").contains("SS") && !NBTUtils.getStringTag(pet, "敏捷").contains("SS")
                && !NBTUtils.getStringTag(pet, "体力").contains("SS") && !NBTUtils.getStringTag(pet, "智慧").contains("SS");
    }
}
