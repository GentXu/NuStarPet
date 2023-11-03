package com.rihejiu.nustarpet.random.pettalent;

import com.rihejiu.nustarpet.random.RandomTalent;

import java.util.Random;

public class KongLang {
    private static final String[] strings = {"[嚎叫]", "[高级旋风]", "[强壮]", "[狂暴]"};
    private static final int[] weights = {8, 5, 3, 1};
    private static final Random random = new Random();

    public static String generateRandomString() {
        return RandomTalent.generateRandomString(random,weights,strings);
    }
}
