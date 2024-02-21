package com.rihejiu.nustarpet.pet;

import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.random.RandomTalentSS;

/**
 * 不会出现SS天赋的宠物对象，继承于宠物
 */
public class PetWithoutSS extends Pet{
    public PetWithoutSS(){
        randomTalentPower();
        randomTalentSpirit();
        randomTalentWisdom();
        randomTalentSpeed();
        this.randomTalent = new RandomTalentSS();
    }
    @Override
    public void randomTalentPower(){
        this.TalentPower = randomTalent.generateRandomCharacter();
        this.TalentPowerNum = NBTUtils.getTalentNum(this.TalentPower);
    }
    @Override
    public void randomTalentSpeed(){
        this.TalentSpeed = randomTalent.generateRandomCharacter();
        this.TalentSpeedNum = NBTUtils.getTalentNum(this.TalentSpeed);
    }
    @Override
    public void randomTalentSpirit(){
        this.TalentSpirit = randomTalent.generateRandomCharacter();
        this.TalentSpiritNum = NBTUtils.getTalentNum(this.TalentSpirit);
    }
    @Override
    public void randomTalentWisdom(){
        this.TalentWisdom = randomTalent.generateRandomCharacter();
        this.TalentWisdomNum = NBTUtils.getTalentNum(this.TalentWisdom);
    }

}
