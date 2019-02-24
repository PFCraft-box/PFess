package cn.mgazul.pfess.pcommand;

import java.io.File;
import java.util.regex.Pattern;

import cn.mgazul.pfcorelib.configuration.PlayerdataAPI;
import cn.mgazul.pfcorelib.message.Msg;
import cn.mgazul.pfcorelib.message.MsgAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandDelHome implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		  
	    if ((sender instanceof Player)) {
	    	Player p = (Player)sender;
	    	if ((args.length == 1)){
	    		String name = args[0];
				if(Pattern.compile("[\u4E00-\u9FA5A-Za-z0-9_]+$").matcher(name).matches()){
					YamlConfiguration Config = PlayerdataAPI.createYaml(p.getUniqueId());
				  int homesize =  Config.getInt("player.playerdata.HomeSize");
				  if(Config.getString("player.playerdata.HomeSize") == null || homesize == 0) {
		    			MsgAPI.sendMsgToPlayer(p, Msg.preall+"&c没有设置传送点.");
		    			return true;
				  }
				  if(Config.getString("Home." + name)==null) {
					  MsgAPI.sendMsgToPlayer(p, Msg.preall+"&c家:&6 "+ name + " &c不存在.");
					  return true;
				  }
				  Config.set("Home." + name, null);
				  Config.set("player.playerdata.HomeSize", homesize-1);
				  PlayerdataAPI.saveYaml(p.getUniqueId(), Config);
	    		  MsgAPI.sendMsgToPlayer(p, Msg.preall + "&2成功删除家:&6 " + name);
				}else {
					MsgAPI.sendMsgToPlayer(p, Msg.preall + "&6名字仅限中文、英文、下划线.");
				}
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
