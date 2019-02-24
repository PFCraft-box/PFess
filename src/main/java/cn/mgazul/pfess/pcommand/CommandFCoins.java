package cn.mgazul.pfess.pcommand;

import cn.mgazul.pfcorelib.economy.YinLiangAPI;
import cn.mgazul.pfcorelib.message.Msg;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import cn.mgazul.pfcorelib.Formater;
import cn.mgazul.pfcorelib.util.Java;

public class CommandFCoins implements CommandExecutor{

	  @Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	    String noPerms = Msg.preall + "§c你没有权限!";
	    if (args.length == 0) {
	      if ((sender instanceof Player)) {
	        Player p = (Player)sender;
	        sender.sendMessage(Msg.preall + "§7你的鱼币: §e" + Formater.formatValue(YinLiangAPI.getYinLiang(p.getUniqueId())));
	      } else {
	        sender.sendMessage(Msg.preall + "§c此命令仅适用于玩家!");
	      }
	    }
	    if (args.length == 1) {
	        for(OfflinePlayer target : Bukkit.getOfflinePlayers()) {
	        	String name = target.getName();
	        	String argsname = args[0];
	        	if(target.getName() == null ) {
	        		sender.sendMessage(Msg.preall + "§c这个玩家不存在!");
	        		return false;
	        	}
	        	if(argsname.equals(name)) {
	        		sender.sendMessage(Msg.preall + "§7查询玩家 §7" + name + ": §6" + Formater.formatValue(YinLiangAPI.getYinLiang(target.getUniqueId())));
	        		return false;
	        	}
	        }
	    }
	    if ((args.length == 2) && (args[0].equalsIgnoreCase("reset"))) {
		      if (sender.isOp()) {
			        for(OfflinePlayer target : Bukkit.getOfflinePlayers()) {
			        	String name = target.getName();
			        	String argsname = args[1];
			        	if(target.getName() == null ) {
			        		sender.sendMessage(Msg.preall + "§c这个玩家不存在!");
			        		return false;
			        	}
			        	if(argsname.equals(name)) {
			    	        YinLiangAPI.setYinLiang(target.getUniqueId(),0.0);
			    	        sender.sendMessage(Msg.preall + name + " §a的账户已重置.");
			    	        return false;
			        	}
			        }
		      } else {
		        sender.sendMessage(noPerms);
		      }
		    } 
		    if ((args.length == 3) && (args[0].equalsIgnoreCase("add"))) {
			      if (sender.isOp()) {
				        for(OfflinePlayer target : Bukkit.getOfflinePlayers()) {
				        	String name = target.getName();
				        	String argsname = args[1];
				        	if(target.getName() == null ) {
				        		sender.sendMessage(Msg.preall + "§c这个玩家不存在!");
				        		return false;
				        	}
				        	if(argsname.equals(name)) {
						        String coins = args[2];			        
						        if(Java.isNumeric(coins)) {
							          YinLiangAPI.addYinLiang(target.getUniqueId(), Double.valueOf(coins));
							          sender.sendMessage(Msg.preall + "§7" + name + " §a添加鱼币 §6" + Formater.formatValue(Double.valueOf(coins)));	      
						        }else {
						        	 sender.sendMessage("无效数字");
							   }
				    	        return false;
				        	}
				        }
			      	}else {
			      		sender.sendMessage(noPerms);
			      	}
				}
		    if ((args.length == 2) && (args[0].equalsIgnoreCase("adl"))) {
			      if (sender.isOp()) {
				        for(OfflinePlayer target : Bukkit.getOfflinePlayers()) {
				        	String coins = args[1];
				        	if(Java.isNumeric(coins)) {
					        	if(target.getName() == null ) {
					        		return false;
					        	}
					        	YinLiangAPI.addYinLiang(target.getUniqueId(), Double.valueOf(coins));
				        		sender.sendMessage(Msg.preall +"§7" + target.getName() + " §a添加鱼币§6" + Formater.formatValue(Double.valueOf(coins)));	      
				        	}else {
				        		sender.sendMessage("无效数字");
				        	}
				        }
			      }else {
			        sender.sendMessage(noPerms);
			      }
				}
		    if ((args.length == 3) && (args[0].equalsIgnoreCase("del"))) {
			      if (sender.isOp()) {
				        for(OfflinePlayer target : Bukkit.getOfflinePlayers()) {
				        	String name = target.getName();
				        	String argsname = args[1];
				        	if(target.getName() == null ) {
				        		sender.sendMessage(Msg.preall + "§c这个玩家不存在!");
				        		return false;
				        	}
				        	if(argsname.equals(name)) {
						        String coins = args[2];			        
						        if(Java.isNumeric(coins)) {
							          YinLiangAPI.removeYinLiang(target.getUniqueId(), Double.valueOf(coins));
							          sender.sendMessage(Msg.preall + name + " §a失去鱼币 §6" + Double.valueOf(Double.valueOf(coins)));
						        }else {
						        	 sender.sendMessage("无效数字");
							   }
				    	        return false;
				        	}
				        }			        
			      }else {
			        sender.sendMessage(noPerms);
			      }
				}
		    if ((args.length == 3) && (args[0].equalsIgnoreCase("set"))) {
			      if (sender.isOp()) {
				        for(OfflinePlayer target : Bukkit.getOfflinePlayers()) {
				        	String name = target.getName();
				        	String argsname = args[1];
				        	if(target.getName() == null ) {
				        		sender.sendMessage(Msg.preall + "§c这个玩家不存在!");
				        		return false;
				        	}
				        	if(argsname.equals(name)) {
						        String coins = args[2];			        
						        if(Java.isNumeric(coins)) {
							          YinLiangAPI.setYinLiang(target.getUniqueId(), Double.valueOf(coins));
							          sender.sendMessage(Msg.preall + "§a已设置玩家 §7" + name + " §a的鱼币为 §6" + Double.valueOf(Double.valueOf(coins)));
							        }else {
						        	sender.sendMessage("无效数字");
							   }
				    	        return false;
				        	}
				        }
			      }else {
			        sender.sendMessage(noPerms);
			      }
				}	    
	    return true;
	  }
	
}
