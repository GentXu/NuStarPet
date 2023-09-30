package com.rihejiu.nustarpet.attribute;

import com.rihejiu.nustarpet.hook.APHook;
import com.rihejiu.nustarpet.listens.EventListener;
import com.rihejiu.nustarpet.taltent.PetTalent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddSourceAttribute {
    public static final Pattern pattern = Pattern.compile("(\\d+)\\+(\\d+)");
    public static final Pattern pattern2 = Pattern.compile("\\d+/\\d+");
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getSpirit() {
        return spirit;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public String getPetnm(){
        return petnm;
    }
    public void setPetnm(String name){
        this.petnm = name;
    }
    private int power;
    private int wisdom;
    private int spirit;
    private int speed;
    private String petnm;
    public void setPetattr(String attr){
        petattr.add(attr);
    }

    public List<String> getPetattr() {
        return petattr;
    }

    List<String> petattr = new ArrayList<>();

    int flag;
    public void giveAttr(Player player, ItemStack pets, String petname){
        flag = 0;
        AddSourceAttribute asa = EventListener.getPlayerAsa(player);
        ItemMeta pet = pets.getItemMeta();
        asa.setPetnm(petname);
        for (String line:pet.getLore()){
            if (line.contains("力量: ")){
                Matcher powermatcher = pattern.matcher(line);
                if (powermatcher.find())
                     asa.setPower(Integer.parseInt(powermatcher.group(1)) + Integer.parseInt(powermatcher.group(2)));
                continue;
            }
            if (line.contains("敏捷: ")){
                Matcher speedmatcher = pattern.matcher(line);
                if (speedmatcher.find())
                    asa.setSpeed(Integer.parseInt(speedmatcher.group(1)) + Integer.parseInt(speedmatcher.group(2)));
                continue;
            }
            if (line.contains("体力: ")){
                Matcher spiritmatcher = pattern.matcher(line);
                if (spiritmatcher.find())
                    asa.setSpirit(Integer.parseInt(spiritmatcher.group(1)) + Integer.parseInt(spiritmatcher.group(2)));
                continue;
            }
            if (line.contains("智慧: ")){
                Matcher wisdommatcher = pattern.matcher(line);
                if (wisdommatcher.find())
                    asa.setWisdom(Integer.parseInt(wisdommatcher.group(1)) + Integer.parseInt(wisdommatcher.group(2)));
            }
        }
        for (String line:pet.getLore()){
            if (line.contains("异兽生命值: ")) {
                Matcher matcher = pattern2.matcher(line);
                if (matcher.find()){
                    String entry = matcher.group();
                    int totalMin = Integer.parseInt(entry.split("/")[0]);
                    if (totalMin <= 0) {
                        flag = 1;
                    }
                }
                break;
            }
        }
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        if (flag != 1) {
            asa.setPetattr("攻击力: " + formatter.format(asa.getPower() * 0.3));
            asa.setPetattr("命中几率: " + formatter.format(asa.getPower() * 0.03));
            asa.setPetattr("闪避几率: " + formatter.format(asa.getSpeed() * 0.03));
            asa.setPetattr("生命值: " + formatter.format(asa.getSpirit() * 6L));
            asa.setPetattr("魔力值: " + formatter.format(asa.getWisdom() * 1.2));
            asa.setPetattr("魔击力: " + formatter.format(asa.getWisdom() * 0.1));
            PetTalent pt = new PetTalent(pets,player);
            pt.chooseName(asa.getPetnm());
            APHook.setAttr("pet", player, asa.getPetattr());
        }
    }
    public void takeAttr(Player player){
        AddSourceAttribute asa = EventListener.getPlayerAsa(player);
        asa.setWisdom(0);
        asa.setSpirit(0);
        asa.setSpeed(0);
        asa.setPower(0);
        asa.getPetattr().clear();
        asa.setPetnm("无异兽");
        APHook.takeAttr("pet",player);
    }
}
