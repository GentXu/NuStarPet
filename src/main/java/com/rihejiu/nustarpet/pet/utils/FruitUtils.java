package com.rihejiu.nustarpet.pet.utils;

import com.rihejiu.nustarpet.attribute.AddSourceAttribute;
import com.rihejiu.nustarpet.command.NBTUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.rihejiu.nustarpet.command.Utils.msgColor;

public class FruitUtils {
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
    public static void petEatFruit(ItemStack petItem, ItemStack fruit, Player player){
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
}
