package com.rihejiu.nustarpet.pet;

import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.random.RandomTalent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Pet {
    protected RandomTalent randomTalent = new RandomTalent();

    public String getTalentPower() {
        return TalentPower;
    }

    public String getTalentSpeed() {
        return TalentSpeed;
    }

    public String getTalentSpirit() {
        return TalentSpirit;
    }

    public String getTalentWisdom() {
        return TalentWisdom;
    }

    public int getTalentPowerNum() {
        return TalentPowerNum;
    }

    public int getTalentSpeedNum() {
        return TalentSpeedNum;
    }

    public int getTalentSpiritNum() {
        return TalentSpiritNum;
    }

    public int getTalentWisdomNum() {
        return TalentWisdomNum;
    }

    public int getMaxHealth() {
        return MaxHealth;
    }
    protected String TalentPower;
    protected String TalentSpeed;
    protected String TalentSpirit;
    protected String TalentWisdom;
    protected int TalentPowerNum;

    protected int TalentSpeedNum;
    protected int TalentSpiritNum;
    protected int TalentWisdomNum;
    protected int MaxHealth = 20;
    public Pet(){
        randomTalentPower();
        randomTalentSpeed();
        randomTalentSpirit();
        randomTalentWisdom();
    }
    public void randomTalentPower(){
        this.TalentPower = randomTalent.generateRandomCharacter();
        this.TalentPowerNum = NBTUtils.getTalentNum(this.TalentPower);
    }
    public void randomTalentSpeed(){
        this.TalentSpeed = randomTalent.generateRandomCharacter();
        this.TalentSpeedNum = NBTUtils.getTalentNum(this.TalentSpeed);
    }
    public void randomTalentSpirit(){
        this.TalentSpirit = randomTalent.generateRandomCharacter();
        this.TalentSpiritNum = NBTUtils.getTalentNum(this.TalentSpirit);
    }
    public void randomTalentWisdom(){
        this.TalentWisdom = randomTalent.generateRandomCharacter();
        this.TalentWisdomNum = NBTUtils.getTalentNum(this.TalentWisdom);
    }

    public static int getPetLevel(Player player){
        return Integer.parseInt(NBTUtils.getStringTag(player.getInventory().getItem(8),"Level"));
    }
    public static int getPetLevel(ItemStack pet){
        return Integer.parseInt(NBTUtils.getStringTag(pet,"Level"));
    }
    public static int getPetExp(ItemStack pet){
        return Integer.parseInt(NBTUtils.getStringTag(pet,"Exp"));
    }
    public static int getPetMaxExp(ItemStack pet){
        return Integer.parseInt(NBTUtils.getStringTag(pet,"MaxExp"));
    }
    public static int getPetNextLevelMaxExp(ItemStack pet){
        int petLevel = getPetLevel(pet);
        return 90 * petLevel * petLevel + 10 * petLevel + 30;
    }
}
