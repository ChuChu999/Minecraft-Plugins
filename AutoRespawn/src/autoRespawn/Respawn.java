package autoRespawn;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Respawn implements Listener {

    @SuppressWarnings("unused")
    private Main main;

    public Respawn(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (player.isDead()) {
            player.setHealth(20);
        }
    }
}
