package betterSleep;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class SkipNight implements Listener {

    private Main main;

    public SkipNight(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        if (Bukkit.getOnlinePlayers().size() > 1) {
            Player player = event.getPlayer();
            World world = player.getWorld();
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

            scheduler.scheduleSyncDelayedTask(main, new Runnable() {
                public void run() {
                    world.setTime(0);
                }
            }, 100);
        }
    }
}
