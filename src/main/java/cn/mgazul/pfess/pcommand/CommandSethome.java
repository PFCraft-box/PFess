package cn.mgazul.pfess.pcommand;

import java.util.regex.Pattern;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import cn.mgazul.pfcorelib.Msg;
import cn.mgazul.pfcorelib.MsgAPI;
import cn.mgazul.pfcorelib.configuration.ConfigUtil;
import cn.mgazul.pfcorelib.configuration.PlayerdataAPI;

public class CommandSethome implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		  
	    if ((sender instanceof Player)) {	
	    	Player player = (Player)sender;
	    	if ((args.length == 1)){
	    		
				  YamlConfiguration Config = PlayerdataAPI.createYaml(player.getUniqueId()); 
				  String name = args[0];
				  if(Pattern.compile("[\u4E00-\u9FA5A-Za-z0-9_]+$").matcher(name).matches()){
					  if(name.length()>8) {
						  MsgAPI.sendMsgToPlayer(player, Msg.preall + "&c名字长度不得大于&6 8.");
						  return true;
					  }
					  int homesize = ConfigUtil.getplayerHomesize(player);
					  int homemaxsize = ConfigUtil.getplayerHomeMaxsize(player);
					  if(Config.getString("player.playerdata.HomeMaxSize") == null || homemaxsize < 6) {
						  ConfigUtil.setplayerHomeMaxsize(player, 5);
					  }
					  if(CommandHomeList.listHomes(player).size() > homesize){
						  Config.set("player.playerdata.HomeSize", (int)CommandHomeList.listHomes(player).size());
						  PlayerdataAPI.saveYaml(player.getUniqueId(), Config);
					  }
					  if(CommandHomeList.listHomes(player).size() < homesize){
						  Config.set("player.playerdata.HomeSize", (int)CommandHomeList.listHomes(player).size());
						  PlayerdataAPI.saveYaml(player.getUniqueId(), Config);
					  }
					  if(Config.getString("player.playerdata.HomeSize")==null) {
						  ConfigUtil.setplayerHomesize(player, 0);
					  }
					  if(homesize == homemaxsize) {
						  MsgAPI.sendMsgToPlayer(player, Msg.preall + "&c家的数量已达上限:&6 " + homesize);
						  return true;
					  }
					  if(Config.getString("Home." + name)==null) {
						  ConfigUtil.setplayerHome(player, name);
						  ConfigUtil.setplayerHomesize(player, 1);
						  MsgAPI.sendMsgToPlayer(player, Msg.preall + "&6"+name +" &2设置成功.");
					  }else {
						  MsgAPI.sendMsgToPlayer(player, Msg.preall + "&c你已经有一个家为:&6 "+name); 
					  }
				  }else {
					  MsgAPI.sendMsgToPlayer(player, Msg.preall + "&6名字仅限中文、英文、下划线.");
				}
	    	} else {
	    		MsgAPI.sendMsgToPlayer(player, Msg.preall + "&7/sethome <名字> &2设置指定名字的位置");
	    	}
	    }else{
	    	sender.sendMessage(MsgAPI.colormsg(Msg.preall+"&c后台无法使用."));
	    	return true;
	    }
		return false;
	  }
}
