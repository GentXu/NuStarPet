package com.rihejiu.nustarpet.listens;

import com.rihejiu.nustarpet.attribute.AddSourceAttribute;
import com.rihejiu.nustarpet.command.Identify;
import com.rihejiu.nustarpet.command.NBTUtils;
import com.rihejiu.nustarpet.command.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventListener implements Listener {
    private static final Map<UUID, AddSourceAttribute> asaMap = new HashMap<>();
    public static AddSourceAttribute getPlayerAsa(Player player) {
        return asaMap.get(player.getUniqueId());
    }
    public static final Pattern pattern = Pattern.compile("\\d+");
    @EventHandler
    public void invclose(InventoryCloseEvent i){
        Player player = (Player) i.getPlayer();
        ItemStack pet = Identify.getPetStack(player,8);
        String NBTtag = NBTUtils.getStringTag(pet,"PetId");
        if (!asaMap.containsKey(player.getUniqueId())) {
            asaMap.put(player.getUniqueId(), new AddSourceAttribute());
        }
        AddSourceAttribute asa = asaMap.get(player.getUniqueId());
        switch (NBTtag){
            case "尖牙蝙蝠":
                asa.takeAttr(player);
                asa.giveAttr(player, pet, "尖牙蝙蝠");
                break;
            case "沙王蜘蛛":
                asa.takeAttr(player);
                asa.giveAttr(player, pet, "沙王蜘蛛");
                break;
            case "萨满阿多":
                asa.takeAttr(player);
                asa.giveAttr(player, pet, "萨满阿多");
                break;
            case "通红之翼":
                asa.takeAttr(player);
                asa.giveAttr(player, pet, "通红之翼");
                break;
            default:
                asa.takeAttr(player);
                break;
        }
        if (player.getInventory().getItem(8) != null && pet.getItemMeta().getLore() != null) {
            for (String line : pet.getItemMeta().getLore()) {
                if (line.contains("需求等级: ")) {
                    Matcher levelmatcher = pattern.matcher(line);
                    if (levelmatcher.find()) {
                        int level = Integer.parseInt(levelmatcher.group());
                        if (player.getLevel() < level) {
                            asa.takeAttr(player);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[异兽]你的等级不足以使用这只异兽，异兽属性将不会生效。"));
                        }
                    }
                    break;
                }
            }
        }
    }
    @EventHandler
    public void rightclick(PlayerInteractEvent p){
        Player player = p.getPlayer();
        ItemStack itemStack = p.getItem();
        if (itemStack == null || itemStack.getItemMeta() == null || !itemStack.getItemMeta().hasDisplayName()){return;}
        if (Utils.checkPet(itemStack)){
            p.setCancelled(true);
            player.sendMessage(Utils.msgColor("&c[异兽]禁止操作。"));
        }
    }
}
