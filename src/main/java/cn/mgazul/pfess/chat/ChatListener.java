package cn.mgazul.pfess.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener{
	
	@EventHandler
	  public void onPlayerCommandPreprocess(AsyncPlayerChatEvent e){
		String[] arrayOfString;
		Player p = e.getPlayer();
        int j = (arrayOfString = e.getMessage().split(" ")).length;
        for (int i = 0; i < j; i++){
          String word = arrayOfString[i];
          if (word.contains("@")){
            String name = word.replaceFirst("@", "");
            Player target = Bukkit.getPlayerExact(name);
            if (target == null) {
              return;
            }            
			if (target == p ) {
				p.sendMessage("§c§l你不能@自己哟！");
                e.setCancelled(true);
                return;
              }
			e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.GRAY + "你成功@了" + ChatColor.GRAY + target.getDisplayName());
            target.sendMessage(ChatColor.GRAY + ""+e.getPlayer().getDisplayName() + "@了你 ");
            target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 0.0F);
          }
        }
    }	 
}
