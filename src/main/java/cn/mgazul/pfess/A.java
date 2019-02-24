package cn.mgazul.pfess;

import cn.mgazul.pfcorelib.message.MsgAPI;
import org.bukkit.*;
import org.bukkit.configuration.file.*;
import java.io.*;
import java.util.*;

import org.bukkit.command.*;
import org.bukkit.entity.*;

public class A
{
    public static String noPermMsg;
    public static String head;
    public static Integer msgsendtime;
    public static List<String> messages;
    public static Integer messager;
    public static File file;
    public static FileConfiguration filec;
    public static Boolean random;
    public static Integer i;
    
    static {
        A.msgsendtime = 120;
        A.messages = new ArrayList<String>();
        A.messager = -1;
        A.i = 0;
    }
    
    public static void start(final File filed) {
        A.file = new File(filed + "/config.yml");
        if (!A.file.exists()) {
            Main.plugin.saveDefaultConfig();
        }
        A.filec = YamlConfiguration.loadConfiguration(A.file);
        A.head = A.filec.getString("Settings.head");
        A.noPermMsg = A.filec.getString("Settings.noPermMes");
        A.msgsendtime = A.filec.getInt("Settings.interval");
        A.random = A.filec.getBoolean("Settings.random");
        A.messages = A.filec.getStringList("Messages.text");
        if (A.messager != -1) {
            Bukkit.getScheduler().cancelTask(A.messager);
        }
        sendMessages();
        try {
            A.filec.save(A.file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String[] splitString(final String str) {
        if (str.indexOf("/n") == -1) {
            return new String[] { str };
        }
        final String[] strArray = str.split("/n");
        return strArray;
    }
    
    public static void sendMessages() {
        A.messager = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                final Random rd = new Random();
                if (A.messages.size() > 0) {
                    if (A.random) {
                        String[] splitString;
                        for (int length = (splitString = A.splitString(A.fixColor(A.messages.get(rd.nextInt(A.messages.size()))))).length, i = 0; i < length; ++i) {
                            final String str = splitString[i];
                            MsgAPI.sendOlMessage(str);
                        }
                    }
                    else {
                        if (A.i > A.messages.size() - 1) {
                            A.i = 0;
                        }
                        String[] splitString2;
                        for (int length2 = (splitString2 = A.splitString(A.fixColor(A.messages.get(A.i)))).length, j = 0; j < length2; ++j) {
                            final String str = splitString2[j];
                            MsgAPI.sendOlMessage(str);
                        }
                        ++A.i;
                    }
                }
            }
        }, 15L, A.msgsendtime * 20);
    }
    
    public static String fixColor(final String text) {
        return text.replace("&", "§");
    }
    
    public static void sendMsg(final CommandSender sender, final String msg) {
        final String chatMsg = fixColor(String.valueOf(A.head) + msg);
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            p.sendMessage(chatMsg);
        }
        else {
            System.out.println(msg);
        }
    }
    
    public static boolean checkP(final CommandSender sender, final String permission) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (!p.hasPermission(permission)) {
                p.sendMessage(fixColor(String.valueOf(A.head) + A.noPermMsg));
            }
            return p.hasPermission(permission);
        }
        return true;
    }
    
    public static void saveConfig() {
        try {
            A.filec.save(A.file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
