package cn.mgazul.pfess.signedit;

import java.util.HashMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SignCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
	    if ((cmd.getName().equalsIgnoreCase("sign")) && ((sender instanceof Player))) {
	    	Player p = (Player)sender;
		      if (sender.hasPermission("sign.set")){	    	  		    	 
			p.sendMessage("/sign set <行数> <文本>  §c可以行数: 0, 1, 2, 3");			
		}		
		if(args.length >= 2){
			String txt = "";	
			HashMap<Integer, String> cur = new HashMap<>();		
			int line = Integer.valueOf(args[1]);
		if(line > 3){
			
			p.sendMessage("§c可以行数: 0, 1, 2, 3");
			return true;
		}
		 for(int i = 2; i < args.length; i++) txt = txt + args[i].replace("&", "§") + " ";{
				 cur.put(line, txt);
				 SignClick.sign.put(p, cur);
				 p.sendMessage("然后右键单击牌子来设置.");
			}		 
		}
	}
		return false;
	}
}

