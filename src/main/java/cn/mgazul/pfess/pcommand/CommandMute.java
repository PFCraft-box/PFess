package cn.mgazul.pfess.pcommand;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cn.mgazul.pfess.Main;

public class CommandMute implements CommandExecutor {
	
	public static ArrayList<Player> jy = new ArrayList<Player>();

	  @Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		  
	      if ((args.length == 0)&&(sender.isOp())){
		        sender.sendMessage("§b§l==============================");
		        sender.sendMessage("§b§l/mute             获得帮助");
		        sender.sendMessage("§b§l/mute on          开启全员禁言");
		        sender.sendMessage("§b§l/mute off         关闭全员禁言");
		        sender.sendMessage("§b§l/mute jy [玩家]    禁言玩家");
		        sender.sendMessage("§b§l/mute unjy [玩家]  解禁玩家");
		        sender.sendMessage("§b§l==============================");
		        return true;
	      }
	      if ((args.length == 1) && (args[0].equalsIgnoreCase("on")) && (sender.isOp())){
	        	Main.plugin.enable = true;
	            sender.sendMessage(ChatColor.RED + "已开启全员禁言");
	            return true;
	      }
	      if ((args.length == 1) && (args[0].equalsIgnoreCase("off")) && (sender.isOp())){
	        	Main.plugin.enable = false;
	            sender.sendMessage(ChatColor.RED + "已关闭全员禁言");
	            return true;
	      }
	      if ((args.length == 2) && (args[0].equalsIgnoreCase("jy")) && (sender.isOp())){
	              Player p = Bukkit.getPlayer(args[1]);
	              if (p != null) {
	            	  if (!CommandMute.jy.contains(p)){
	            		  jy.add(p);
	                      sender.sendMessage(ChatColor.RED + "已禁言");
	                      p.sendMessage(ChatColor.RED + "你已被管理员禁言");
	                      return true;
	            	  }
            		  sender.sendMessage(ChatColor.RED + "已被禁言");
	            }else {
	            	sender.sendMessage(ChatColor.RED + "错误:玩家不存在或未登录");
	            }
	      }
	      if ((args.length == 2) && (args[0].equalsIgnoreCase("unjy")) && (sender.isOp())){
		              Player p = Bukkit.getPlayer(args[1]);
		              if (p != null) {
		            	  if (!CommandMute.jy.contains(p)){	
		            		  sender.sendMessage(ChatColor.RED + "没有被禁言");
		            		  return true;
		          	      }
	        	        	jy.remove(p);
	        	        	sender.sendMessage(ChatColor.RED + "已解除禁言");
	                      p.sendMessage(ChatColor.RED + "你已被管理员解除禁言");
		            }else {
		            	sender.sendMessage(ChatColor.RED + "错误:玩家不存在或未登录");
		            }
		  }
	    return false;
	  }
}
