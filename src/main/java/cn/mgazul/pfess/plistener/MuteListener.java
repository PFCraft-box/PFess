package cn.mgazul.pfess.plistener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import cn.mgazul.pfess.Main;
import cn.mgazul.pfess.pcommand.CommandMute;

public class MuteListener implements Listener {

	  @EventHandler
	  public void OnChat(AsyncPlayerChatEvent e) {
	    Player p = e.getPlayer();
	    if (Main.plugin.enable){	      
		    if (!p.isOp()){
		    	e.setCancelled(true);
		    	p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l对不起，管理员开启了全员禁言，你无法说话"));
		    }
	    }
	    for (Player p1 : CommandMute.jy) {
	        if (p1==p) {
	            e.setCancelled(true);
	            p.sendMessage(ChatColor.RED + "管理员已将你禁言,因此你无法讲话");
	        }
	    }
	  }
}
