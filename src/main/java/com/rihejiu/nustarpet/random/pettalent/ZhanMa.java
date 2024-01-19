package com.rihejiu.nustarpet.random.pettalent;

import com.rihejiu.nustarpet.random.RandomTalent;

import java.util.Random;

public class ZhanMa {
    private static final String[] strings = {"[高级寒冰]", "[高级吸血]", "[铁皮]", "[撕裂]"};
    private static final int[] weights = {1, 1, 1, 1};
    private static final Random random = new Random();

    public static String generateRandomString() {
        return RandomTalent.generateRandomString(random,weights,strings);
    }
}
