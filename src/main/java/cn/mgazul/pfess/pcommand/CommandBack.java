package cn.mgazul.pfess.pcommand;

import java.io.File;

import cn.mgazul.pfcorelib.message.Msg;
import cn.mgazul.pfcorelib.message.MsgAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import cn.mgazul.pfcorelib.configuration.ConfigUtil;
import cn.mgazul.pfcorelib.nms.TitleAPI;
import cn.mgazul.pfess.Main;

public class CommandBack implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		  
	    if ((sender instanceof Player)) {
	    	Player p = (Player)sender;
	    	if ((args.length == 0)){
	    		String uuid = p.getUniqueId().toString();	    
	    		File file = new File("plugins/"+ Msg.PluginName+"/Players", uuid.toString()+".yml");
	    		FileConfiguration Config = YamlConfiguration.loadConfiguration(file);
	    		if(Config.getString("DeathLoc.world")==null) {
	    			MsgAPI.sendMsgToPlayer(p, Msg.preall + "&c没有记录点.");
	    			return true;
	    		}	
	  	        if((Main.back.get(p) != null) && ((Main.back.get(p)).longValue() > System.currentTimeMillis())) {
		          int time = (int)(((Main.back.get(p)).longValue() - System.currentTimeMillis()) / 1000L);
		          TitleAPI.sendPlayerAbar(p, "§c传送正在冷却中§e " + time);
		          return true;
		        }	      	
	    		ConfigUtil.getplayerDeathLocation(p);
	    		Main.back.put(p, System.currentTimeMillis() + 1*60000L);
				TitleAPI.sendPlayerAbar(p, "§6§l传送完成");	    		
	    		return true;
	    	} 
	    }else{
	    	sender.sendMessage(MsgAPI.colormsg(Msg.preall+"&c后台无法使用."));
	    	return false;
	    }
		return false;
	  } 
}
