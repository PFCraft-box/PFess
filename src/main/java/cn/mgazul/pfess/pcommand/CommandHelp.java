package cn.mgazul.pfess.pcommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHelp implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
	if (cmd.getName().equals("help"))
    {
      if ((sender instanceof Player))
      {
        Player player = (Player)sender;
        {
          player.sendMessage("§c§l=================《就是江湖》=================");
          player.sendMessage( "§2§l所有信息请查阅菜单内的说明或者对应的NPC");
          player.sendMessage( "§2§l看着天上按下蹲键或者直接按F键打开菜单");
          player.sendMessage("§c§l===============================================");
        }
      }
      else
      {
    	  sender.sendMessage("§c§l命令仅在游戏中使用.");
      }
    }
      return true;
    }
}