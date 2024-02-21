package com.rihejiu.nustarpet.pet.utils;

import com.rihejiu.nustarpet.hook.NeigeHook;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ImmolateUtils {
    /**
     * 献祭宠物
     * @param pet   宠物对象
     * @param player    玩家名
     */
    public static void immolatePet(ItemStack pet, Player player){
        ItemMeta petMeta = pet.getItemMeta();
        String petName = petMeta.getDisplayName();
        switch (petName){
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
                NeigeHook.ShouhunCommand(player.getName(),"兽魂",25);
                NeigeHook.JinghuaCommand(player.getName(),"天赋精华",8,15);
                break;
            case "§f战魂":
                NeigeHook.ShouhunCommand(player.getName(),"兽魂",30);
                NeigeHook.JinghuaCommand(player.getName(),"天赋精华",8,15);
                break;
            case "§f战马":
                NeigeHook.ShouhunCommand(player.getName(),"兽魂",35);
                NeigeHook.JinghuaCommand(player.getName(),"天赋精华",8,15);
                break;
        }
    }
}
