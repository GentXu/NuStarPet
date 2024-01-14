package com.rihejiu.nustarpet.listens;

import com.rihejiu.nustarpet.attribute.AddSourceAttribute;
import com.rihejiu.nustarpet.command.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityDamageEntityListener implements Listener {
    private static final List<EntityDamageEvent.DamageCause> causes = Arrays.asList(EntityDamageEvent.DamageCause.ENTITY_ATTACK, EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK);

    public static final Pattern pattern = Pattern.compile("\\d+/\\d+");

    @EventHandler
    public void damage(EntityDamageByEntityEvent event) {
        if (event.isCancelled()){return;}
        if (!causes.contains(event.getCause()))
            return;
        if (event.getEntity() instanceof Player) {
            Player player = (Player)event.getEntity();
            ItemStack items = player.getInventory().getItem(8);
            updateItems(player, items);
            player.updateInventory();
        }
    }

    public void updateItems(Player player, ItemStack... items) {
        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR && item
                    .hasItemMeta() && item.getItemMeta().hasLore())
                subDur(player, item);
        }
    }

    private void subDur(Player player, ItemStack item) {
        int index = 0;
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lores = itemMeta.getLore();
        AddSourceAttribute asa = new AddSourceAttribute();
        for (String line : lores) {
            if (line.contains("异兽生命值: ")) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String entry = matcher.group();
                    int totalMin = Integer.parseInt(entry.split("/")[0]) - 1;
                    int totalMax = Integer.parseInt(entry.split("/")[1]);
                    if (totalMin < 0) {
                        asa.takeAttr(player);
                        player.sendMessage(Utils.msgColor("&c[异兽]你的异兽已阵亡。"));
                        return;
                    }
                        lores.set(index, ChatColor.translateAlternateColorCodes('&',"&c异兽生命值: ") + totalMin + "/" + totalMax);
                }
            }
            index++;
        }
        itemMeta.setLore(lores);
        item.setItemMeta(itemMeta);
    }
}
