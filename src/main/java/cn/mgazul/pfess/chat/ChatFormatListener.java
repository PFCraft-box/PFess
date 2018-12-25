package cn.mgazul.pfess.chat;

import cn.mgazul.pfess.PFessPapiHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormatListener implements Listener{
	  
	  @EventHandler
	  public void onChat(AsyncPlayerChatEvent e){
	    Player p = e.getPlayer();
	    e.setCancelled(true);
	    Bukkit.getServer().broadcastMessage(PFessPapiHook.replacepapi(p,"&f[%pfw_worldname%&f]%pfess_tags%&f<"+p.getName()+"&f>&7 ")+ e.getMessage());
	  }
	  
}
