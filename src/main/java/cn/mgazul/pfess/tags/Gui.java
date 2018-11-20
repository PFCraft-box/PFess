package cn.mgazul.pfess.tags;

import cn.mgazul.pfcorelib.MsgAPI;
import cn.mgazul.pfcorelib.configuration.PlayerdataAPI;
import cn.mgazul.pfess.PFessPapiHook;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Gui implements Listener {

    public static String menu = MsgAPI.colormsg("&6称号管理系统");

    public static ItemStack createItem( Material mat, String title) {
        ItemStack itemStack = new ItemStack(mat, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(title);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack createItem1(final Material mat, final String title, final String[] lores) {
        final ItemStack itemStack = new ItemStack(mat, 1);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(title);
        final List<String> lines = new ArrayList<String>();
        for (final String line : lores) {
            lines.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        itemMeta.setLore(lines);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static void openMenuGui(Player p){
        int pos = 0;
        Inventory inventory = Bukkit.createInventory(null, 54, menu);
        YamlConfiguration Config = PlayerdataAPI.createYaml(p.getUniqueId());
        if (Config.getConfigurationSection("Tags.") != null) {
            for(String list : Config.getConfigurationSection("Tags").getKeys(false)) {
                final ItemStack itemStack = new ItemStack(Material.NAME_TAG, 1);
                final ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(MsgAPI.colormsg(list));
                final List<String> lines = new ArrayList<String>();
                lines.add(MsgAPI.colormsg(Config.getString("Tags."+list+".info")));
                lines.add(MsgAPI.colormsg("&7获取方法: "+Config.getString("Tags."+list+".get")));
                lines.add(MsgAPI.colormsg("&7获取时间: "+Config.getString("Tags."+list+".time")));
                itemMeta.setLore(lines);
                itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                itemStack.setItemMeta(itemMeta);
                inventory.setItem(pos, itemStack);
                pos++;
            }
        }
        inventory.setItem(46, createItem(Material.REDSTONE_BLOCK, MsgAPI.colormsg("&4清除佩戴的称号")));
        inventory.setItem(48, createItem(Material.IRON_DOOR, MsgAPI.colormsg("&4关闭菜单")));
        inventory.setItem(49, createItem(Material.PLAYER_HEAD, MsgAPI.colormsg("&7已佩戴称号: ") + PFessPapiHook.replacepapi(p, "%pfess_tags%")));
        inventory.setItem(50, createItem(Material.IRON_DOOR, MsgAPI.colormsg("&4关闭菜单")));

        p.openInventory(inventory);
        for (int i = 36; i < 45; i++)
            if (inventory.getItem(i) == null){
                ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§r");
                itemStack.setItemMeta(itemMeta);
                inventory.setItem(i, itemStack);
            }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if (e.getInventory().getName().equals(menu)){
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {    //如果点击的是null，则返回
                return;
            }
            Player p = (Player)e.getWhoClicked();
            YamlConfiguration Config = PlayerdataAPI.createYaml(p.getUniqueId());
            if(e.getSlot() == 48 || e.getSlot() == 50){
                p.closeInventory();
            }
            if(e.getSlot() == 46){
                Config.set("Tag",null);
                PlayerdataAPI.saveYaml(p.getUniqueId(),Config);
                p.closeInventory();
                MsgAPI.sendMsgToPlayer(p, "&2成功清除已佩戴的称号");
            }
            if(e.getSlot() >=0 && e.getSlot() < 36 && e.getCurrentItem().hasItemMeta()) {
                if (Config.getConfigurationSection("Tags.") != null) {
                    for (String list : Config.getConfigurationSection("Tags").getKeys(false)) {
                        ItemStack item = e.getCurrentItem();
                        ItemMeta im = item.getItemMeta();
                        String itemname = im.getDisplayName();
                       if (list.equals(itemname)) {
                          Config.set("Tag",itemname);
                           PlayerdataAPI.saveYaml(p.getUniqueId(),Config);
                           p.closeInventory();
                           p.sendMessage(MsgAPI.colormsg("&7已佩戴称号: ")+itemname);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        if (e.getInventory().getName().equals(menu)) {
            HumanEntity p = e.getPlayer();
            YamlConfiguration Config = PlayerdataAPI.createYaml(p.getUniqueId());
            String normal = "§3江湖";
            final Date d = Calendar.getInstance().getTime();
            final DateFormat tm = new SimpleDateFormat("yyyy-MM-dd");
            final String date = tm.format(d);
            final Date d2 = Calendar.getInstance().getTime();
            final DateFormat tm2 = new SimpleDateFormat("HH:mm:ss");
            final String time = tm2.format(d2);
            if (Config.get("Tags."+normal) == null) {
                Config.set("Tag",normal);
                Config.set("Tags."+normal+".is",false);
                Config.set("Tags."+normal+".time", date + " " + time);
                Config.set("Tags."+normal+".info", "&2有你在的地方,就是江湖.");
                Config.set("Tags."+normal+".get", "&e首次打开称号系统并关闭即可获得.");
                PlayerdataAPI.saveYaml(p.getUniqueId(),Config);
            }
        }
    }
}
