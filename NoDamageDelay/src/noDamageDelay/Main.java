package noDamageDelay;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    public void onEnable() {
        getLogger().info("NoDamageDelay has been started!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        getLogger().info("NoDamageDelay has been stopped!");
    }

    @EventHandler
    public void playerDamageDelay(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setNoDamageTicks(0);
        player.setMaximumNoDamageTicks(0);
    }

    @EventHandler
    public void entityDamageDelay(CreatureSpawnEvent event) {
        LivingEntity entity = event.getEntity();
        entity.setNoDamageTicks(0);
        entity.setMaximumNoDamageTicks(0);
    }

}
