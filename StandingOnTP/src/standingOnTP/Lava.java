package standingOnTP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Lava {

    public static void touchingLava() {
        Bukkit.getServer().getScheduler()
                .scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
                    public void run() {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            Location playerLoc = player.getLocation();
                            String block = playerLoc.getBlock().getType()
                                    .toString();
                            if (block.equalsIgnoreCase("stationary_lava")
                                    && player.getWorld().getName()
                                            .equals("world")) {
                                player.playSound(playerLoc,
                                        Sound.BLOCK_LAVA_EXTINGUISH, 1, 1);
                                player.teleport(
                                        new Location(Bukkit.getWorld("world"),
                                                2.5, 13, -3.5, -90, 0));
                                player.sendMessage("<" + ChatColor.BLUE
                                        + "Old Man Jenkins" + ChatColor.RESET
                                        + "> " + ChatColor.RED
                                        + "GET OFF MY LAWN!!!");
                            }
                        }
                    }
                }, 0, 50);
    }
}
