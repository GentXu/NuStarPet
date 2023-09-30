package com.rihejiu.nustarpet.hook;

import org.bukkit.Bukkit;

import java.util.Random;

public class NeigeHook {
    public static void JinghuaCommand(String player,String item,int min,int max){
        Random rm = new Random();
        int num = rm.nextInt(max - min + 1) + min;
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ni give " + player + " " + item + " " + num);
    }
    public static void ShouhunCommand(String player,String item,int num){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ni give " + player + " " + item + " " + num);
    }
}
