package cn.mgazul.pfess.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import cn.mgazul.pfess.Main;

public class WorldNoCommand implements Listener{
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onCommand(PlayerCommandPreprocessEvent e){
    Player p = e.getPlayer();
    if (Main.plugin.getConfig().contains(p.getLocation().getWorld().getName())) {
      for (String cmd : Main.plugin.getConfig().getStringList(p.getLocation().getWorld().getName())) {
        if (e.getMessage().toLowerCase().contains(cmd.toLowerCase())){
        	if(p.isOp()) return;
          e.setCancelled(true);
          p.sendMessage("§c当前世界无法使用该指令！");
        }
      }
    }
  }
}
