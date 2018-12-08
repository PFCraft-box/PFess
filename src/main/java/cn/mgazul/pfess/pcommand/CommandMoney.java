package cn.mgazul.pfess.pcommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cn.mgazul.pfcorelib.Formater;
import cn.mgazul.pfcorelib.MoneyAPI;
import cn.mgazul.pfcorelib.Msg;
import cn.mgazul.pfcorelib.util.Java;

public class CommandMoney implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	    String noPerms = Msg.preall + "§c你没有权限!";
	    if (args.length == 0) {
	      if ((sender instanceof Player)) {
	        Player p = (Player)sender;
	        sender.sendMessage(Msg.preall + "§7你的铜钱: §e" +Formater.formatValue(MoneyAPI.getMoneys(p.getUniqueId())));
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
	        		sender.sendMessage(Msg.preall + "§7查询玩家 §7" + name + ": §6" + Formater.formatValue(MoneyAPI.getMoneys(target.getUniqueId())));
	        		return false;
	        	}
	        }
	    } 
	    if (args.length == 3) {
	        Player target = Bukkit.getPlayer(args[1]);
	        if (args[0].equalsIgnoreCase("pay")) {
	        if (target != null) {
	        	if(target==sender) {
					 sender.sendMessage(ChatColor.AQUA + "你不能转账给自己!");
					 return false;
	        	}
	        	String coins = args[2];
	        	Player p = (Player)sender;
	        	
	        	if( MoneyAPI.getMoneys(p.getUniqueId()) >Double.valueOf(coins)) {
	        	 MoneyAPI.addMoneys(target.getUniqueId(), Double.valueOf(coins));
	        	 MoneyAPI.removeMoneys(p.getUniqueId(), Double.valueOf(coins));
	        	 sender.sendMessage(Msg.preall + "§7你转账给 §7" + target.getDisplayName() + ": §e" +coins);
	        	 target.sendMessage(Msg.preall + "§7你收到 §7"+ sender.getName()+": §e"+coins);
	        	}else {
	        		sender.sendMessage(Msg.preall + "§c你的余额不足!");
	        	}
	        } else {
	          sender.sendMessage(Msg.preall + "§c这个玩家不在线!");
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
		    	        MoneyAPI.setMoneys(target.getUniqueId(),0.0);
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
					        MoneyAPI.addMoneys(target.getUniqueId(), Double.valueOf(coins));
					          sender.sendMessage(Msg.preall + "§7" + name + " §a添加铜钱 §6" + Formater.formatValue(Double.valueOf(coins)));	      
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
			        for(Player target : Bukkit.getOnlinePlayers()) {
			        	String coins = args[1];
			        	if(Java.isNumeric(coins)) {
				        	if(target.getName() == null ) {
				        		return false;
				        	}
			        		MoneyAPI.addMoneys(target.getUniqueId(), Double.valueOf(coins));
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
						          MoneyAPI.removeMoneys(target.getUniqueId(), Double.valueOf(coins));
						          sender.sendMessage(Msg.preall + name + " §a失去铜钱 §6" + Formater.formatValue(Double.valueOf(coins)));
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
					        	MoneyAPI.setMoneys(target.getUniqueId(), Double.valueOf(coins));
						          sender.sendMessage(Msg.preall + "§a已设置玩家 §7" + name + " §a的铜钱为 §6" + Formater.formatValue(Double.valueOf(coins)));
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
