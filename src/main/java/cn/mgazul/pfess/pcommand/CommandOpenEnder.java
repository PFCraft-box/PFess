package cn.mgazul.pfess.pcommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandOpenEnder implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player)sender;
      
        player.openInventory(player.getEnderChest());
		// TODO 自动生成的方法存根
		return false;
	}

}
