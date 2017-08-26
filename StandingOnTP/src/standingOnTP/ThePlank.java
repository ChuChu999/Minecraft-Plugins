package standingOnTP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ThePlank {

    public static void touchingWater() {
        Bukkit.getServer().getScheduler()
                .scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
                    public void run() {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            String playerName = player.getName();
                            Location centerLoc = Bukkit.getWorld("world")
                                    .getBlockAt(-78, 7, 1071).getLocation();
                            Location playerLoc = player.getLocation();
                            String block = playerLoc.getBlock().getType()
                                    .toString();
                            String world = player.getWorld().getName()
                                    .toString();
                            if (world.equalsIgnoreCase("world")) {
                                double radius = playerLoc.distance(centerLoc);
                                if (radius <= 10 && block
                                        .equalsIgnoreCase("stationary_water")) {
                                    for (Player broadcast : Bukkit
                                            .getOnlinePlayers()) {
                                        broadcast.sendMessage(ChatColor.RED
                                                + playerName + ChatColor.GOLD
                                                + " walked the plank!");
                                    }
                                    // Kill player
                                    player.setHealth(0.00);
                                }
                            }
                        }
                    }
                }, 0, 50);
    }
}
