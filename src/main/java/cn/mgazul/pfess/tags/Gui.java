package cn.mgazul.pfess.tags;

import cn.mgazul.pfcorelib.MsgAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gui {

    public static String menu = MsgAPI.colormsg("&6称号管理系统");

    public static void openMenuGui(Player p){
        Inventory inventory = Bukkit.createInventory(null, 54, menu);


        p.openInventory(inventory);
        for (int i = 36; i < 44; i++)
            if (inventory.getItem(i) == null){
                ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§r");
                itemStack.setItemMeta(itemMeta);
                inventory.setItem(i, itemStack);
            }
    }
}
