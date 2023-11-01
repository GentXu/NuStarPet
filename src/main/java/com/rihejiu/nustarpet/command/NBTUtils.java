package com.rihejiu.nustarpet.command;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTUtils {
    public static ItemStack setStringTag(ItemStack itemStack, String key, String value) {
        net.minecraft.server.v1_12_R1.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = nmsItemStack.getTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        tag.setString(key, value);
        nmsItemStack.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }
    public static ItemStack setNBTTag(ItemStack itemStack, String key,String key2, String value) {
        net.minecraft.server.v1_12_R1.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = nmsItemStack.getTag();
        NBTTagCompound tag2 = new NBTTagCompound();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        tag2.setString(key2,value);
        tag.set(key, tag2);
        nmsItemStack.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }
    public static String getStringTag(ItemStack itemStack, String key) {
        net.minecraft.server.v1_12_R1.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = nmsItemStack.getTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        tag.getString(key);
        return tag.getString(key);
    }
    public static ItemStack removeTag(ItemStack itemStack, String key) {
        net.minecraft.server.v1_12_R1.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = nmsItemStack.hasTag() ? nmsItemStack.getTag() : new NBTTagCompound();
        if (tag.hasKey(key)) { // 检查标签是否存在
            tag.remove(key); // 移除指定键的NBT标签
        }
        nmsItemStack.setTag(tag); // 将更新后的NBT标签设置回NMS ItemStack
        return CraftItemStack.asBukkitCopy(nmsItemStack); // 转换回Bukkit ItemStack并返回
    }
    public static int getTalentNum(String talent) {
        switch (talent){
            case "D" : return 1;
            case "C" : return 2;
            case "B" : return 3;
            case "A" : return 4;
            case "S" : return 5;
            case "SS" : return 6;
        }
        return 0;
    }
}

