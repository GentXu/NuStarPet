package com.rihejiu.nustarpet.random;


public class RandomTalentSS extends RandomTalent {
    // 随机数生成器
    public RandomTalentSS(){
        this.characters = new String[]{"D", "C", "B", "A", "S"};
        this.weights = new int[]{9, 6, 4, 2, 1};
    }

}
