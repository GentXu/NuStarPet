package com.rihejiu.nustarpet.hook;

import org.bukkit.entity.Player;
import org.serverct.ersha.api.AttributeAPI;
import org.serverct.ersha.attribute.data.AttributeData;

import java.util.List;

public class APHook {
    public static void setAttr(String sourceName, Player player, List<String> attr) {
        AttributeData data = AttributeAPI.getAttrData(player);
        AttributeAPI.addSourceAttribute(data, sourceName, attr);
        AttributeAPI.updateAttribute(player);
    }
    public static void takeAttr(String sourceName, Player player){
        AttributeData data = AttributeAPI.getAttrData(player);
        AttributeAPI.takeSourceAttribute(data,sourceName);
        AttributeAPI.updateAttribute(player);
    }
}
