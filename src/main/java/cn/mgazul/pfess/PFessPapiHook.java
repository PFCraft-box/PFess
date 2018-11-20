package cn.mgazul.pfess;

import cn.mgazul.pfcorelib.configuration.PlayerdataAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PFessPapiHook extends PlaceholderHook{

    private static final String hook_name = "pfess";

    public String onPlaceholderRequest(final Player p, final String i) {
        if (p == null) {
            return null;
        }
        if (i.equalsIgnoreCase("tags")) {
            YamlConfiguration Config = PlayerdataAPI.createYaml(p.getUniqueId());
            String tag = Config.getString("Tag");
            if(tag == null){
                return "";
            }
            return "&f["+tag +"&f]";
        }
        return null;
    }

    public static void hook() {
        PlaceholderAPI.registerPlaceholderHook("pfess", new PFessPapiHook());
    }

    public static void unhook() {
        PlaceholderAPI.unregisterPlaceholderHook("pfess");
    }

    public static String replacepapi(final OfflinePlayer player, final String x) {
        return PlaceholderAPI.setPlaceholders(player, x.replace("&", "§"));
    }
}
