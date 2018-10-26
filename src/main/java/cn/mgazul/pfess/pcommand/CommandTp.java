package cn.mgazul.pfess.pcommand;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cn.mgazul.pfcorelib.util.Java;

public class CommandTp implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	
	  if ((sender instanceof Player)){
		Player p = (Player)sender;
	    if ((args.length == 1)  && (p.isOp())){		        
	        if (Bukkit.getPlayer(args[0]) != null){
	      	    Player p2 = Bukkit.getPlayer(args[0]);
	      	    p.teleport(p2.getLocation());
	        }
	     }
	    if ((args.length == 2) && (p.isOp())){
	        if (Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[1]) != null ){
	      	    Player p2 = Bukkit.getPlayer(args[0]);
	      	    Player p3 = Bukkit.getPlayer(args[1]);
	      	    p2.teleport(p3.getLocation());
	        }
	     }
	    if ((args.length == 3) && (p.isOp())){
	        if (Java.isNumeric(args[0]) && Java.isNumeric(args[1]) && Java.isNumeric(args[2]) ){
	        	String x = args[0];
	        	String y = args[1];
	        	String z = args[2];
	      	    p.teleport(new Location(p.getWorld(), Double.valueOf(x), Double.valueOf(y), Double.valueOf(z)));
	        }
	     }
	    if ((args.length == 5) && (p.isOp())){
	        if (Java.isNumeric(args[0]) && Java.isNumeric(args[1]) && Java.isNumeric(args[2]) && Java.isNumeric(args[3]) && Java.isNumeric(args[4]) ){
	        	String x = args[0];
	        	String y = args[1];
	        	String z = args[2];
	        	String yaw = args[3];
	        	String pitch = args[4];
	      	    p.teleport(new Location(p.getWorld(),  Double.valueOf(x), Double.valueOf(y), Double.valueOf(z), Float.parseFloat(yaw), Float.parseFloat(pitch)));
	        }
	     }
	    if ((args.length == 4) && (p.isOp())){
	        if (Java.isNumeric(args[1]) && Java.isNumeric(args[2]) && Java.isNumeric(args[3]) ){
	        	String x = args[1];
	        	String y = args[2];
	        	String z = args[3];
	        	String world = args[0];
	      	    p.teleport(new Location(Bukkit.getWorld(world), Double.valueOf(x), Double.valueOf(y), Double.valueOf(z)));
	        }
	     }
	    if ((args.length == 6) && (p.isOp())){
	        if (Java.isNumeric(args[1]) && Java.isNumeric(args[2]) && Java.isNumeric(args[3]) && Java.isNumeric(args[4]) && Java.isNumeric(args[5]) ){
	        	String x = args[1];
	        	String y = args[2];
	        	String z = args[3];
	        	String yaw = args[4];
	        	String pitch = args[5];
	        	String world = args[0];
	      	    p.teleport(new Location(Bukkit.getWorld(world),  Double.valueOf(x), Double.valueOf(y), Double.valueOf(z), Float.parseFloat(yaw), Float.parseFloat(pitch)));
	        }
	     }
	  }
	return false;
	}
}
