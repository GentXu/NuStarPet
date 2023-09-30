package com.rihejiu.nustarpet.random.pettalent;

import com.rihejiu.nustarpet.random.RandomTalent;

import java.util.Random;

public class Bat2 {
    private static final String[] strings = {"[火焰]", "[石肤]", "[火盾]", "[魔灵]"};
    private static final int[] weights = {8, 5, 3, 1};
    private static final Random random = new Random();

    public static String generateRandomString() {
        return RandomTalent.generateRandomString(random,weights,strings);
    }
}
