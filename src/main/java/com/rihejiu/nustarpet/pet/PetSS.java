package com.rihejiu.nustarpet.pet;

import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.random.RandomTalentSS;

public class PetSS {
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

    public int getTalentPowerNum() {return TalentPowerNum;}

    public int getTalentSpeedNum() {
        return TalentSpeedNum;
    }

    public int getTalentSpiritNum() {
        return TalentSpiritNum;
    }

    public int getTalentWisdomNum() {
        return TalentWisdomNum;
    }

    String TalentPower =  RandomTalentSS.generateRandomCharacter();
    String TalentSpeed =  RandomTalentSS.generateRandomCharacter();
    String TalentSpirit =  RandomTalentSS.generateRandomCharacter();
    String TalentWisdom =  RandomTalentSS.generateRandomCharacter();
    int TalentPowerNum = NBTUtils.getTalentNum(TalentPower);
    int TalentSpeedNum = NBTUtils.getTalentNum(TalentSpeed);
    int TalentSpiritNum = NBTUtils.getTalentNum(TalentSpirit);
    int TalentWisdomNum = NBTUtils.getTalentNum(TalentWisdom);
}
