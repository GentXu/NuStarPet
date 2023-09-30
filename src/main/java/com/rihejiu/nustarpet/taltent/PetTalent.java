package com.rihejiu.nustarpet.taltent;

import com.rihejiu.nustarpet.attribute.AddSourceAttribute;
import com.rihejiu.nustarpet.command.Identify;
import com.rihejiu.nustarpet.listens.EventListener;
import com.rihejiu.nustarpet.pet.Pet;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.NumberFormat;
import java.util.List;

public class PetTalent {
    private Player player = null;
    private ItemStack pet = null;
    public PetTalent(){
        flag = 0;
        flag1 = 0;
        flag2 = 0;
        flag3 = 0;
    }
    public PetTalent(ItemStack pet,Player player){
        flag = 0;
        flag1 = 0;
        flag2 = 0;
        flag3 = 0;
        this.player = player;
        this.pet = pet;
    }
    public void chooseName(String petname){
        switch (petname){
            case "尖牙蝙蝠":
                Bat(pet,player);
                break;
            case "沙王蜘蛛":
                Spider(pet,player);
                break;
            case "萨满阿多":
                Zombie(pet,player);
                break;
            case "通红之翼":
                Bat2(pet,player);
                break;
        }
    }
    private int flag;
    private int flag1;
    private int flag2;
    private int flag3;
    public void talentMsg(String petname, Player player){
        switch (petname){
            case "尖牙蝙蝠":
                talentMsgBat(player);
                break;
            case "沙王蜘蛛":
                talentMsgSpider(player);
                break;
            case "萨满阿多":
                talentMsgZombie(player);
                break;
            case "通红之翼":
                talentMsgBat2(player);
                break;
        }
    }
    public void talentMsgBat(Player player){
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        if (Identify.getPet(player,8) == null){
            return;
        }
        ItemMeta petmeta = Identify.getPet(player,8);
        int level = Pet.getPetLevel(player);
        List<String> lore = petmeta != null ? petmeta.getLore() : null;
        for (int i = 0; i < (lore != null ? lore.size() : 0); i++){
            String line = lore.get(i);
            if (line.contains("[吸血]") && !line.contains("§7[") && flag == 0){
                player.sendMessage(ChatColor.WHITE + "吸血: 15%几率吸取" + formatter.format(level * 1.5) + "生命值");
                flag++;
                i--;
            }
            if (line.contains("[闪电]") && !line.contains("§7[") && flag1 == 0){
                player.sendMessage(ChatColor.WHITE + "闪电: 雷电基础伤害+" + level);
                flag1++;
                i--;
            }
            if (line.contains("[寂静]") && !line.contains("§7[") && flag2 == 0){
                player.sendMessage(ChatColor.WHITE + "寂静: 魔力恢复+" + formatter.format(level * 0.5));
                flag2++;
                i--;
            }
            if (line.contains("[飞行]") && !line.contains("§7[") && flag3 == 0){
                player.sendMessage(ChatColor.WHITE + "飞行: 闪避几率+" + formatter.format(level * 0.2));
                flag3++;
                i--;
            }
        }
    }
    public void Bat(ItemStack pet, Player player){
        ItemMeta petmeta = pet.getItemMeta();
        int level = Pet.getPetLevel(player);
        List<String> lore = petmeta.getLore();
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        AddSourceAttribute asa = EventListener.getPlayerAsa(player);
        for (int i = 0;i < lore.size();i++){
            String line = lore.get(i);
            if (line.contains("[吸血]") && !line.contains("§7[") && flag == 0){
                asa.setPetattr("吸血: " + formatter.format(level * 1.5));
                flag++;
                i--;
            }
            if (line.contains("[闪电]") && !line.contains("§7[") && flag1 == 0){
                asa.setPetattr("雷电基础伤害: " + level);
                flag1++;
                i--;
            }
            if (line.contains("[寂静]") && !line.contains("§7[") && flag2 == 0){
                asa.setPetattr("魔力恢复: " + formatter.format(level * 0.5));
                flag2++;
                i--;
            }
            if (line.contains("[飞行]") && !line.contains("§7[") && flag3 == 0){
                asa.setPetattr("闪避几率: " + formatter.format(level * 0.2));
                flag3++;
                i--;
            }
        }
    }
    public void talentMsgSpider(Player player){
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        if (Identify.getPet(player,8) == null){
            return;
        }
        ItemMeta petmeta = Identify.getPet(player,8);
        int level = Pet.getPetLevel(player);
        List<String> lore = petmeta != null ? petmeta.getLore() : null;
        for (int i = 0; i < (lore != null ? lore.size() : 0); i++){
            String line = lore.get(i);
            if (line.contains("[毒液]") && !line.contains("§7[") && flag == 0){
                player.sendMessage(ChatColor.WHITE + "毒液: 提升中毒几率" + formatter.format(level * 0.3) + "%");
                flag++;
                i--;
            }
            if (line.contains("[旋风]") && !line.contains("§7[") && flag1 == 0){
                player.sendMessage(ChatColor.WHITE + "旋风: 飓风基础伤害+" + level);
                flag1++;
                i--;
            }
            if (line.contains("[硬壳]") && !line.contains("§7[") && flag2 == 0){
                player.sendMessage(ChatColor.WHITE + "硬壳: 防御力+" + formatter.format(level * 1.2));
                flag2++;
                i--;
            }
            if (line.contains("[再生]") && !line.contains("§7[") && flag3 == 0){
                player.sendMessage(ChatColor.WHITE + "再生: 生命恢复+" + formatter.format(level * 0.5));
                flag3++;
                i--;
            }
        }
    }
    public void Spider(ItemStack pet, Player player){
        ItemMeta petmeta = pet.getItemMeta();
        int level = Pet.getPetLevel(player);
        List<String> lore = petmeta.getLore();
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        AddSourceAttribute asa = EventListener.getPlayerAsa(player);
        for (int i = 0;i < lore.size();i++){
            String line = lore.get(i);
            if (line.contains("[毒液]") && !line.contains("§7[") && flag == 0){
                asa.setPetattr("中毒几率: " + formatter.format(level * 0.3));
                flag++;
                i--;
            }
            if (line.contains("[旋风]") && !line.contains("§7[") && flag1 == 0){
                asa.setPetattr("飓风基础伤害: " + level);
                flag1++;
                i--;
            }
            if (line.contains("[硬壳]") && !line.contains("§7[") && flag2 == 0){
                asa.setPetattr("防御力: " + formatter.format(level * 1.2));
                flag2++;
                i--;
            }
            if (line.contains("[再生]") && !line.contains("§7[") && flag3 == 0){
                asa.setPetattr("生命恢复: " + formatter.format(level * 0.5));
                flag3++;
                i--;
            }
        }
    }
    public void talentMsgZombie(Player player){
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        if (Identify.getPet(player,8) == null){
            return;
        }
        ItemMeta petmeta = Identify.getPet(player,8);
        int level = Pet.getPetLevel(player);
        List<String> lore = petmeta != null ? petmeta.getLore() : null;
        for (int i = 0; i < (lore != null ? lore.size() : 0); i++){
            String line = lore.get(i);
            if (line.contains("[寒冰]") && !line.contains("§7[") && flag == 0){
                player.sendMessage(ChatColor.WHITE + "寒冰: 寒冰基础伤害+" + level);
                flag++;
                i--;
            }
            if (line.contains("[涌能]") && !line.contains("§7[") && flag1 == 0){
                player.sendMessage(ChatColor.WHITE + "涌能: 魔力值+" + formatter.format(level * 2L));
                flag1++;
                i--;
            }
            if (line.contains("[冰盾]") && !line.contains("§7[") && flag2 == 0){
                player.sendMessage(ChatColor.WHITE + "冰盾: 寒冰抗性+" + formatter.format(level * 0.3) + "%");
                flag2++;
                i--;
            }
            if (line.contains("[邪术]") && !line.contains("§7[") && flag3 == 0){
                player.sendMessage(ChatColor.WHITE + "邪术: 15%概率吸取对方魔力" + formatter.format(level * 0.5) + "点");
                flag3++;
                i--;
            }
        }
    }
    public void Zombie(ItemStack pet, Player player){
        ItemMeta petmeta = pet.getItemMeta();
        int level = Pet.getPetLevel(player);
        List<String> lore = petmeta.getLore();
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        AddSourceAttribute asa = EventListener.getPlayerAsa(player);
        for (int i = 0;i < lore.size();i++){
            String line = lore.get(i);
            if (line.contains("[寒冰]") && !line.contains("§7[") && flag == 0){
                asa.setPetattr("寒冰基础伤害: " + level);
                flag++;
                i--;
            }
            if (line.contains("[涌能]") && !line.contains("§7[") && flag1 == 0){
                asa.setPetattr("魔力值: " + formatter.format(level * 2L));
                flag1++;
                i--;
            }
            if (line.contains("[冰盾]") && !line.contains("§7[") && flag2 == 0){
                asa.setPetattr("寒冰抗性: " + formatter.format(level * 0.3));
                flag2++;
                i--;
            }
            if (line.contains("[邪术]") && !line.contains("§7[") && flag3 == 0){
                asa.setPetattr("魔力吸取: " + formatter.format(level * 0.5));
                flag3++;
                i--;
            }
        }
    }
    public void talentMsgBat2(Player player){
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        if (Identify.getPet(player,8) == null){
            return;
        }
        ItemMeta petmeta = Identify.getPet(player,8);
        int level = Pet.getPetLevel(player);
        List<String> lore = petmeta != null ? petmeta.getLore() : null;
        for (int i = 0; i < (lore != null ? lore.size() : 0); i++){
            String line = lore.get(i);
            if (line.contains("[火焰]") && !line.contains("§7[") && flag == 0){
                player.sendMessage(ChatColor.WHITE + "火焰: 火焰基础伤害+" + level);
                flag++;
                i--;
            }
            if (line.contains("[石肤]") && !line.contains("§7[") && flag1 == 0){
                player.sendMessage(ChatColor.WHITE + "石肤: PVP防御+" + formatter.format(level * 1.5));
                flag1++;
                i--;
            }
            if (line.contains("[火盾]") && !line.contains("§7[") && flag2 == 0){
                player.sendMessage(ChatColor.WHITE + "火盾: 火焰抗性+" + formatter.format(level * 0.3) + "%");
                flag2++;
                i--;
            }
            if (line.contains("[魔灵]") && !line.contains("§7[") && flag3 == 0){
                player.sendMessage(ChatColor.WHITE + "魔灵: 魔击力" + formatter.format(level * 1.2));
                flag3++;
                i--;
            }
        }
    }
    public void Bat2(ItemStack pet, Player player){
        ItemMeta petmeta = pet.getItemMeta();
        int level = Pet.getPetLevel(player);
        List<String> lore = petmeta.getLore();
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        AddSourceAttribute asa = EventListener.getPlayerAsa(player);
        for (int i = 0;i < lore.size();i++){
            String line = lore.get(i);
            if (line.contains("[火焰]") && !line.contains("§7[") && flag == 0){
                asa.setPetattr("火焰基础伤害: " + level);
                flag++;
                i--;
            }
            if (line.contains("[石肤]") && !line.contains("§7[") && flag1 == 0){
                asa.setPetattr("PVP防御: " + formatter.format(level * 1.2));
                flag1++;
                i--;
            }
            if (line.contains("[火盾]") && !line.contains("§7[") && flag2 == 0){
                asa.setPetattr("火焰抗性: " + formatter.format(level * 0.3));
                flag2++;
                i--;
            }
            if (line.contains("[魔灵]") && !line.contains("§7[") && flag3 == 0){
                asa.setPetattr("魔击力: " + formatter.format(level * 1.2));
                flag3++;
                i--;
            }
        }
    }
}
