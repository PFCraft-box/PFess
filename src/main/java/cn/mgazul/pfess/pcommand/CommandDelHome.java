package cn.mgazul.pfess.pcommand;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import cn.mgazul.pfcorelib.Msg;
import cn.mgazul.pfcorelib.MsgAPI;
import cn.mgazul.pfcorelib.configuration.ConfigUtil;

public class CommandDelHome implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		  
	    if ((sender instanceof Player)) {
	    	Player p = (Player)sender;
	    	if ((args.length == 1)){
	    		String name = args[0];
	    		String uuid = p.getUniqueId().toString();	    
	    		File file = new File("plugins/"+Msg.PluginName+"/Players", uuid.toString()+".yml");	
	    		FileConfiguration Config = YamlConfiguration.loadConfiguration(file);
				  int homesize =  Config.getInt("player.playerdata.HomeSize");
				  if(Config.getString("player.playerdata.HomeSize")==null||homesize == 0) {
		    			MsgAPI.sendMsgToPlayer(p, Msg.preall+"&c没有设置传送点.");
		    			return true;
				  }
				  if(Config.getString("Home." + name)==null) {
					  MsgAPI.sendMsgToPlayer(p, Msg.preall+"&c家:&6 "+ name + " &c不存在.");
					  return true;
				  }
				  Config.set("Home." + name, null);
				  ConfigUtil.fileSave(Config, file);
				  ConfigUtil.removeHomesize(p, 1.0);
	    		  MsgAPI.sendMsgToPlayer(p, Msg.preall + "&2成功删除家:&6 " + name);
	    	} else {
	    		MsgAPI.sendMsgToPlayer(p, Msg.preall + " &7/delhome <名字> &2删除指定家");
	    	}
	    }else{
	    	sender.sendMessage(MsgAPI.colormsg(Msg.preall+"&c后台无法使用."));
	    	return true;
	    }
			return false;
	  }
}
