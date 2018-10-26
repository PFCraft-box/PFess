package cn.mgazul.pfess.pcommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Suicide implements CommandExecutor{	  

	  @Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		  if ((sender instanceof Player)){
			  Player player = (Player)sender;
			  if ((cmd.getName().equalsIgnoreCase("suicide")) && ((sender instanceof Player))) {		 
				  player.setHealth(0.0D);
	    	}
		  }else{
		      sender.sendMessage("§c?");
		      return false;
		    }	
	    return true;
	  }
}