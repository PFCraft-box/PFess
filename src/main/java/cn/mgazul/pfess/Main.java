package cn.mgazul.pfess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import cn.mgazul.pfess.chat.ChatListener;
import cn.mgazul.pfess.chat.WorldNoCommand;
import cn.mgazul.pfess.pcommand.AdminCmd;
import cn.mgazul.pfess.pcommand.CommandBack;
import cn.mgazul.pfess.pcommand.CommandDelHome;
import cn.mgazul.pfess.pcommand.CommandFCoins;
import cn.mgazul.pfess.pcommand.CommandFly;
import cn.mgazul.pfess.pcommand.CommandGameMode;
import cn.mgazul.pfess.pcommand.CommandHelp;
import cn.mgazul.pfess.pcommand.CommandHome;
import cn.mgazul.pfess.pcommand.CommandHomeList;
import cn.mgazul.pfess.pcommand.CommandMoney;
import cn.mgazul.pfess.pcommand.CommandMute;
import cn.mgazul.pfess.pcommand.CommandSethome;
import cn.mgazul.pfess.pcommand.CommandSpeed;
import cn.mgazul.pfess.pcommand.CommandTp;
import cn.mgazul.pfess.pcommand.CommandTpa;
import cn.mgazul.pfess.pcommand.CommandTpaccept;
import cn.mgazul.pfess.pcommand.CommandTpdeny;
import cn.mgazul.pfess.pcommand.CommandVanish;
import cn.mgazul.pfess.pcommand.ConsoleCmd;
import cn.mgazul.pfess.pcommand.Spawn;
import cn.mgazul.pfess.pcommand.Suicide;
import cn.mgazul.pfess.plistener.MuteListener;
import cn.mgazul.pfess.plistener.PlayerListener;
import cn.mgazul.pfess.signedit.SignClick;
import cn.mgazul.pfess.signedit.SignCommand;

public class Main extends JavaPlugin implements Listener{

	  public static Main plugin;
      public List<String> players = new ArrayList<String>();
      public List<String> warps = new ArrayList<String>();
	  public boolean enable;
	  
	  public final static Map<String, Long> tpaCooldown = new HashMap<String, Long>();
	  public final static Map<String, String> currentRequest = new HashMap<String, String>();
	  public static String prefix = "§7[§6PFess§7]";
	  public static HashMap<Player, Long> back = new HashMap<Player, Long>();

	@Override
	public void onEnable(){ 
		
		plugin = this;    
		long currentTim = System.currentTimeMillis();
	    getConfig().options().copyDefaults(true);
	    saveConfig();
	    saveDefaultConfig();
	    reloadConfig();
	    A.start(this.getDataFolder());

	    getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	    
    	Bukkit.getConsoleSender().sendMessage(prefix + " " +"§a检查运行环境中......");
        if (Bukkit.getPluginManager().getPlugin("PF-CoreLib") == null) {
        	Bukkit.getConsoleSender().sendMessage(prefix + " " +"§c未检查到§ePF-CoreLib");
        	Bukkit.getConsoleSender().sendMessage(prefix + " " +"§c请检查插件列表");
        }else {
        	Bukkit.getConsoleSender().sendMessage(prefix + " " +"§a已检查到PF-CoreLib");
        } 
	    registerEvents();
	    registerCommand();
        Bukkit.getConsoleSender().sendMessage(prefix + " " + "§a该插件已成功激活!§e(§b耗时: "+(System.currentTimeMillis() - currentTim)+"§e ms) §7[v" + getDescription().getVersion() + "]");             
    } 
	
	  private void registerEvents(){
		    PluginManager Event = Bukkit.getPluginManager();	
		    Event.registerEvents(this, this);
		    Event.registerEvents(new SignClick(), this);
		    Event.registerEvents(new WorldNoCommand(), this);
		    Event.registerEvents(new PlayerListener(), this);
		    Event.registerEvents(new ChatListener(), this);	
		  //  Event.registerEvents(new ChatFormatListener(), this);
		    getServer().getPluginManager().registerEvents(new MuteListener(), this);
	  }
	  
	  private void registerCommand(){
		    getCommand("fcoins").setExecutor(new CommandFCoins());
		    getCommand("money").setExecutor(new CommandMoney());
		    getCommand("help").setExecutor(new CommandHelp());
		    getCommand("suicide").setExecutor(new Suicide());
		    getCommand("pf").setExecutor(new AdminCmd());
		    getCommand("pf").setTabCompleter(new AdminCmd());
		    getCommand("pfc").setExecutor(new ConsoleCmd());
		    getCommand("pfc").setTabCompleter(new ConsoleCmd());
		    getCommand("sign").setExecutor(new SignCommand());
		    getCommand("setspawn").setExecutor(new Spawn());
		    getCommand("spawn").setExecutor(new Spawn());
			getCommand("tpa").setExecutor(new CommandTpa());
			getCommand("tpp").setExecutor(new CommandTp());
			getCommand("tpaccept").setExecutor(new CommandTpaccept());
			getCommand("tpdeny").setExecutor(new CommandTpdeny());
			getCommand("back").setExecutor(new CommandBack());
			getCommand("home").setExecutor(new CommandHome());
			getCommand("sethome").setExecutor(new CommandSethome());
			getCommand("delhome").setExecutor(new CommandDelHome());
			getCommand("homelist").setExecutor(new CommandHomeList());
			getCommand("gm").setExecutor(new CommandGameMode());
			getCommand("fly").setExecutor(new CommandFly());
			getCommand("vanish").setExecutor(new CommandVanish());
			getCommand("mute").setExecutor(new CommandMute());
			getCommand("speed").setExecutor(new CommandSpeed());
			getCommand("speed").setTabCompleter(new CommandSpeed());
			//getCommand("hat").setExecutor(new CommandHat());
			
	  }
	  
	  @Override
	public void onDisable() {}
}