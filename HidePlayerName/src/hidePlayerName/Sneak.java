package hidePlayerName;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Sneak implements Listener {

    @SuppressWarnings("unused")
    private Main main;

    public Sneak(Main main) {
        this.main = main;
    }

    @EventHandler
    public void alwaysShift(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String world = player.getWorld().getName();
        if (!world.equalsIgnoreCase("world")
                && !world.equalsIgnoreCase("XPCrafters")
                && !player.isFlying()) {
            if (!player.isSneaking()) {
                player.setSneaking(true);
            }
            if (player.isSprinting()) {
                player.setWalkSpeed((float) 0.26);
            } else {
                player.setWalkSpeed((float) 0.2);
            }
        }
    }

    @EventHandler
    public void neverShift(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String world = player.getWorld().getName();
        if (world.equalsIgnoreCase("XPCrafters") && player.isSneaking()) {
            player.setSneaking(false);
        }
    }
}
