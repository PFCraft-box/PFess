package cn.mgazul.pfess.pcommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cn.mgazul.pfcorelib.Msg;
import cn.mgazul.pfcorelib.MsgAPI;

public class CommandFly implements CommandExecutor{
	
	  @Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		  		 
		  if ((sender instanceof Player)){
			Player p = (Player)sender;
		    if ((args.length == 0) && (p.isOp())){		        
		    	if (p.getAllowFlight()){
		    		p.setFlySpeed(0.1F);
		    		p.setAllowFlight(false);
		    		MsgAPI.sendMsgToPlayer(p, Msg.preall+"&2已关闭飞行模式");
		    	}else {
		    		p.setFlySpeed(0.1F);
		    		p.setAllowFlight(true);
		    		MsgAPI.sendMsgToPlayer(p, Msg.preall+"&2已开启飞行模式");
		    	}
		    }
		    }else{
		    	sender.sendMessage(MsgAPI.colormsg(Msg.preall+"&c后台无法使用."));
			      return false;
			    }		    
	  return false;
	 }
}
