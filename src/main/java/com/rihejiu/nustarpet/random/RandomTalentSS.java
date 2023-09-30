package com.rihejiu.nustarpet.random;

import java.util.Random;

public class RandomTalentSS {
    private static final String[] characters = {"D", "C", "B", "A", "S","SS"};
    // 字符对应的权重
    private static final int[] weights = {9, 6, 5, 4, 3,1};

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
}
