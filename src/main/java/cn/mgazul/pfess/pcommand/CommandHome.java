package cn.mgazul.pfess.pcommand;

import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import cn.mgazul.pfcorelib.configuration.ConfigUtil;
import cn.mgazul.pfcorelib.util.Java;
import cn.mgazul.pfcorelib.MoneyAPI;
import cn.mgazul.pfcorelib.Msg;
import cn.mgazul.pfcorelib.MsgAPI;

public class CommandHome implements CommandExecutor{

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
	    		 ConfigUtil.getplayerHome(p, name);
	    		  MsgAPI.sendMsgToPlayer(p, Msg.preall + " &2成功传送至家:&6 " + name);
	    	}
	    	if ((args.length == 0)){
	    		MsgAPI.sendMsgToPlayer(p, Msg.preall + "&6当前最大设家数量为: &c"+ConfigUtil.getplayerHomeMaxsize(p)+" &6已设家数量:&2 "+ ConfigUtil.getplayerHomesize(p));
	    		MsgAPI.sendMsgToPlayer(p, Msg.preall + "&7/home <名字> &2传送到指定位置");
	    		MsgAPI.sendMsgToPlayer(p, Msg.preall + "&7/sethome <名字> &2设置指定名字的位置");
	    		MsgAPI.sendMsgToPlayer(p, Msg.preall + "&7/delhome <名字> &2删除指定家");
	    		MsgAPI.sendMsgToPlayer(p, Msg.preall + "&7/homelist &2显示你所有的家");
	    		MsgAPI.sendMsgToPlayer(p, Msg.preall + "&7/home buy <数量> &2购买上限,单价: 10000");
	    	}
	    	if ((args.length == 2) && (args[0].equalsIgnoreCase("buy"))){
	        	 String coins = args[1];
	        	 Double homes = Double.parseDouble(coins);
			     if(Java.isNumeric(coins)) {
			    	 if(MoneyAPI.getMoneys(p.getUniqueId())>= 10000.0*homes) {	        	
		    			ConfigUtil.setplayerHomeMaxsize(p, homes);
		    			MoneyAPI.removeMoneys(p.getUniqueId(), 10000.0*homes);
		    			MsgAPI.sendMsgToPlayer(p, Msg.preall + "&6设家上限已增加: &21,&6当前最大设家数量为: &c"+ConfigUtil.getplayerHomeMaxsize(p));      
			    	 } else {
	    			MsgAPI.sendMsgToPlayer(p, Msg.preall + "&c余额不足,需要铜钱:&6 " + 10000.0*homes);
	    		   }
			    }else {
			    sender.sendMessage("无效数字");
			 }
	    	}
	    }else{
	    	sender.sendMessage(MsgAPI.colormsg(Msg.preall+"&c后台无法使用."));
	    	return true;
	    }
			return false;
	  }
}
