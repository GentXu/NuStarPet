package com.rihejiu.nustarpet;

import com.rihejiu.nustarpet.listens.*;
import com.rihejiu.nustarpet.menu.event.Monitor;
import net.milkbowl.vault.economy.Economy;
import com.rihejiu.nustarpet.command.Commands;
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
        Objects.requireNonNull(Bukkit.getPluginCommand("nspet")).setExecutor(new Commands());
        loadListeners();
        console("[NuStarPet]§a _   _       ____  _             ____      _   ");
        console("[NuStarPet]§a| \\ | |_   _/ ___|| |_ __ _ _ __|  _ \\ ___| |_ ");
        console("[NuStarPet]§a|  \\| | | | \\___ \\| __/ _` | '__| |_) / _ \\ __|");
        console("[NuStarPet]§a| |\\  | |_| |___) | || (_| | |  |  __/  __/ |_ ");
        console("[NuStarPet]§a|_| \\_|\\__,_|____/ \\__\\__,_|_|  |_|   \\___|\\__|");
        console("§f宠物插件开启成功");
        console("§c作者: §93318029085");
    }
    private void loadListeners(){
        getServer().getPluginManager().registerEvents(new EntityDamageEntityListener(), this);
        getServer().getPluginManager().registerEvents( new AttrGiveListener(), this);
        getServer().getPluginManager().registerEvents(new EvolveListener(),this);
        getServer().getPluginManager().registerEvents(new Monitor(),this);
        getServer().getPluginManager().registerEvents(new ImmolateListener(),this);
        getServer().getPluginManager().registerEvents(new FruitListener(),this);
        getServer().getPluginManager().registerEvents(new InheritListener(),this);
        getServer().getPluginManager().registerEvents(new InheritListener1(),this);
        getServer().getPluginManager().registerEvents(new LevelUpListener(),this);
        getServer().getPluginManager().registerEvents(new ReviveListener(),this);
        getServer().getPluginManager().registerEvents(new TaltenListener(),this);
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
