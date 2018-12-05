package cn.mgazul.pfess.pcommand;

import cn.mgazul.pfess.tags.Gui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTags implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if ((sender instanceof Player)) {
            Player player = (Player) sender;
            if (args.length == 0) {
                Gui.openMenuGui(player);
            }
        }
        return false;
    }
}
