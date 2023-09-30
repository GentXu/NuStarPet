package com.rihejiu.nustarpet.command;

import com.rihejiu.nustarpet.pet.Pet;
import com.rihejiu.nustarpet.random.pettalent.Bat;
import com.rihejiu.nustarpet.random.pettalent.Bat2;
import com.rihejiu.nustarpet.random.pettalent.Spider;
import com.rihejiu.nustarpet.random.pettalent.Zombie;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PetList {
    public void bianfu(Player player) {
        // 创建一个新的物品，这里使用钻石剑作为示例
        ItemStack customItem = new ItemStack(Material.MONSTER_EGG);
        // 获取 ItemMeta，用于设置 Lore 和 NBT
        ItemMeta meta = customItem.getItemMeta();
        // 创建异兽对象
        Pet pet = new Pet();
        // 设置Lore
        // 创建 Lore 列表并添加 Lore 行
        List<String> lore = new ArrayList<>();
        lore.add(Utils.msgColor("&61级尖牙蝙蝠"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7需求等级: 5"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7经验: 0(0%)"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&6力量: 15" + "+" + pet.getTalentPowerNum() + "[" + pet.getTalentPower()+ "]" ));
        lore.add(Utils.msgColor("&6敏捷: 20" + "+" + pet.getTalentSpeedNum() + "[" + pet.getTalentSpeed() + "]" ));
        lore.add(Utils.msgColor("&6体力: 20" + "+" + pet.getTalentSpiritNum() + "[" + pet.getTalentSpirit() + "]" ));
        lore.add(Utils.msgColor("&6智慧: 15" + "+" + pet.getTalentWisdomNum() + "[" + pet.getTalentWisdom() + "]" ));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&d果实: 0(上限: 1)"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&c异兽生命值: 20/") + pet.getMaxHealth());
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7一只被封印着的蝙蝠，可以将它召唤出来"));
        lore.add(Utils.msgColor("&7为你战斗。"));
        lore.add(Utils.msgColor("&7种族天赋:"));
        lore.add(Utils.msgColor("&7[吸血][闪电][寂静][飞行]"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&3携带天赋: "));
        lore.add(Utils.msgColor("&3" + Bat.generateRandomString()));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&4血脉: 未开启"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7售价: 10000"));
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.WHITE+ "尖牙蝙蝠");
        customItem.setItemMeta(meta);
        // 设置NBT标签
        customItem = NBTUtils.setStringTag(customItem, "力量", pet.getTalentPower());
        customItem = NBTUtils.setStringTag(customItem, "体力", pet.getTalentSpirit());
        customItem = NBTUtils.setStringTag(customItem, "智慧", pet.getTalentWisdom());
        customItem = NBTUtils.setStringTag(customItem, "敏捷", pet.getTalentSpeed());
        customItem = NBTUtils.setStringTag(customItem, "Exp", "0");
        customItem = NBTUtils.setStringTag(customItem, "Level", "1");
        customItem = NBTUtils.setStringTag(customItem, "Evolution", "1");
        customItem = NBTUtils.setStringTag(customItem, "MaxExp", "165");
        customItem = NBTUtils.setStringTag(customItem, "MaxLevel", "90");
        customItem = NBTUtils.setStringTag(customItem, "Player", player.getName());
        customItem = NBTUtils.setStringTag(customItem, "PetId", "尖牙蝙蝠");
        customItem = NBTUtils.setStringTag(customItem, "Uuid", "7af4a886-a1c1-4044-ab45-c04ec89bbf45");
        customItem = NBTUtils.setNBTTag(customItem, "EntityTag", "id","minecraft:bat");
        // 给玩家物品
        player.getInventory().addItem(customItem);
    }
    public void zhizhu(Player player) {
        // 创建一个新的物品，这里使用钻石剑作为示例
        ItemStack customItem = new ItemStack(Material.MONSTER_EGG);
        // 获取 ItemMeta，用于设置 Lore 和 NBT
        ItemMeta meta = customItem.getItemMeta();
        // 创建异兽对象
        Pet pet = new Pet();
        // 设置Lore
        // 创建 Lore 列表并添加 Lore 行
        List<String> lore = new ArrayList<>();
        lore.add(Utils.msgColor("&61级沙王蜘蛛"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7需求等级: 10"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7经验: 0(0%)"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&6力量: 25" + "+" + pet.getTalentPowerNum() + "[" + pet.getTalentPower()+ "]" ));
        lore.add(Utils.msgColor("&6敏捷: 30" + "+" + pet.getTalentSpeedNum() + "[" + pet.getTalentSpeed() + "]" ));
        lore.add(Utils.msgColor("&6体力: 30" + "+" + pet.getTalentSpiritNum() + "[" + pet.getTalentSpirit() + "]" ));
        lore.add(Utils.msgColor("&6智慧: 25" + "+" + pet.getTalentWisdomNum() + "[" + pet.getTalentWisdom() + "]" ));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&d果实: 0(上限: 1)"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&c异兽生命值: 20/") + pet.getMaxHealth());
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7一只被封印着的蜘蛛，可以将它召唤出来"));
        lore.add(Utils.msgColor("&7为你战斗。"));
        lore.add(Utils.msgColor("&7种族天赋:"));
        lore.add(Utils.msgColor("&7[毒液][旋风][硬壳][再生]"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&3携带天赋: "));
        lore.add(Utils.msgColor("&3" + Spider.generateRandomString()));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&4血脉: 未开启"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7售价: 10000"));
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.WHITE+ "沙王蜘蛛");
        customItem.setItemMeta(meta);
        // 设置NBT标签
        customItem = NBTUtils.setStringTag(customItem, "力量", pet.getTalentPower());
        customItem = NBTUtils.setStringTag(customItem, "体力", pet.getTalentSpirit());
        customItem = NBTUtils.setStringTag(customItem, "智慧", pet.getTalentWisdom());
        customItem = NBTUtils.setStringTag(customItem, "敏捷", pet.getTalentSpeed());
        customItem = NBTUtils.setStringTag(customItem, "Exp", "0");
        customItem = NBTUtils.setStringTag(customItem, "Level", "1");
        customItem = NBTUtils.setStringTag(customItem, "Evolution", "1");
        customItem = NBTUtils.setStringTag(customItem, "MaxExp", "165");
        customItem = NBTUtils.setStringTag(customItem, "MaxLevel", "90");
        customItem = NBTUtils.setStringTag(customItem, "Player", player.getName());
        customItem = NBTUtils.setStringTag(customItem, "PetId", "沙王蜘蛛");
        customItem = NBTUtils.setStringTag(customItem, "Uuid", "315935f1-f100-4a5e-bdf7-203e34ae7883");
        customItem = NBTUtils.setNBTTag(customItem, "EntityTag", "id","minecraft:spider");
        // 给玩家物品
        player.getInventory().addItem(customItem);
    }
    public void saman(Player player) {
        // 创建一个新的物品，这里使用钻石剑作为示例
        ItemStack customItem = new ItemStack(Material.MONSTER_EGG);
        // 获取 ItemMeta，用于设置 Lore 和 NBT
        ItemMeta meta = customItem.getItemMeta();
        // 创建异兽对象
        Pet pet = new Pet();
        // 设置Lore
        // 创建 Lore 列表并添加 Lore 行
        List<String> lore = new ArrayList<>();
        lore.add(Utils.msgColor("&61级萨满阿多"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7需求等级: 15"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7经验: 0(0%)"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&6力量: 30" + "+" + pet.getTalentPowerNum() + "[" + pet.getTalentPower()+ "]" ));
        lore.add(Utils.msgColor("&6敏捷: 30" + "+" + pet.getTalentSpeedNum() + "[" + pet.getTalentSpeed() + "]" ));
        lore.add(Utils.msgColor("&6体力: 40" + "+" + pet.getTalentSpiritNum() + "[" + pet.getTalentSpirit() + "]" ));
        lore.add(Utils.msgColor("&6智慧: 30" + "+" + pet.getTalentWisdomNum() + "[" + pet.getTalentWisdom() + "]" ));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&d果实: 0(上限: 1)"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&c异兽生命值: 20/") + pet.getMaxHealth());
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7一只被封印着的小萨满，可以将它召唤出来"));
        lore.add(Utils.msgColor("&7为你战斗。"));
        lore.add(Utils.msgColor("&7种族天赋:"));
        lore.add(Utils.msgColor("&7[寒冰][涌能][冰盾][邪术]"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&3携带天赋: "));
        lore.add(Utils.msgColor("&3" + Zombie.generateRandomString()));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&4血脉: 未开启"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7售价: 10000"));
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.WHITE+ "萨满阿多");
        customItem.setItemMeta(meta);
        // 设置NBT标签
        customItem = NBTUtils.setStringTag(customItem, "力量", pet.getTalentPower());
        customItem = NBTUtils.setStringTag(customItem, "体力", pet.getTalentSpirit());
        customItem = NBTUtils.setStringTag(customItem, "智慧", pet.getTalentWisdom());
        customItem = NBTUtils.setStringTag(customItem, "敏捷", pet.getTalentSpeed());
        customItem = NBTUtils.setStringTag(customItem, "Exp", "0");
        customItem = NBTUtils.setStringTag(customItem, "Level", "1");
        customItem = NBTUtils.setStringTag(customItem, "Evolution", "1");
        customItem = NBTUtils.setStringTag(customItem, "MaxExp", "165");
        customItem = NBTUtils.setStringTag(customItem, "MaxLevel", "90");
        customItem = NBTUtils.setStringTag(customItem, "Player", player.getName());
        customItem = NBTUtils.setStringTag(customItem, "PetId", "萨满阿多");
        customItem = NBTUtils.setStringTag(customItem, "Uuid", "ef1653c4-5f31-4f25-91df-9afb055919cd");
        customItem = NBTUtils.setNBTTag(customItem, "EntityTag", "id","minecraft:zombie");
        // 给玩家物品
        player.getInventory().addItem(customItem);
    }
    public void tonghong(Player player) {
        // 创建一个新的物品，这里使用钻石剑作为示例
        ItemStack customItem = new ItemStack(Material.MONSTER_EGG);
        // 获取 ItemMeta，用于设置 Lore 和 NBT
        ItemMeta meta = customItem.getItemMeta();
        // 创建异兽对象
        Pet pet = new Pet();
        // 设置Lore
        // 创建 Lore 列表并添加 Lore 行
        List<String> lore = new ArrayList<>();
        lore.add(Utils.msgColor("&61级通红之翼"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7需求等级: 20"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7经验: 0(0%)"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&6力量: 35" + "+" + pet.getTalentPowerNum() + "[" + pet.getTalentPower()+ "]" ));
        lore.add(Utils.msgColor("&6敏捷: 35" + "+" + pet.getTalentSpeedNum() + "[" + pet.getTalentSpeed() + "]" ));
        lore.add(Utils.msgColor("&6体力: 40" + "+" + pet.getTalentSpiritNum() + "[" + pet.getTalentSpirit() + "]" ));
        lore.add(Utils.msgColor("&6智慧: 40" + "+" + pet.getTalentWisdomNum() + "[" + pet.getTalentWisdom() + "]" ));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&d果实: 0(上限: 1)"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&c异兽生命值: 20/") + pet.getMaxHealth());
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7一只被封印着的烈焰之翼，可以将它召唤出来"));
        lore.add(Utils.msgColor("&7为你战斗。"));
        lore.add(Utils.msgColor("&7种族天赋:"));
        lore.add(Utils.msgColor("&7[火焰][石肤][火盾][魔灵]"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&3携带天赋: "));
        lore.add(Utils.msgColor("&3" + Bat2.generateRandomString()));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&4血脉: 未开启"));
        lore.add(Utils.msgColor("&8&m一一一一一一一一一一一一一一一一一"));
        lore.add(Utils.msgColor("&7售价: 10000"));
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.WHITE+ "通红之翼");
        customItem.setItemMeta(meta);
        // 设置NBT标签
        customItem = NBTUtils.setStringTag(customItem, "力量", pet.getTalentPower());
        customItem = NBTUtils.setStringTag(customItem, "体力", pet.getTalentSpirit());
        customItem = NBTUtils.setStringTag(customItem, "智慧", pet.getTalentWisdom());
        customItem = NBTUtils.setStringTag(customItem, "敏捷", pet.getTalentSpeed());
        customItem = NBTUtils.setStringTag(customItem, "Exp", "0");
        customItem = NBTUtils.setStringTag(customItem, "Level", "1");
        customItem = NBTUtils.setStringTag(customItem, "Evolution", "1");
        customItem = NBTUtils.setStringTag(customItem, "MaxExp", "165");
        customItem = NBTUtils.setStringTag(customItem, "MaxLevel", "90");
        customItem = NBTUtils.setStringTag(customItem, "Player", player.getName());
        customItem = NBTUtils.setStringTag(customItem, "PetId", "通红之翼");
        customItem = NBTUtils.setStringTag(customItem, "Uuid", "4ee789a6-2515-4017-82a8-466f0a3f9583");
        customItem = NBTUtils.setNBTTag(customItem, "EntityTag", "id","minecraft:bat");
        // 给玩家物品
        player.getInventory().addItem(customItem);
    }
}
