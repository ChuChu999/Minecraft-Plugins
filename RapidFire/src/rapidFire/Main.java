package rapidFire;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Fire(this), this);
        getLogger().info("RapidFire has been enabled!");
    }

    public void onDisable() {
        getLogger().info("RapidFire has been disabled!");
    }
}
