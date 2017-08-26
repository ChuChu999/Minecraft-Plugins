package rapidFire;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

public class Fire implements Listener {

    private Main main;
    private int taskID;

    public Fire(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onScroll(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        String world = player.getWorld().getName();
        Projectile arrow = player.launchProjectile(Arrow.class);
        Vector velocity = arrow.getVelocity();
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

        taskID = scheduler.scheduleSyncRepeatingTask(main, new Runnable() {
            public void run() {
                if (!arrow.isOnGround()) {
                    // Spawn arrows
                    Bukkit.getWorld(world).spawnArrow(arrow.getLocation(),
                            velocity, 1f, 1f);
                } else {
                    scheduler.cancelTask(taskID);
                }
            }
        }, 0, 3);
    }
}
