package com.rihejiu.nustarpet.menu;

import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.command.Utils;
import com.rihejiu.nustarpet.menu.specification.AbstractMenu;
import com.rihejiu.nustarpet.menu.specification.MenuHolder;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

import java.util.ArrayList;
import java.util.List;

public class InheritMenu2 extends AbstractMenu {
    protected ItemStack inheritPet;
    protected ItemStack inheritedPet;
    protected String inheritPetName;
    protected String inheritPetPower;
    protected String inheritPetSpeed;
    protected String inheritPetSpirit;
    protected String inheritPetWisdom;
    protected String inheritedPetName;
    public InheritMenu2(Player player,ItemStack inheritPet,ItemStack inheritedPet){
        this.owner = player;
        this.size = 45;
        this.title = "确认传承";
        this.inheritPet = inheritPet;
        this.inheritedPet = inheritedPet;
        this.inheritPetName = inheritPet.getItemMeta().getDisplayName();
        this.inheritedPetName = inheritedPet.getItemMeta().getDisplayName();
        this.inheritPetPower = NBTUtils.getStringTag(inheritPet,"力量");
        this.inheritPetSpeed = NBTUtils.getStringTag(inheritPet,"敏捷");
        this.inheritPetSpirit = NBTUtils.getStringTag(inheritPet,"体力");
        this.inheritPetWisdom = NBTUtils.getStringTag(inheritPet,"智慧");
        this.components = Bukkit.createInventory(new MenuHolder(this,"确认传承"),this.size,this.title);
        build(this.components);
    }
    @Override
    public void build(Inventory components){
        Dye dye = new Dye();
        DyeColor orange = DyeColor.RED;
        DyeColor green = DyeColor.MAGENTA;
        DyeColor red = DyeColor.ORANGE;
        DyeColor blue = DyeColor.YELLOW;
        ItemStack glass = MenuItem.menuItem(Material.STAINED_GLASS_PANE,"-",null);
        for (int i = 0; i < 45; i++) {
            if (i != 11 && i != 13 && i != 15 && i != 29 && i != 30 && i != 32 && i != 33){
                components.setItem(i,glass);
            }
        }
        components.setItem(11,this.inheritedPet);
        components.setItem(15,this.inheritPet);
        components.setItem(13,MenuItem.menuItem(Material.SIGN,Utils.lineColor("&e玩法介绍"),signLore()));
        components.setItem(29,MenuItem.menuItemWithColor(dye,orange,Material.STAINED_GLASS_PANE,Utils.lineColor("&e传承属性: " + "力量[" + inheritPetPower + "]"),null));
        components.setItem(30,MenuItem.menuItemWithColor(dye,green,Material.STAINED_GLASS_PANE,Utils.lineColor("&e传承属性: " + "敏捷[" + inheritPetSpeed + "]"),null));
        components.setItem(32,MenuItem.menuItemWithColor(dye,blue,Material.STAINED_GLASS_PANE,Utils.lineColor("&e传承属性: " + "智慧[" + inheritPetWisdom + "]"),null));
        components.setItem(33,MenuItem.menuItemWithColor(dye,red,Material.STAINED_GLASS_PANE,Utils.lineColor("&e传承属性: " + "体力[" + inheritPetSpirit + "]"),null));
    }
    public List<String> signLore(){
        List<String> lore = new ArrayList<>();
        lore.add(Utils.lineColor("&f请选择即将传承成长属性"));
        lore.add(Utils.lineColor("&f被传异兽: " + inheritedPetName));
        lore.add(Utils.lineColor("&f传承异兽: " + inheritPetName));
        lore.add(Utils.lineColor("&f克隆果实: " + Utils.checkItem(this.owner,"克隆果实") + "/" + "1"));
        lore.add(Utils.lineColor("&f青涩果实: " + Utils.checkItem(this.owner,"青涩果实") + "/" + "5"));
        return lore;
    }

}
