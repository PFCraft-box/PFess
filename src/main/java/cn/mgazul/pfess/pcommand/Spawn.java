package cn.mgazul.pfess.pcommand;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import cn.mgazul.pfcorelib.Msg;
import cn.mgazul.pfcorelib.MsgAPI;
import cn.mgazul.pfcorelib.configuration.ConfigUtil;
import cn.mgazul.pfess.Main;

public class Spawn implements CommandExecutor{
  
  @Override
public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
    if (cmd.getName().equalsIgnoreCase("setspawn")){
      Player p = (Player)sender;
      if (!p.hasPermission("spawn.set")) {
        p.sendMessage(Msg.Nocmd);
       } else {
    	  ConfigUtil.spawnSet(p);
    	  return true;
       }
    }
    if (cmd.getName().equalsIgnoreCase("spawn")){
    	Player p = (Player)sender;
        long delay = 0L;
        if (!p.isOp()) {
          delay = 3;
        }
        if (delay != 0L) {
        	MsgAPI.sendMsgToPlayer(p, Msg.preall + "&2请等待&6" + delay + "&2秒，不要移动.");
        }
        Location beforeLoc = p.getLocation();
        new BukkitRunnable(){
          @Override
		public void run(){
            if (p.getLocation().distance(beforeLoc) > 1.0D){
            	MsgAPI.sendMsgToPlayer(p, Msg.preall + "&c你移动了，请再试一次.");
              cancel();
            }else{
            	MsgAPI.sendMsgToPlayer(p, Msg.preall + "&2传送成功.");
            	ConfigUtil.spawn(p);
            }
          }
        }.runTaskLater(Main.plugin, delay * 20L);
      }
    return true;
  }
}
