package cn.mgazul.pfess.signedit;

import java.util.HashMap;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignClick implements Listener{
	
	public static HashMap<Player, HashMap<Integer, String>> sign = new HashMap<>();
		
	@EventHandler
	public void onInt(PlayerInteractEvent e){		
		if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;		
		if(!(e.getClickedBlock().getState() instanceof Sign)) return;
		Sign s = (Sign) e.getClickedBlock().getState();	
		if(sign.containsKey(e.getPlayer())){
			HashMap<Integer, String> cur = sign.get(e.getPlayer());
			for(int i : cur.keySet()){
				StringBuilder sb = new StringBuilder(cur.get(i));
				sb = sb.deleteCharAt(sb.length() - 1);
				
			s.setLine(i, sb.toString());
			e.getPlayer().sendMessage("§c行数 §e" + i + " §c的内容变为: §r" + cur.get(i));
			s.update();
			cur.clear();
			sign.remove(e.getPlayer());
			break;
			}
		}
	}
}
