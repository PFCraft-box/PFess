package cn.mgazul.pfess.plistener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import cn.mgazul.pfcorelib.ChatComponentAPI;
import cn.mgazul.pfcorelib.configuration.ConfigUtil;
import cn.mgazul.pfess.Main;
import cn.mgazul.pfess.pcommand.CommandVanish;

public class PlayerListener implements Listener{

	@EventHandler
	  public void onChenghao(PlayerJoinEvent event){
	    Player player = event.getPlayer();  	
	    player.setGameMode(GameMode.SURVIVAL);
	    player.setHealthScale(20D);
	    player.setAllowFlight(false);
    		ConfigUtil.create(player);  
            player.sendMessage("§c§l=================《就是江湖》=================");
            player.sendMessage( "§2§l所有信息请查阅菜单内的说明或者对应的NPC");
            player.sendMessage( "§2§l看着天上按下蹲键或者直接按F键打开菜单");
            player.sendMessage("§c§l===============================================");
	    	if (!event.getPlayer().hasPlayedBefore()){
	    		Bukkit.broadcastMessage("§d§l欢迎新玩家:§6§l"+event.getPlayer().getName());
	    		ConfigUtil.spawn(player);
	    		//传送至出生点	    			    		
	        }	  	    			      
		      for (Player p : Bukkit.getServer().getOnlinePlayers()) {	        
		    	  p.sendMessage("§3"+player.getName()+"§6已上线");
		          
		    }
		      for (Player p :  CommandVanish.vanished) {
		          p.hidePlayer(Main.plugin,p);
		        }
		    event.setJoinMessage(null);
	    }
	
	
	  @EventHandler
	  public void onQuit(PlayerQuitEvent event){
		  Player p = event.getPlayer();
		  CommandVanish.vanished.remove(p);
	      for (Player player : Bukkit.getServer().getOnlinePlayers()) {	   
	          if(player==null) {
	        	  return;
	         }
	          player.sendMessage("§3"+p.getName()+"§6已下线");
	      }
	      event.setQuitMessage(null);
	   }	
	  
	  public void AutoRespawn(final Player player, int Time){
		  Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable(){
		      @Override
			public void run(){
		        player.spigot().respawn();
		      }
		    }, Time);
		  }
	  
	  //自动复活
	  @EventHandler
	  public void onPlayerDeath(PlayerDeathEvent event){
		  Player p = event.getEntity();
		  event.setKeepInventory(true);   
		  ConfigUtil.setplayerDeathLocation(p);
		  ChatComponentAPI.sendClickChat(p, "§c你已死亡", "点击返回死亡地点", "/back");
		  AutoRespawn(p, 40);
	  }
	  
	  //传送时记录前位置
	  @EventHandler
	  public void onTpLoc(PlayerTeleportEvent event){
		  Player p = event.getPlayer();
		  if(p.hasMetadata("NPC")) return;
		  ConfigUtil.setplayerDeathLocation(p);
	  }
	  
	  @EventHandler
	  public void onPreLogin(PlayerLoginEvent e){
	    if (e.getResult() == PlayerLoginEvent.Result.KICK_FULL) {
	      e.setKickMessage("§c服务器当前已满。 稍后再试");
	    } else if (e.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {
	      e.setKickMessage("§3服务器已开启白名单");
	    }
	    if ((e.getPlayer().hasPermission("join.full"))&& 
	    	      (e.getResult() == PlayerLoginEvent.Result.KICK_FULL)) {
	    	      e.allow();
	    	    }
	  }	
	 
	  
	  @EventHandler
	  public void onKick(PlayerKickEvent e) {
	        e.setLeaveMessage(null);
	    }	    	  
}

