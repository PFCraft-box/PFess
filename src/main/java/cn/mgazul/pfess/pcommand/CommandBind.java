package cn.mgazul.pfess.pcommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import cn.mgazul.pfcorelib.Msg;
import cn.mgazul.pfcorelib.MsgAPI;

public class CommandBind implements CommandExecutor{
	
	  @Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		  		 
		  if ((sender instanceof Player)){
			Player p = (Player)sender;
				p.getInventory().getItemInOffHand().addEnchantment(Enchantment.BINDING_CURSE, 1);
		    }else{
		    	sender.sendMessage(MsgAPI.colormsg(Msg.preall+"&c后台无法使用."));
			      return false;
			    }		    
	  return false;
	 } 
}