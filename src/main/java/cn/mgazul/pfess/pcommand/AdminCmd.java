package cn.mgazul.pfess.pcommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.mgazul.pfcorelib.message.Msg;
import cn.mgazul.pfcorelib.message.MsgAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import cn.mgazul.pfcorelib.configuration.ConfigUtil;
import cn.mgazul.pfcorelib.item.CustomItem;

public class AdminCmd implements CommandExecutor, TabCompleter{
	
	  @Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		  		 
		  if ((sender instanceof Player)){
			Player player = (Player)sender;
		    if ((args.length == 1) && (args[0].equalsIgnoreCase("help")) && (player.isOp())){		        
		    	player.sendMessage("=====玩家使用指令帮助=====");
		    	MsgAPI.sendMsgToPlayer(player, "主命令-pf");
		    	player.sendMessage(" -heal 治疗自己");
		    	player.sendMessage(" -heal <id> 治疗玩家");
		    	player.sendMessage(" -color 颜色代码");
		    	player.sendMessage(" -setwarp <坐标名> 添加坐标");
		    	player.sendMessage(" -delwarp <坐标名> 删除坐标");
		     }
		    if ((args.length == 1) && (args[0].equalsIgnoreCase("heal")) && (player.isOp())){
		  	  		AttributeInstance MaxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		  	  		Double getMaxhealth = MaxHealth.getBaseValue();
		  	  		player.setHealth(getMaxhealth);
		  	  		player.setFireTicks(0);
		  	  		player.setFoodLevel(20);
		  	  		MsgAPI.sendMsgToPlayer(player, Msg.preall+"&2你已被治疗");
		  	  		player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.3F, 0.3F);
		     }
		      if ((args.length == 2) && (args[0].equalsIgnoreCase("heal")) && (player.isOp())) {
		        if (Bukkit.getServer().getPlayer(args[1]) != null){
		            Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
		  	  		AttributeInstance MaxHealth = targetPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		  	  		Double getMaxhealth = MaxHealth.getBaseValue();
		  	  		targetPlayer.setHealth(getMaxhealth);
		            targetPlayer.setFoodLevel(20);
		            targetPlayer.setFireTicks(0);
		            MsgAPI.sendMsgToPlayer(player, Msg.preall+"&2你治疗了&6"+targetPlayer.getName()+"&2.");
		            targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.3F, 0.3F);
		            MsgAPI.sendMsgToPlayer(targetPlayer, Msg.preall+"&2你已被&6"+player.getName()+"&2奶了一口.");
		        }
		      }	 
		      
		      if ((args.length == 2) && (args[0].equalsIgnoreCase("gethead")) && (player.isOp())) {
			        for(OfflinePlayer target : Bukkit.getOfflinePlayers()) {
			        	String name = target.getName();
			        	String argsname = args[1];
			        	if(target.getName() == null ) {
			        		sender.sendMessage(Msg.preall + "§c这个玩家不存在!");
			        		return false;
			        	}
			        	if(argsname.equals(name)) {
			        		ItemStack C1 = new ItemStack(Material.PLAYER_HEAD, 1);
			        		SkullMeta sm = (SkullMeta)C1.getItemMeta();
			        		sm.setOwningPlayer(target);
			            	C1.setItemMeta((ItemMeta)sm);
			            	player.getInventory().addItem(new ItemStack[] { C1 });
			        	}
			        }
			      }	 
		      
		      if ((args.length == 2) && (args[0].equalsIgnoreCase("oe")) && (player.isOp())) {
			        if (Bukkit.getServer().getPlayer(args[1]) != null){
			            Player tPlayer = Bukkit.getServer().getPlayer(args[1]);
			            player.openInventory(tPlayer.getEnderChest());
			        }
			      }	 
		      if ((args.length == 2) && (args[0].equalsIgnoreCase("oi")) && (player.isOp())) {
			        if (Bukkit.getServer().getPlayer(args[1]) != null){
			            Player tPlayer = Bukkit.getServer().getPlayer(args[1]);
			            player.openInventory(tPlayer.getInventory());
			        }
			      }	 
		      if ((args.length == 2) && (args[0].equalsIgnoreCase("oi11")) && (player.isOp())) {
		    	  if (Bukkit.getServer().getPlayer(args[1]) != null){
		    		  Player  p= Bukkit.getServer().getPlayer(args[1]);
		    		  p.getInventory().setBoots(null);
		    	  }
		      }
		      if ((args.length == 2) && (args[0].equalsIgnoreCase("oi12")) && (player.isOp())) {
		    	  if (Bukkit.getServer().getPlayer(args[1]) != null){
		    		  Player  p= Bukkit.getServer().getPlayer(args[1]);
		    		  p.getInventory().setLeggings(null);
		    	  }
		      }
		      if ((args.length == 2) && (args[0].equalsIgnoreCase("oi13")) && (player.isOp())) {
		    	  if (Bukkit.getServer().getPlayer(args[1]) != null){
		    		  Player  p= Bukkit.getServer().getPlayer(args[1]);
		    		  p.getInventory().setChestplate(null);
		    	  }
		      }
		      if ((args.length == 2) && (args[0].equalsIgnoreCase("oi14")) && (player.isOp())) {
		    	  if (Bukkit.getServer().getPlayer(args[1]) != null){
		    		  Player  p= Bukkit.getServer().getPlayer(args[1]);
		    		  p.getInventory().setHelmet(null);
		    	  }
		      }
		      if ((args.length == 2) && (args[0].equalsIgnoreCase("oi1")) && (player.isOp())) {
			        if (Bukkit.getServer().getPlayer(args[1]) != null){
			            Player  p= Bukkit.getServer().getPlayer(args[1]);
			            Inventory i = Bukkit.createInventory(null, 9, "&c管理查水表-装备栏");
			           	      
			  	      ItemStack[] item = p.getInventory().getArmorContents();
			  	      
			          i.setItem(0, item[3]);
			          i.setItem(1, item[2]);
			          i.setItem(2, item[1]);
			          i.setItem(3, item[0]);          
			         
			          if(p.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
			        	  i.setItem(5, new CustomItem(Material.ORANGE_STAINED_GLASS_PANE,"§7主手",1));        	    
			          }else {
			        	  i.setItem(5, p.getInventory().getItemInMainHand());
			          }
			          if(p.getInventory().getItemInOffHand().getType().equals(Material.AIR)) {
			        	  i.setItem(6, new CustomItem(Material.ORANGE_STAINED_GLASS_PANE,"§7副手",1));
			          }else {
			        	  i.setItem(6, p.getInventory().getItemInOffHand());
			          }
			          if(item[3]==null) {
			        	  i.setItem(0, new CustomItem(Material.ORANGE_STAINED_GLASS_PANE,"§7帽子",1));  
			          }
			          if(item[2]==null) {
			        	  i.setItem(1, new CustomItem(Material.ORANGE_STAINED_GLASS_PANE,"§7衣服",1));  
			          }
			          if(item[1]==null) {
			        	  i.setItem(2, new CustomItem(Material.ORANGE_STAINED_GLASS_PANE,"§7裤子",1));  
			          }
			          if(item[0]==null) {
			        	  i.setItem(3, new CustomItem(Material.ORANGE_STAINED_GLASS_PANE,"§7鞋子",1));  
			          } 
					    player.openInventory(i);			     
			        }
			      }	
		    if ((args.length == 1) && (args[0].equalsIgnoreCase("color")) && (player.isOp())){
		          player.sendMessage("§4§l<---------§3[§6Color§3]§4§l--------->");
		          player.sendMessage("§00 §11 §22 §33 §44 §55 §66 §77 §88 §99 §aa §bb §cc §dd §ee §ff");
		          player.sendMessage("§mm Strikethrough§r §nn Underline§r §ll Bold");
		          player.sendMessage("k §kMagic§r o §oItalic");
		        }
		    if ((args.length == 1) && (args[0].equalsIgnoreCase("setjail")) && (player.isOp())){
			      ConfigUtil.playerSetJail(player);
		     }
		    if ((args.length == 2) && (args[0].equalsIgnoreCase("setwarp")) && (player.isOp())){
			      ConfigUtil.setWarp(args[1], player.getLocation());
			      MsgAPI.sendMsgToPlayer(player, Msg.preall+"&2坐标&6" + args[1] + "&2已经设置为你的当前位置.");
		     }
		    if ((args.length == 2) && (args[0].equalsIgnoreCase("delwarp")) && (player.isOp())){
		    	ConfigUtil.removeWarp(args[1]);
		    	MsgAPI.sendMsgToPlayer(player, Msg.preall+"&2坐标&6" + args[1] + "&2已经成功删除.");
		     }
		    if ((args.length == 1) && (args[0].equalsIgnoreCase("warps")) && (player.isOp())){
		        if (ConfigUtil.listWarps().size() == 0){
		        	MsgAPI.sendMsgToPlayer(player, "无");
		          return false;
		        }
		        for (String code : ConfigUtil.listWarps()) {
		          MsgAPI.sendMsgToPlayer(player,  code);
		        }
		        return false;
		     }	    	    
		    }else{
			      sender.sendMessage(MsgAPI.colormsg(Msg.preall+"&c后台无法使用."));
			      return false;
			    }		    
	  return false;
	 }

		private List<String> params = Arrays.asList("help", "heal", "color", "setwarp", "delwarp", "warps");

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