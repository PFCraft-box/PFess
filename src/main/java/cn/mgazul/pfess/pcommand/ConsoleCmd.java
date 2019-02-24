package cn.mgazul.pfess.pcommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cn.mgazul.pfcorelib.message.Msg;
import cn.mgazul.pfcorelib.message.MsgAPI;
import cn.mgazul.pfess.tags.Gui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import cn.mgazul.pfcorelib.configuration.ConfigUtil;
import cn.mgazul.pfcorelib.world.WorldWrapper;
import cn.mgazul.pfess.Main;
import cn.mgazul.pfess.StatsUtils;

public class ConsoleCmd implements CommandExecutor, TabCompleter{
		  
	@SuppressWarnings("deprecation")
	public static List<Material> BAND_MATERIALES = Arrays.asList(new Material[] { 
			    Material.LEGACY_STATIONARY_WATER, 
			    Material.LEGACY_STATIONARY_LAVA, 
			    Material.WATER, 
			    Material.LAVA });
	
	private List<String> params = Arrays.asList("showp", "bcc", "reload", "tpr", "tpw", "winfo", "warp");

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
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	    if ((args.length == 1) && (args[0].equalsIgnoreCase("help")) && (sender.isOp())){		        
	    	sender.sendMessage("=====后台使用指令帮助=====");
	    	sender.sendMessage(" 主命令-pfc");
	    	sender.sendMessage(" -showp <id> 查看玩家信息");
	    	sender.sendMessage(" -bcc <信息> 公告");
	    	sender.sendMessage(" -reload 重载配置文件");
	    	sender.sendMessage(" -tpr <id> <世界> 随机传送玩家到指定世界");
	    	sender.sendMessage(" -tpw <世界名> 传送到指定世界的出生点");
	    	sender.sendMessage(" -winfo <世界名> 查看该世界的信息");
	    	sender.sendMessage(" -warp <id> <坐标名> 查看玩家信息");
			sender.sendMessage(" -tags <id> <称号名字> <获取途径> <称号介绍> 给玩家添加称号");
	     }
	    if ((args.length == 3) && (args[0].equalsIgnoreCase("set")) && (sender.isOp())){	
	    	if(args[1].equalsIgnoreCase("perfix")) {
                Main.plugin.getConfig().set("perifx", args[2]);
                sender.sendMessage("设置成功");
                Main.plugin.saveConfig();
                Main.plugin.reloadConfig();
	    	}	    	
	    }
	    if ((args.length == 1) && (args[0].equalsIgnoreCase("windows")) && (sender.isOp())){		        
	        int playerAmount = Bukkit.getOnlinePlayers().size();
	        int maxplayerAmount = Bukkit.getOfflinePlayers().length;
	        boolean onlineMode = Bukkit.getOnlineMode();
	        String bukkitVersion = Bukkit.getVersion();
	        bukkitVersion = bukkitVersion.substring(bukkitVersion.indexOf("MC: ") + 4, bukkitVersion.length() - 1);
	        
	        String javaVersion = System.getProperty("java.version");
	        String osName = System.getProperty("os.name");
	        String osArch = System.getProperty("os.arch");
	        String osVersion = System.getProperty("os.version");
	        	            
	        sender.sendMessage("服务器版本: "+ bukkitVersion);
	        sender.sendMessage("在线人数: "+ Integer.valueOf(playerAmount));
	        sender.sendMessage("离线在线总人数: "+ Integer.valueOf(maxplayerAmount));
	        sender.sendMessage("正版验证: "+Boolean.valueOf(onlineMode));
	        
	        sender.sendMessage("Java版本: "+javaVersion);
	        sender.sendMessage("§8§lMax Java§r§8: §7" + StatsUtils.BytesToMegaBytes(StatsUtils.maxMemory()) + "§8MB");
	        sender.sendMessage("§7§l总§r§8: §7" + StatsUtils.BytesToMegaBytes(StatsUtils.totalMemory()) + "§8MB");
	        sender.sendMessage("§a§l空闲§r§8: §7" + StatsUtils.BytesToMegaBytes(StatsUtils.freeMemory()) + "§8MB");
	        
	        
	        try {
	        	sender.sendMessage("§8§l平均负载§r§8: §7" + StatsUtils.LoadAverange() + "§8%");
				sender.sendMessage("§7§l处理器负载§r§8: §7" + StatsUtils.getProcessCpuLoad() + "§8%");
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	        sender.sendMessage("§7§l总磁盘§r§8: §7" + StatsUtils.BytesToGigaBytes(StatsUtils.totalDisk()) + "§8GB");
	        sender.sendMessage("§8§l可用磁盘§r§8: §7" + StatsUtils.BytesToGigaBytes(StatsUtils.usableDisk()) + "§8GB");
	        sender.sendMessage("§a§l空闲磁盘§r§8: §7" + StatsUtils.BytesToGigaBytes(StatsUtils.freeDisk()) + "§8GB");
	        sender.sendMessage("OS: "+osName);
	        sender.sendMessage("OS-Version: "+ osVersion);
	        sender.sendMessage("OS-Arch: "+osArch);
	     }
		if ((args.length == 5 ) && (args[0].equalsIgnoreCase("tags")) && (sender.hasPermission("pfess.admin"))){
			if (Bukkit.getPlayer(args[1]) != null) {
				Player p2 = Bukkit.getPlayer(args[1]);
				Gui.setTags(p2, args[2], args[3], args[4]);
			}else{
				sender.sendMessage(ChatColor.GRAY + "玩家不在线.");
			}
		}
	    if ((args.length == 2 ) && (args[0].equalsIgnoreCase("showp")) && (sender.hasPermission("pfess.admin"))){
	        if (Bukkit.getPlayer(args[1]) != null){
	      	    Player p2 = Bukkit.getPlayer(args[1]);
	  	  		AttributeInstance MaxHealth = p2.getAttribute(Attribute.GENERIC_MAX_HEALTH);
	  	  		double getMaxHealth = MaxHealth.getBaseValue();
	      	    sender.sendMessage(Msg.preall+"§8:" + "§8-----=====§7[§4查询的玩家§2-§e" + p2.getName() + "§7]§8=====-----");
	      	  	sender.sendMessage("§7§l别称>§8§l"+p2.getDisplayName());
	      	  	sender.sendMessage("§7§lUUID>§8§l"+p2.getUniqueId().toString());
	      	  	sender.sendMessage("§7§l血量>§8§l"+p2.getHealth()+"§7§l/§8§l"+getMaxHealth);
	          	sender.sendMessage("§7§l饥饿度>§8§l"+p2.getFoodLevel());
	          	sender.sendMessage("§7§lEXP经验>§8§l"+p2.getLevel());
	          	sender.sendMessage("§7§l当前位置>"+"§3§l世界:§8§l"+p2.getWorld().getName()+"§3§lX:§8§l"+p2.getLocation().getBlockX()+"§3§lY:§8§l"+p2.getLocation().getBlockY()+"§3§lZ:§8§l"+p2.getLocation().getBlockZ());
	          	sender.sendMessage("§7§l游戏模式>§8§l"+p2.getGameMode());   
	          	sender.sendMessage("§7§l飞行速度>§8§l"+p2.getFlySpeed()); 
	          	sender.sendMessage("§7§l行走速度>§8§l"+p2.getWalkSpeed());
	          	sender.sendMessage("§7§l飞行模式>§8§l"+p2.getAllowFlight());
	          	sender.sendMessage("§7§l飞行中>§8§l"+p2.isFlying());
	          	sender.sendMessage("§7§lOP>§8§l"+p2.isOp());
	        }else{
	        	sender.sendMessage(ChatColor.GRAY + "玩家不在线.");
		        }
	     }
	    if ((args.length == 2) && (args[0].equalsIgnoreCase("bcc")) && (sender.hasPermission("pfess.admin"))){	
	        StringBuilder announcementBuilder = new StringBuilder(args[1]);
	        for (int i = 0; i < args.length; i++) {
	          announcementBuilder.append(args[i]).append(" ");
	        }
	        String message = ChatColor.translateAlternateColorCodes('&', announcementBuilder.toString().trim());
			 Bukkit.broadcastMessage("§6§l[江湖公告]§4§l " + message);
	     }
	    if ((args.length == 1) && (args[0].equalsIgnoreCase("reload")) && (sender.hasPermission("pfess.admin"))){		        
	    	Main.plugin.reloadConfig();
	        Main.plugin.saveConfig();
	        sender.sendMessage(Msg.reload);
	     }
	    if ((args.length == 3) && (args[0].equalsIgnoreCase("tpr")) && (sender.hasPermission("pfess.admin"))){		        
	    	Player p = Bukkit.getServer().getPlayer(args[1]);
	    	int minBound = -300000;
		    int maxBound = 300000;
		    int a = maxBound - minBound;		    
		    int newX = (int) (Math.random() * a + minBound);
		    int newZ = (int) (Math.random() * a + minBound);
		    String worldname= args[2];
		    int newY = Bukkit.getWorld(worldname).getHighestBlockYAt(newX, newZ);
		    Location checkLoc = new Location(Bukkit.getWorld(worldname), newX, newY - 1, newZ);
		    Location loc = new Location(Bukkit.getWorld(worldname), newX, newY + 1, newZ);
		    Block block = Bukkit.getWorld(worldname).getBlockAt(checkLoc);
		    if (BAND_MATERIALES.contains(block.getType())) {
		    	block.setType(Material.STONE);
		    }
		    loc.setY(newY);
	        long delay = 0L;
	        if (!p.isOp()) {
	          delay = 3;
	        }
	        if (delay != 0L) {
	          p.sendMessage("§2请等待" + delay + "秒，不要移动");
	        }
	        Location beforeLoc = p.getLocation();
	        new BukkitRunnable()
	        {
	          @Override
			public void run()
	          {
	            if (p.getLocation().distance(beforeLoc) > 1.0D)
	            {
	              p.sendMessage("§6你移动了，请再试一次");
	              cancel();
	            }else{
	            	p.teleport(loc);
	            }
	          }
	        }.runTaskLater(Main.plugin, delay * 20L);
		  }    
	
	    if ((args.length == 3) && (args[0].equalsIgnoreCase("tpw")) && (sender.hasPermission("pfess.admin"))){		        
	    	Player p = Bukkit.getServer().getPlayer(args[1]);    	
		    String worldname= args[2];
		    World isworld = Bukkit.getWorld(worldname);
		    if(isworld==null) {return true;}
			Location world = Bukkit.getWorld(worldname).getSpawnLocation();
	        long delay = 0L;
	        if (!p.isOp()) {
	          delay = 3;
	        }
	        if (delay != 0L) {
	          p.sendMessage("§2请等待" + delay + "秒，不要移动");
	        }
	        Location beforeLoc = p.getLocation();
	        new BukkitRunnable()
	        {
	          @Override
			public void run()
	          {
	            if (p.getLocation().distance(beforeLoc) > 1.0D)
	            {
	              p.sendMessage("§6你移动了，请再试一次");
	              cancel();
	            }else{
	            	p.teleport(world);
	            }
	          }
	        }.runTaskLater(Main.plugin, delay * 20L);
	     }
	    if ((args.length == 2) && (args[0].equalsIgnoreCase("winfo")) && (sender.hasPermission("pfess.admin"))){		        
	    	String worldname= args[1];
			World world = Bukkit.getWorld(worldname);
		      for (String line : WorldWrapper.getWorldInfo(world)) {
		          sender.sendMessage(line);
		        }
	     }
	    if ((args.length == 0)&&(sender.hasPermission("pfess.admin"))) {
	    	sender.sendMessage("§a管理指令");			
		}
	    if ((args.length == 3) && (args[0].equalsIgnoreCase("warp")) && (sender.hasPermission("pfess.admin"))){		        
	    	Player p = Bukkit.getServer().getPlayer(args[1]);    
			ConfigUtil.teleportWarp(p, args[2]);
	     }
	    if ((args.length == 2) && (args[0].equalsIgnoreCase("entitylist")) && (sender.hasPermission("pfess.admin"))){	
	      if (args.length > 0)
	      {
	        World world = Bukkit.getWorld(args[1].toString());
	        if (world != null)
	        {
	          HashMap<EntityType, Integer> entities = new HashMap<EntityType, Integer>();
	          for (Entity en : world.getEntities()) {
	            if (entities.containsKey(en.getType())) {
	              entities.put(en.getType(), Integer.valueOf(entities.get(en.getType()).intValue() + 1));
	            } else {
	              entities.put(en.getType(), Integer.valueOf(1));
	            }
	          }
	          for (EntityType entype : entities.keySet()) {
	            sender.sendMessage(entype.name() + ": " + entities.get(entype));
	          }
	        }else{
	          sender.sendMessage(ChatColor.RED + "世界不存在");
	        }
	      }else{
	        sender.sendMessage(ChatColor.RED + "使用方法: /EntityList {World Name}");
	      }
	      return true;
	    }
	    if ((args.length == 2) && (args[0].equalsIgnoreCase("cleare")) && (sender.hasPermission("pfess.admin"))){	
	    	Player p = Bukkit.getServer().getPlayer(args[1]);    
	        ItemStack item = p.getInventory().getItemInMainHand();
	        if (item.getEnchantments().size() < 1){
	        	sender.sendMessage(ChatColor.RED + "此物品没有附魔.");
	          return true;
	        }
	        if(item.getItemMeta().getEnchants() != Enchantment.BINDING_CURSE) {
	           
	          
	          
	          
	         // item.removeEnchantment(Enchantment.VANISHING_CURSE);/*消失诅咒*/
	         // item.removeEnchantment(Enchantment.SWEEPING_EDGE);/*横扫之刃*/
	         // item.removeEnchantment(Enchantment.FROST_WALKER); /*冰霜行者*/
	         // item.removeEnchantment(Enchantment.ARROW_FIRE);   /*火焰附加(弓)*/
	         // item.removeEnchantment(Enchantment.ARROW_INFINITE);/*无限*/
	         // item.removeEnchantment(Enchantment.ARROW_KNOCKBACK);/*击退*/
	         // item.removeEnchantment(Enchantment.DAMAGE_ALL);/*锋利*/
	         // item.removeEnchantment(Enchantment.DAMAGE_ARTHROPODS);/*节肢杀手*/
	         // item.removeEnchantment(Enchantment.DAMAGE_UNDEAD);/*亡灵杀手*/
	         // item.removeEnchantment(Enchantment.DIG_SPEED);/*效率*/
	         // item.removeEnchantment(Enchantment.DURABILITY);/*耐久*/
	         // item.removeEnchantment(Enchantment.FIRE_ASPECT);/*火焰附加*/
	         // item.removeEnchantment(Enchantment.KNOCKBACK);/*击退*/
	         // item.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);/*时运*/
	         // item.removeEnchantment(Enchantment.LOOT_BONUS_MOBS);/*抢夺*/
	         // item.removeEnchantment(Enchantment.OXYGEN);/*水肺*/
	         // item.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);/*保护*/
	         // item.removeEnchantment(Enchantment.PROTECTION_EXPLOSIONS);/*爆炸保护*/
	         // item.removeEnchantment(Enchantment.PROTECTION_FALL);/*摔落保护*/
	        //  item.removeEnchantment(Enchantment.PROTECTION_FIRE);/*火焰保护*/
	         // item.removeEnchantment(Enchantment.PROTECTION_PROJECTILE);/*弹射保护*/
	         // item.removeEnchantment(Enchantment.SILK_TOUCH);/*精准采集*/
	         // item.removeEnchantment(Enchantment.WATER_WORKER);/*水下速掘*/
	         // item.removeEnchantment(Enchantment.THORNS);/*荆棘*/
	        //  item.removeEnchantment(Enchantment.LURE);/*饵钓*/
	        //  item.removeEnchantment(Enchantment.LUCK);/*海之眷顾*/
	        //  item.removeEnchantment(Enchantment.DEPTH_STRIDER);/*深海探索者*/
	         // item.removeEnchantment(Enchantment.FROST_WALKER);/*冰霜行者*/
	         // item.removeEnchantment(Enchantment.MENDING);/*经验修补*/
	        	 item.removeEnchantment(Enchantment.BINDING_CURSE);/*绑定诅咒*/
	        	sender.sendMessage(ChatColor.GREEN + "成功洗魔.");
	        }
	      }
	      if ((args.length == 3) && (args[0].equalsIgnoreCase("pvp")) && (sender.hasPermission("pfess.admin"))) {
	    	  if (args[1].equalsIgnoreCase("on")){
	    		  World w = Bukkit.getWorld(args[2]);
	    		  w.setPVP(true);
	    		  sender.sendMessage("on");
	    	  }
	    	  if (args[1].equalsIgnoreCase("off")){
	    		  World w = Bukkit.getWorld(args[2]);
	    		  w.setPVP(false);
	    		  sender.sendMessage("off");
	    	  }
		    }	
	      if ((args.length == 3) && (args[0].equalsIgnoreCase("difficulty")) && (sender.hasPermission("pfess.admin"))) {
	    	  if (args[1].equalsIgnoreCase("0")){
	    		  World w = Bukkit.getWorld(args[2]);
	    		  w.setDifficulty(Difficulty.PEACEFUL);
	    		  w.setMonsterSpawnLimit(0);
	    		  sender.sendMessage("0");
	    	  }
	    	  if (args[1].equalsIgnoreCase("1")){
	    		  World w = Bukkit.getWorld(args[2]);
	    		  w.setDifficulty(Difficulty.EASY);
	    		  sender.sendMessage("1");
	    	  }
	    	  if (args[1].equalsIgnoreCase("2")){
	    		  World w = Bukkit.getWorld(args[2]);
	    		  w.setDifficulty(Difficulty.NORMAL);
	    		  sender.sendMessage("2");
	    	  }
	    	  if (args[1].equalsIgnoreCase("3")){
	    		  World w = Bukkit.getWorld(args[2]);
	    		  w.setDifficulty(Difficulty.HARD);
	    		  sender.sendMessage("3");
	    	  }
		    }
	      if ((args.length == 1) && (args[0].equalsIgnoreCase("gc")) && (sender.isOp())) {
	  		long memoryBeforeGC = getCurrentMemoryUsage();
			System.runFinalization();
			System.gc();
			long memoryAfterGC = getCurrentMemoryUsage();
			if (memoryAfterGC < memoryBeforeGC) {
				long gcMemory = (memoryAfterGC - memoryBeforeGC) / 1024 / 1024;
				sender.sendMessage(Msg.preall+ MsgAPI.colormsg("&2回收了&6&n "+Math.abs(gcMemory)+"MB &2内存"));
			} else {
				sender.sendMessage(Msg.preall+MsgAPI.colormsg("&c垃圾回收操作执行无效果"));
			}
		    }	
		return false;	
	}
	
	protected long getCurrentMemoryUsage() {
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}
}
