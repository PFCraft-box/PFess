package cn.mgazul.pfess.pcommand;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import cn.mgazul.pfcorelib.Msg;
import cn.mgazul.pfcorelib.MsgAPI;
import cn.mgazul.pfcorelib.configuration.PlayerdataAPI;

public class CommandHomeList implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		  
	    if ((sender instanceof Player)) {
	    	Player p = (Player)sender;
	    	if ((args.length == 0)){
	    		 String message = "§7你拥有以下家: ";
                for (final String home : listHomes(p)) {
                    message = String.valueOf(message) + "&6"+home + "&7, ";
                }
                message = String.valueOf(message.substring(0, message.length() - 2)) + ".";
                MsgAPI.sendMsgToPlayer(p, Msg.preall + message);
	    	} else {
	    		MsgAPI.sendMsgToPlayer(p, Msg.preall + "&7/homelist &2显示你所有的家");
	    	}
	    }else{
	    	sender.sendMessage(MsgAPI.colormsg(Msg.preall+"&c后台无法使用."));
	    	return true;
	    }
			return false;
	  }
	
    public static ArrayList<String> listHomes(Player p) {
    	YamlConfiguration Config = PlayerdataAPI.createYaml(p.getUniqueId()); 
        final ArrayList<String> list = new ArrayList<String>();
		if (Config.getConfigurationSection("Home.") != null) {
			for (final String key : Config.getConfigurationSection("Home").getKeys(false)) {
				list.add(key);
			}
		}
        return list;
    }

	public static ArrayList<String> listHomess(Player p) {
		YamlConfiguration Config = PlayerdataAPI.createYaml(p.getUniqueId());
		final ArrayList<String> list = new ArrayList<String>();
		if (Config.getConfigurationSection("Home.") != null) {
			for (final String key : Config.getConfigurationSection("Home").getKeys(false)) {
				list.add(key);
			}
		}
		return list;
	}
}
