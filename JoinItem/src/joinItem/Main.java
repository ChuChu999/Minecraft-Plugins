package joinItem;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Join(this), this);
        getLogger().info("JoinItem has been enabled!");
    }

    public void onDisable() {
        getLogger().info("JoinItem has been disabled!");
    }
}
