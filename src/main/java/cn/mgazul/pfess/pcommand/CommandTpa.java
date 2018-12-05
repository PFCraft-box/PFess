package cn.mgazul.pfess.pcommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import cn.mgazul.pfcorelib.Msg;
import cn.mgazul.pfcorelib.MsgAPI;
import cn.mgazul.pfess.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandTpa implements CommandExecutor{
	
	  @Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		  if ((sender instanceof Player)) {
			  Player p = (Player)sender;
					  if (args.length ==1){
						  int cooldown = 5;
						  if (Main.tpaCooldown.containsKey(p.getName())){
							  long diff = (System.currentTimeMillis() - Main.tpaCooldown.get(sender.getName()).longValue()) / 1000L;
							  if (diff < cooldown){
								  MsgAPI.sendMsgToPlayer(p, Msg.preall+"&6你必须等 &6" + cooldown + "&2秒后再次使用此指令.");
								  return false;
							  }
						  }
						  final Player target = Bukkit.getServer().getPlayer(args[0]);
						  long keepAlive = 30 * 20L;
						 if (target == null){
							 MsgAPI.sendMsgToPlayer(p, Msg.preall+"&c您只能向在线玩家发送传送请求.");
							 return false;
						 }
						 if (target == p){
							 MsgAPI.sendMsgToPlayer(p, Msg.preall + "&c你不能传送到自己.");
							 return false;
	         	     }
						 sendRequest(p, target);
	          
						 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable(){
							 @Override
							public void run(){
								 killRequest(target.getName());
							 }
						 }, keepAlive);     
						 Main.tpaCooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis()));
					  }else{
						  MsgAPI.sendMsgToPlayer(p, Msg.preall+"&6发送传送请求给玩家.");
						  MsgAPI.sendMsgToPlayer(p, Msg.preall+"&6/tpa <玩家名>");
					  }
		  }else{
			  sender.sendMessage(MsgAPI.colormsg(Msg.preall+"&c后台无法使用."));
			  return false;
		  }
	      return false;
	    }
	    

	  
	  public boolean killRequest(String key){
	    if (Main.currentRequest.containsKey(key)){
	      Player loser = Bukkit.getServer().getPlayer(Main.currentRequest.get(key));
	      if (loser != null) {
	    	  MsgAPI.sendMsgToPlayer(loser, Msg.preall+"&c您的传送请求超时.");
	      }
	      Main.currentRequest.remove(key);   
	      return true;
	    }
	    return false;
	  }
	  
	  public void sendRequest(Player p, Player p1){
		  MsgAPI.sendMsgToPlayer(p, Msg.preall+"&2已发送传送请求到&6 " + p1.getName() + ".");
		  p1.playSound(p1.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.3F, 0.3F);
	      MsgAPI.sendMsgToPlayer(p1, Msg.preall+ "&6"+p.getName() + "&2已经发送了一个传送请求给你.");
          TextComponent component = new TextComponent(ChatColor.translateAlternateColorCodes('&', "            &6接受"));
          component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "§6点击接受")).create()));
          component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"));
  	    
          TextComponent component1 = new TextComponent(ChatColor.translateAlternateColorCodes('&', "      &6拒绝"));
  	      component1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "§6点击拒绝")).create()));
  	      component1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny"));
	      p1.spigot().sendMessage(component, component1);
	    
	    Main.currentRequest.put(p1.getName(), p.getName());
	  }
	
}
