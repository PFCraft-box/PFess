package cn.mgazul.pfess.pcommand;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cn.mgazul.pfcorelib.Msg;
import cn.mgazul.pfcorelib.MsgAPI;

public class CommandGameMode implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		  		 
		  if ((sender instanceof Player)){
			Player p = (Player)sender;
		    if (args.length == 1 && p.isOp()){		    	
		    	if(args[0].equalsIgnoreCase("0")){		        
		    		p.setGameMode(GameMode.SURVIVAL);
		    		MsgAPI.sendMsgToPlayer(p, Msg.preall+"&2已设置为生存模式");
		    	}   
		    	if (args[0].equalsIgnoreCase("1")){		        
		    		p.setGameMode(GameMode.CREATIVE);
		    		MsgAPI.sendMsgToPlayer(p, Msg.preall+"&2已设置为创造模式");
		    	} 
		    	if (args[0].equalsIgnoreCase("2")){		        
		    		p.setGameMode(GameMode.ADVENTURE);
		    		MsgAPI.sendMsgToPlayer(p, Msg.preall+"&2已设置为冒险模式");
		    	} 
		    	if (args[0].equalsIgnoreCase("3")){		        
		    		p.setGameMode(GameMode.SPECTATOR);
		    		MsgAPI.sendMsgToPlayer(p, Msg.preall+"&2已设置为旁观模式");
		    	} 
		    }
		    if (args.length == 2 && p.isOp()) {
                if (Bukkit.getPlayer(args[1]) == null) {
                	MsgAPI.sendMsgToPlayer(p, Msg.preall+"&c没有找到该玩家");
                    return true;
                }
                Player t = Bukkit.getPlayer(args[1]);
                if (args[0].equalsIgnoreCase("0")) {
                	MsgAPI.sendMsgToPlayer(p, Msg.preall+"&2已设置 &7"+ t.getDisplayName() +" &2为生存模式");
                    t.setGameMode(GameMode.SURVIVAL);
                    return true;
                }
                if (args[0].equalsIgnoreCase("1")) {
                	MsgAPI.sendMsgToPlayer(p, Msg.preall+"&2已设置 &7"+ t.getDisplayName() +" &2为创造模式");
                    t.setGameMode(GameMode.CREATIVE);
                    return true;
                }
                if (args[0].equalsIgnoreCase("2")) {
                	MsgAPI.sendMsgToPlayer(p, Msg.preall+"&2已设置 &7"+ t.getDisplayName() +" &2为冒险模式");
                    t.setGameMode(GameMode.ADVENTURE);
                    return true;
                }
                if (args[0].equalsIgnoreCase("3")) {
                	MsgAPI.sendMsgToPlayer(p, Msg.preall+"&2已设置 &7"+ t.getDisplayName() +" &2为旁观模式");
                    t.setGameMode(GameMode.SPECTATOR);
                    return true;
                }
            }
		    	}else{
			      sender.sendMessage("§c?");
			      return false;
			    }		    
	  return false;
	 }
}
