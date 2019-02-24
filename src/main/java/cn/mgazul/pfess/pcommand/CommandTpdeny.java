package cn.mgazul.pfess.pcommand;

import cn.mgazul.pfcorelib.message.Msg;
import cn.mgazul.pfcorelib.message.MsgAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import cn.mgazul.pfess.Main;

public class CommandTpdeny implements CommandExecutor{

	  @Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
	    if ((sender instanceof Player)) {
	    	Player p = (Player)sender;
	    	if (args.length ==0){
	    		if (Main.currentRequest.containsKey(p.getName())){
	    			Player poorRejectedGuy = Bukkit.getServer().getPlayer(Main.currentRequest.get(p.getName()));
	    			Main.currentRequest.remove(p.getName());
	    			if (poorRejectedGuy != null){
	    				MsgAPI.sendMsgToPlayer(poorRejectedGuy, Msg.preall+"&6"+ p.getName() + "&2拒绝你的传送请求.");
	    				MsgAPI.sendMsgToPlayer(p, Msg.preall+"&2你拒绝了&6"+ poorRejectedGuy.getName() + "&2的请求.");
	    				return true;
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
