package com.rihejiu.nustarpet.random.pettalent;

import com.rihejiu.nustarpet.random.RandomTalent;

import java.util.Random;

public class Zhanhun {
    private static final String[] strings = {"[高级飞行]", "[鹰眼]", "[冰盾]", "[邪术]","[啄目]","[高级闪电]"};
    private static final int[] weights = {1, 1, 1, 1, 1, 1};
    private static final Random random = new Random();

    public static String generateRandomString() {
        return RandomTalent.generateRandomString(random,weights,strings);
    }
}
