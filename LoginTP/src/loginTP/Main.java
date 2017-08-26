package loginTP;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new TP(this), this);
        getLogger().info("LoginTP has been enabled!");
    }

    public void onDisable() {
        getLogger().info("LoginTP has been disabled!");
    }
}
