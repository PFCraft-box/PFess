package cn.mgazul.pfess.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormatListener implements Listener{
	  
	  @EventHandler
	  public void onChat(AsyncPlayerChatEvent e){
	    Player p = e.getPlayer();
	    if(e.getMessage() == null) {
	    	return;
	    }
	    if(p.isOp()) {
	    	e.setFormat("§f<§f "+p.getName()+" §f>§7 "+ e.getMessage().replace("&", "§"));
	    }
		e.setFormat("§f<§f "+p.getName()+" §f>§7 "+ e.getMessage());
	  }
	  
}
