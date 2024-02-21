package com.rihejiu.nustarpet.pet.utils;

import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.command.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static com.rihejiu.nustarpet.command.Utils.msgColor;
public class InheritUtils {
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
        inheritedpet = LevelUpUtils.petLevelUp(player,exp,inheritedpet);
        return inheritedpet;
    }
    /**
     * 检测是否含有SS天赋
     * @param inheritedPet  被传承宠物
     * @return  是否
     */
    public static boolean checkInheritSS(ItemStack inheritedPet){
        ItemMeta inheritedPetMeta = inheritedPet.getItemMeta();
        List<String> lore = inheritedPetMeta.getLore();
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
}
