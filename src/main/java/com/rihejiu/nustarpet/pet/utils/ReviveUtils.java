package com.rihejiu.nustarpet.pet.utils;

import com.rihejiu.nustarpet.NuStarPet;
import com.rihejiu.nustarpet.command.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviveUtils {
    public static final Pattern pattern = Pattern.compile("(\\d+)/(\\d+)");
    /**
     * 花费金币恢复宠物生命值
     *
     * @param pet   宠物对象
     * @param player    玩家名称
     */
    public static void setPetHealth(ItemStack pet, Player player){
        int index = 0;
        List<String> lores = pet.getItemMeta().getLore();
        ItemMeta itemMeta = pet.getItemMeta();
        // 遍历lore 修改物品经验值
        for (String line:lores){
            int lorehealth;
            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setMaximumFractionDigits(2);
            if (line.contains("异兽生命值: ")){
                Matcher healthmatcher = pattern.matcher(line);
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
}
