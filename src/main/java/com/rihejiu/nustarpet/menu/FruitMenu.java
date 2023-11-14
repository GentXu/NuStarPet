package com.rihejiu.nustarpet.menu;

import com.rihejiu.nustarpet.command.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class FruitMenu {
    public Inventory components;
    public Player owner;
    public static final String TITLE = "喂养果实";
    public FruitMenu(Player player){
        components = Bukkit.createInventory(player,9,TITLE);
        owner = player;
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("-");
        glass.setItemMeta(glassMeta);
        ItemStack flower = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta flowerMeta = flower.getItemMeta();
        flowerMeta.setDisplayName(ChatColor.YELLOW + "确定喂养");
        flower.setItemMeta(flowerMeta);
        ItemStack sign = new ItemStack(Material.SIGN);
        ItemMeta signMeta = sign.getItemMeta();
        signMeta.setDisplayName(ChatColor.YELLOW + "玩法介绍");
        List<String> lore = new ArrayList<>();
        lore.add(Utils.msgColor("&f<- 异兽    果实 ->"));
        signMeta.setLore(lore);
        sign.setItemMeta(signMeta);
        // 菜单绘制
        for (int i = 0; i < 8; i++) {
            if (i != 2 && i != 4 && i != 6){
                components.setItem(i,glass);
            }

        }
        components.setItem(8,flower);
        components.setItem(4,sign);
    }
    // 打开该菜单的方法
    public void open(){
        owner.openInventory(components);
    }
}
