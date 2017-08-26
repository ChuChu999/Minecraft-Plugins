package loginTP;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TP implements Listener {

    @SuppressWarnings("unused")
    private Main main;

    public TP(Main main) {
        this.main = main;
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location spawn = new Location(Bukkit.getWorld("XPCrafters"), 0.5, 10,
                0.5, 0, 0);
        player.teleport(spawn);
    }
}
