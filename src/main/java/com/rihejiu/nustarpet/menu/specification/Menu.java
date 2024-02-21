package com.rihejiu.nustarpet.menu.specification;

import org.bukkit.inventory.Inventory;

/**
 * Menu接口，定义了菜单方法规范
 */
interface Menu {
    Inventory toBukkitInventory();
    void build(Inventory inventory);
    void open();
}
