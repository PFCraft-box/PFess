package cn.mgazul.pfess.pcommand;

import cn.mgazul.pfcorelib.message.Msg;
import cn.mgazul.pfcorelib.message.MsgAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import cn.mgazul.pfess.Main;

public class CommandTpaccept implements CommandExecutor{

	  @Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
	    if ((sender instanceof Player)) {
	    	Player p = (Player)sender;
	    	if ((args.length == 0)){
	    		if (Main.currentRequest.containsKey(p.getName())){
	    			Player p1 = Bukkit.getServer().getPlayer(Main.currentRequest.get(p.getName()));
	    			Main.currentRequest.remove(p.getName());
	    			if (p1 != null){
	    				p1.teleport(p);
	    				MsgAPI.sendMsgToPlayer(p, Msg.preall+ "&2传送中...");
	    				MsgAPI.sendMsgToPlayer(p1, Msg.preall+ "&2传送中...");
	    			}else{
	    				MsgAPI.sendMsgToPlayer(p, Msg.preall+"&c玩家离开了服务器!");
	    				return false;
	    			}
	    		}else{
	    			MsgAPI.sendMsgToPlayer(p, Msg.preall+"&c您没有任何当前的tp请求.");
	    			return false;
	    		}
	    		return true;
	    	} 
	    }else{
	    	sender.sendMessage(MsgAPI.colormsg(Msg.preall+"&c后台无法使用."));
	    	return false;
	    }
		return false;
	}
}
