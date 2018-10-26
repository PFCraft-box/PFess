package cn.mgazul.pfess.pcommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandHat implements CommandExecutor{	  
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		  if ((sender instanceof Player)){
			  Player player = (Player)sender;
			if (args.length == 0) {		
				  ItemStack itemStack = player.getInventory().getItemInMainHand();
				 // if(player.getInventory().getHelmet() != null  && player.getInventory().getHelmet().getType() != Material.AIR) {				  
				//	  MsgAPI.sendMsgToPlayer(player, Msg.preall+ "&c你已经有帽子了");
				 // }
				 // if(itemStack.getType() != Material.AIR) {
				//	  MsgAPI.sendMsgToPlayer(player, Msg.preall+ "&c你手上没有任何物品");
				 // }
				 player.getInventory().setHelmet(itemStack);	
				 player.getInventory().getItemInMainHand().clone();
	    	   }
		  }else{
		      sender.sendMessage("§c?");
		      return true;
		    }	
	    return false;
	  }
}
