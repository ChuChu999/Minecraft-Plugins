package autoRespawn;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Respawn(this), this);
        getLogger().info("AutoRespawn has been started!");
    }

    public void onDisable() {
        getLogger().info("AutoRespawn has been stopped!");
    }
}
