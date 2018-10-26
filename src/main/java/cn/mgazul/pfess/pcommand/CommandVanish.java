package cn.mgazul.pfess.pcommand;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cn.mgazul.pfcorelib.Msg;
import cn.mgazul.pfcorelib.MsgAPI;
import cn.mgazul.pfess.Main;

	public class CommandVanish implements CommandExecutor{
		
		public static ArrayList<Player> vanished = new ArrayList<Player>();
		
		  @Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
			  		 
			  if ((sender instanceof Player)){
				Player p = (Player)sender;
			    if ((args.length == 0) && (p.isOp())){		        
			        if (!CommandVanish.vanished.contains(p)){
			          for (Player pl : Bukkit.getServer().getOnlinePlayers()){
			            pl.hidePlayer(Main.plugin,p);
			          }
			          CommandVanish.vanished.add(p);
			          MsgAPI.sendMsgToPlayer(p, Msg.preall+"&2已开启隐身模式");
			          return true;
			        }
			        for (Player pl : Bukkit.getServer().getOnlinePlayers()){
			        	pl.showPlayer(Main.plugin,p);
			        }
			        CommandVanish.vanished.remove(p);
			        MsgAPI.sendMsgToPlayer(p, Msg.preall+"&2已关闭隐身模式");
			        return true;
			    }
			    if ((args.length == 1) && (p.isOp())){		
			        if (Bukkit.getServer().getPlayer(args[0]) != null){
			            Player p1 = Bukkit.getServer().getPlayer(args[0]);
			        if (!CommandVanish.vanished.contains(p1)){
			          for (Player pl : Bukkit.getServer().getOnlinePlayers()){
			            pl.hidePlayer(Main.plugin,p1);
			          }
			          CommandVanish.vanished.add(p1);
			          MsgAPI.sendMsgToPlayer(p1, Msg.preall+"&2已开启隐身模式");
			          return true;
			        }
			        for (Player pl : Bukkit.getServer().getOnlinePlayers()){
			        	pl.showPlayer(Main.plugin,p1);
			        }
			        CommandVanish.vanished.remove(p1);
			        MsgAPI.sendMsgToPlayer(p1, Msg.preall+"&2已关闭隐身模式");
			        return true;
			        }else {
			        	 MsgAPI.sendMsgToPlayer(p, Msg.preall+"&c该玩家不在线");
			        }
			      }
			    }else{
			    	sender.sendMessage(MsgAPI.colormsg(Msg.preall+"&c后台无法使用."));
				      return false;
				    }		    
		  return false;
		 }
}
