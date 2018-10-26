package cn.mgazul.pfess;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class StatsUtils{
	
    public static long freeMemory() {
        return Runtime.getRuntime().freeMemory();
    }
    
    public static long maxMemory() {
        return Runtime.getRuntime().maxMemory();
    }
    
    public static long totalMemory() {
        return Runtime.getRuntime().totalMemory();
    }
    
    public static double getMemoryUsage() {
        final long used_memory = totalMemory() - freeMemory();
        final double percent = totalMemory() / 100L;
        final double usage = used_memory / percent;
        return usage;
    }
    
    public static String getMemoryUsageBar() {
        String usagebar = "";
        final double round_usage = getMemoryUsage() * 0.7;
        final int usage = (int)Math.ceil(round_usage);
        for (int i = 0; i < 70; ++i) {
            if (i < usage) {
                usagebar = String.valueOf(usagebar) + "§c\u25ac";
            }
            else if (usage == i) {
                usagebar = String.valueOf(usagebar) + "§6\u25ac";
            }
            else {
                usagebar = String.valueOf(usagebar) + "§a\u25ac";
            }
        }
        return usagebar;
    }
    
    public static double LoadAverange() {
        final OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        return osBean.getSystemLoadAverage();
    }
    
    public static double getProcessCpuLoad() throws Exception {
        final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        final ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
        final AttributeList list = mbs.getAttributes(name, new String[] { "ProcessCpuLoad" });
        if (list.isEmpty()) {
            return Double.NaN;
        }
        final Attribute att = (Attribute) list.get(0);
        Double value = (Double)att.getValue();
        if (value == -1.0) {
            return Double.NaN;
        }
        return (int)(value * 1000.0) / 10.0;
    }
    
    public static String getCPUUsageBar() throws Exception {
        String usagebar = "";
        final double round_usage = getProcessCpuLoad() * 0.7;
        final int usage = (int)Math.ceil(round_usage);
        for (int i = 0; i < 70; ++i) {
            if (i < usage) {
                usagebar = String.valueOf(usagebar) + "§c\u25ac";
            }
            else if (usage == i) {
                usagebar = String.valueOf(usagebar) + "§6\u25ac";
            }
            else {
                usagebar = String.valueOf(usagebar) + "§a\u25ac";
            }
        }
        return usagebar;
    }
    
    public static long totalDisk() {
        final File f = new File("/");
        return f.getTotalSpace();
    }
    
    public static long freeDisk() {
        final File f = new File("/");
        return f.getFreeSpace();
    }
    
    public static long usableDisk() {
        final File f = new File("/");
        return f.getUsableSpace();
    }
    
    public static double getDiskUsage() {
        final long used_memory = totalDisk() - freeDisk();
        final double percent = totalDisk() / 100L;
        final double usage = used_memory / percent;
        return usage;
    }
    
    public static String getDiskUsageBar() {
        String usagebar = "";
        final double round_usage = getDiskUsage() * 0.7;
        final int usage = (int)Math.ceil(round_usage);
        for (int i = 0; i < 70; ++i) {
            if (i < usage) {
                usagebar = String.valueOf(usagebar) + "§c\u25ac";
            }
            else if (usage == i) {
                usagebar = String.valueOf(usagebar) + "§6\u25ac";
            }
            else {
                usagebar = String.valueOf(usagebar) + "§a\u25ac";
            }
        }
        return usagebar;
    }
    
    public static double BytesToMegaBytes(final long bytes) {
        final double kilo_bytes = bytes / 1024L;
        final double mega_bytes = kilo_bytes / 1024.0;
        return round(mega_bytes, 3);
    }
    
    public static double BytesToGigaBytes(final long bytes) {
        final double kilo_bytes = bytes / 1024L;
        final double mega_bytes = kilo_bytes / 1024.0;
        final double giga_bytes = mega_bytes / 1024.0;
        return round(giga_bytes, 3);
    }
    
    static double round(final double value, final int sec) {
        return Math.round(value * Math.pow(10.0, sec)) / Math.pow(10.0, sec);
    }

}
