package playerCommandFilter;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Blacklist implements Listener {

    @SuppressWarnings("unused")
    private Main main;

    public Blacklist(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onCMD(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String cmd = event.getMessage().toLowerCase();

        if (cmd.contains("randomtickspeed")) {
            player.sendMessage(ChatColor.RED
                    + "Sorry, that command has been disabled for your safety!");
            event.setCancelled(true);
        }
    }
}
