package com.rihejiu.nustarpet.command;

import com.rihejiu.nustarpet.attribute.AddSourceAttribute;
import com.rihejiu.nustarpet.hook.NeigeHook;
import com.rihejiu.nustarpet.pet.Pet;
import com.rihejiu.nustarpet.pet.PetSS;
import com.rihejiu.nustarpet.pet.PetUUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.regex.Matcher;

public class Utils {
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
     * @return
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
     * @return
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
     * @return
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
                return true;
        }
        return false;
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
