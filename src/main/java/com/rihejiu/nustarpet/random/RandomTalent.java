package com.rihejiu.nustarpet.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomTalent {
    private static final String[] characters = {"D", "C", "B", "A", "S"};
    // 字符对应的权重
    private static final int[] weights = {9, 6, 4, 2, 1};

    // 随机数生成器
    private static final Random random = new Random();

    // 生成随机字符的方法
    public static String generateRandomCharacter() {
        int totalWeight = 0;
        for (int weight : weights) {
            totalWeight += weight;
        }

        int randomWeight = random.nextInt(totalWeight);
        int cumulativeWeight = 0;

        for (int i = 0; i < characters.length; i++) {
            cumulativeWeight += weights[i];
            if (randomWeight < cumulativeWeight) {
                return characters[i];
            }
        }

        // 如果权重设置有问题，默认返回第一个字符
        return characters[0];
    }
    public static String generateRandomString(Random random,int[] weights,String [] strings) {
        int randomCount = random.nextInt(strings.length) + 1;
        List<String> selectedStrings = new ArrayList<>();

        for (int i = 0; i < randomCount; i++) {
            int totalWeight = Arrays.stream(weights).sum();
            int randomWeight = random.nextInt(totalWeight);
            int cumulativeWeight = 0;

            for (int j = 0; j < strings.length; j++) {
                cumulativeWeight += weights[j];
                if (randomWeight < cumulativeWeight && !selectedStrings.contains(strings[j])) {
                    selectedStrings.add(strings[j]);
                    break;
                }
            }
        }

        return String.join("", selectedStrings);
    }
}
