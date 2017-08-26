package playerCommandFilter;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Blacklist(this),
                this);
        getLogger().info("PlayerCommandFilter has been started!");
    }

    public void onDisable() {
        getLogger().info("PlayerCommandFilter has been stopped!");
    }
}
