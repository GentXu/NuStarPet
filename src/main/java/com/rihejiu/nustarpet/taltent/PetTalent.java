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
        flag4 = 0;
        flag5 = 0;
    }
    public PetTalent(ItemStack pet,Player player){
        flag = 0;
        flag1 = 0;
        flag2 = 0;
        flag3 = 0;
        flag4 = 0;
        flag5 = 0;
        this.player = player;
        this.pet = pet;
    }

    /**
     * 根据不同的宠物名调用对应的宠物天赋方法
     * @param petname   传入宠物名
     */
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
            case "恐狼":
                kongLang(pet,player);
                break;
            case "战魂":
                zhanHun(pet,player);
                break;
            case "战马":
                zhanMa(pet,player);
                break;
        }
    }
    // 不让天赋重复检测
    private int flag;
    private int flag1;
    private int flag2;
    private int flag3;
    private int flag4;
    private int flag5;

    /**
     * 对应宠物的天赋消息
     * @param petname   宠物名字
     * @param player    消息发送的玩家
     */
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
            case "恐狼":
                talentMsgKongLang(player);
                break;
            case "战魂":
                talentMsgZhanHun(player);
                break;
            case "战马":
                talentMsgZhanMa(player);
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
                asa.setPetattr("吸魔: " + formatter.format(level * 0.5));
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
    public void talentMsgKongLang(Player player){
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
            if (line.contains("[嚎叫]") && !line.contains("§7[") && flag == 0){
                player.sendMessage(ChatColor.WHITE + "嚎叫: 护甲穿透+" + formatter.format(level * 0.2) + "%");
                flag++;
                i--;
            }
            if (line.contains("[高级旋风]") && !line.contains("§7[") && flag1 == 0){
                player.sendMessage(ChatColor.WHITE + "高级旋风: 飓风基础伤害+" + formatter.format(level * 1.4));
                flag1++;
                i--;
            }
            if (line.contains("[强壮]") && !line.contains("§7[") && flag2 == 0){
                player.sendMessage(ChatColor.WHITE + "强壮: 生命值+" + level * 5);
                flag2++;
                i--;
            }
            if (line.contains("[狂暴]") && !line.contains("§7[") && flag3 == 0){
                player.sendMessage(ChatColor.WHITE + "狂暴: 连击几率" + formatter.format(level * 0.3));
                flag3++;
                i--;
            }
        }
    }
    public void talentMsgZhanHun(Player player){
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
            if (line.contains("[鹰眼]") && !line.contains("§7[") && flag == 0){
                player.sendMessage(ChatColor.WHITE + "鹰眼: 暴击几率+" + formatter.format(level * 0.25) + "%");
                flag++;
                i--;
            }
            if (line.contains("[高级飞行]") && !line.contains("§7[") && flag1 == 0){
                player.sendMessage(ChatColor.WHITE + "高级飞行: 闪避几率+" + formatter.format(level * 0.25) + "%");
                flag1++;
                i--;
            }
            if (line.contains("[高级闪电]") && !line.contains("§7[") && flag2 == 0){
                player.sendMessage(ChatColor.WHITE + "高级闪电: 雷电基础伤害+" + level * 5);
                flag2++;
                i--;
            }
            if (line.contains("[冰盾]") && !line.contains("§7[") && flag3 == 0){
                player.sendMessage(ChatColor.WHITE + "冰盾: 寒冰抗性" + formatter.format(level * 0.3) + "%");
                flag3++;
                i--;
            }
            if (line.contains("[邪术]") && !line.contains("§7[") && flag4 == 0){
                player.sendMessage(ChatColor.WHITE + "邪术: 魔力吸取" + formatter.format(level * 0.5));
                flag4++;
                i--;
            }
            if (line.contains("[啄目]") && !line.contains("§7[") && flag5 == 0){
                player.sendMessage(ChatColor.WHITE + "啄目: 命中几率" + formatter.format(level * 0.25) + "%");
                flag5++;
                i--;
            }
        }
    }
    public void talentMsgZhanMa(Player player){
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
            if (line.contains("[高级寒冰]") && !line.contains("§7[") && flag == 0){
                player.sendMessage(ChatColor.WHITE + "高级寒冰: 寒冰基础伤害+" + level * 5);
                flag++;
                i--;
            }
            if (line.contains("[高级吸血]") && !line.contains("§7[") && flag1 == 0){
                player.sendMessage(ChatColor.WHITE + "吸血: 15%几率吸取" + formatter.format(level * 3.5) + "生命值");
                flag1++;
                i--;
            }
            if (line.contains("[铁皮]") && !line.contains("§7[") && flag2 == 0){
                player.sendMessage(ChatColor.WHITE + "铁皮: 防御力+" + level * 2);
                flag2++;
                i--;
            }
            if (line.contains("[撕裂]") && !line.contains("§7[") && flag3 == 0){
                player.sendMessage(ChatColor.WHITE + "撕裂: " + formatter.format(level * 0.5) + "%几率撕裂目标,每3秒损失3%生命值，持续12秒");
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
    public void kongLang(ItemStack pet, Player player){
        ItemMeta petmeta = pet.getItemMeta();
        int level = Pet.getPetLevel(player);
        List<String> lore = petmeta.getLore();
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        AddSourceAttribute asa = EventListener.getPlayerAsa(player);
        for (int i = 0;i < lore.size();i++){
            String line = lore.get(i);
            if (line.contains("[嚎叫]") && !line.contains("§7[") && flag == 0){
                asa.setPetattr("护甲穿透: " + formatter.format(level * 0.2));
                flag++;
                i--;
            }
            if (line.contains("[高级旋风]") && !line.contains("§7[") && flag1 == 0){
                asa.setPetattr("飓风基础伤害: " + formatter.format(level * 1.4));
                flag1++;
                i--;
            }
            if (line.contains("[强壮]") && !line.contains("§7[") && flag2 == 0){
                asa.setPetattr("生命值: " + level * 5);
                flag2++;
                i--;
            }
            if (line.contains("[狂暴]") && !line.contains("§7[") && flag3 == 0){
                asa.setPetattr("连击几率: " + formatter.format(level * 0.3));
                flag3++;
                i--;
            }
        }
    }
    public void zhanHun(ItemStack pet, Player player){
        ItemMeta petmeta = pet.getItemMeta();
        int level = Pet.getPetLevel(player);
        List<String> lore = petmeta.getLore();
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        AddSourceAttribute asa = EventListener.getPlayerAsa(player);
        for (int i = 0;i < lore.size();i++){
            String line = lore.get(i);
            if (line.contains("[啄目]") && !line.contains("§7[") && flag == 0){
                asa.setPetattr("命中几率: " + formatter.format(level * 0.25));
                flag++;
                i--;
            }
            if (line.contains("[鹰眼]") && !line.contains("§7[") && flag1 == 0){
                asa.setPetattr("暴击几率: " + formatter.format(level * 0.25));
                flag1++;
                i--;
            }
            if (line.contains("[冰盾]") && !line.contains("§7[") && flag2 == 0){
                asa.setPetattr("寒冰抗性: " + formatter.format(level * 0.3));
                flag2++;
                i--;
            }
            if (line.contains("[邪术]") && !line.contains("§7[") && flag3 == 0){
                asa.setPetattr("吸魔: " + formatter.format(level * 0.5));
                flag3++;
                i--;
            }
            if (line.contains("[高级飞行]") && !line.contains("§7[") && flag4 == 0){
                asa.setPetattr("闪避几率: " + formatter.format(level * 0.25));
                flag4++;
                i--;
            }
            if (line.contains("[高级闪电]") && !line.contains("§7[") && flag5 == 0){
                asa.setPetattr("雷电基础伤害: " + level * 5);
                flag5++;
                i--;
            }
        }
    }
    public void zhanMa(ItemStack pet, Player player){
        ItemMeta petmeta = pet.getItemMeta();
        int level = Pet.getPetLevel(player);
        List<String> lore = petmeta.getLore();
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        AddSourceAttribute asa = EventListener.getPlayerAsa(player);
        for (int i = 0;i < lore.size();i++){
            String line = lore.get(i);
            if (line.contains("[高级寒冰]") && !line.contains("§7[") && flag == 0){
                asa.setPetattr("寒冰基础伤害: " + level * 5);
                flag++;
                i--;
            }
            if (line.contains("[高级吸血]") && !line.contains("§7[") && flag1 == 0){
                asa.setPetattr("吸血: " + formatter.format(level * 3.5));
                flag1++;
                i--;
            }
            if (line.contains("[铁皮]") && !line.contains("§7[") && flag2 == 0){
                asa.setPetattr("防御力: " + level * 2);
                flag2++;
                i--;
            }
            if (line.contains("[撕裂]") && !line.contains("§7[") && flag3 == 0){
                asa.setPetattr("撕裂: " + formatter.format(level * 0.5));
                flag3++;
                i--;
            }
        }
    }
}
