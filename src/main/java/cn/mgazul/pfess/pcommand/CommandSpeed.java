package cn.mgazul.pfess.pcommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.mgazul.pfcorelib.message.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class CommandSpeed implements CommandExecutor, TabCompleter{
	
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (args.length == 1 && p.isOp()) {              
                if(this.isFloat(args[0])) {
                	if (p.isFlying()){
                		final float speed = Float.parseFloat(args[0]);
                		if (speed >= 0.0f && speed < 11.0f) {
                				p.setFlySpeed(speed / 10.0f);
                			 	p.sendMessage(Msg.preall + "飞行速度已设置为 §b" + speed);
                			}
                		}else{
                			float speed = Float.parseFloat(args[0]);
                			if (speed >= 0.0f && speed < 11.0f) {
                				p.setWalkSpeed(speed / 10.0f);
                				p.sendMessage(Msg.preall + "行走速度已设置为 §b" + speed);
                		}
                	}
                }
                if (args[0].equalsIgnoreCase("reset")) {
                	p.setFlySpeed(0.1f);
                    p.setWalkSpeed(0.2f);
                    p.sendMessage(Msg.preall + "行走和飞行速度已恢复默认.");
                }
            }
        }		
		return false;		
	}
	
    private boolean isFloat(String input) {
        try {
            Float.parseFloat(input);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
	private List<String> params = Arrays.asList("reset");

	@Override
	public List<String> onTabComplete(CommandSender sneder, Command cmd, String label, String[] args) {
		List<String> list = new ArrayList<>();
		if (args.length == 1 && sneder.isOp()) {
			for (String param : params) {
				if (param.toLowerCase().startsWith(args[0].toLowerCase()))
					list.add(param);
			}
		} 
		return list;
	}

}
