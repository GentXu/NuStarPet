package com.rihejiu.nustarpet;

import net.milkbowl.vault.economy.Economy;
import com.rihejiu.nustarpet.command.Main;
import com.rihejiu.nustarpet.listens.EntityDamageEntityListener;
import com.rihejiu.nustarpet.listens.EventListener;
import com.rihejiu.nustarpet.listens.PetLevelUp;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class NuStarPet extends JavaPlugin {
    private static PlayerPointsAPI points;
    public static PlayerPointsAPI getPoints(){return points;}
    public static Economy getEconomy() {
        return economy;
    }
    private static Economy economy = null;
    public void console(String s){
        Bukkit.getConsoleSender().sendMessage(s);
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!initVault()){
            getLogger().severe("初始化Vault支持失败,请检测是否已经安装Vault插件");
        } else {
            getLogger().severe("初始化Vault成功！");
        }
        if (!initPlayerPoints()){
            getLogger().severe("初始化PlayerPoints支持失败,请检测是否已经安装PlayerPoints插件");
        } else {
            getLogger().severe("初始化PlayerPoints成功！");
            points = PlayerPoints.getInstance().getAPI();
        }
        Objects.requireNonNull(Bukkit.getPluginCommand("nspet")).setExecutor(new Main());
        getServer().getPluginManager().registerEvents(new EntityDamageEntityListener(), this);
        getServer().getPluginManager().registerEvents(new PetLevelUp(),  this);
        getServer().getPluginManager().registerEvents( new EventListener(), this);
        console("§f宠物插件开启成功");
        console("§c作者: §93318029085");
    }
    private boolean initVault(){
        boolean hasNull = false;
        //获取权限系统实例
        //初始化经济系统实例
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            if ((economy = economyProvider.getProvider()) == null) hasNull = true;
        }
        return !hasNull;
    }
    private boolean initPlayerPoints(){
        boolean hasNull = false;
        //获取权限系统实例
        //初始化经济系统实例
        RegisteredServiceProvider<PlayerPointsAPI> PlayerPointsProvider = getServer().getServicesManager().getRegistration(org.black_ixx.playerpoints.PlayerPointsAPI.class);
        if (PlayerPointsProvider != null) {
            if ((points = PlayerPointsProvider.getProvider()) == null) hasNull = true;
        }
        return !hasNull;
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
