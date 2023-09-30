package com.rihejiu.nustarpet.random.pettalent;

import com.rihejiu.nustarpet.random.RandomTalent;
import java.util.Random;

public class Bat {
    private static final String[] strings = {"[吸血]", "[闪电]", "[寂静]", "[飞行]"};
    private static final int[] weights = {8, 5, 3, 1};
    private static final Random random = new Random();

    public static String generateRandomString() {
        return RandomTalent.generateRandomString(random,weights,strings);
    }
}
