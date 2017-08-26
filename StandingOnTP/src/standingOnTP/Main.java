package standingOnTP;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    private static Main main;

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getMain();
        setMain();
        Lava.touchingLava();
        ThePlank.touchingWater();
        getLogger().info("StandingOnTP has been started!");
    }

    public void onDisable() {
        getLogger().info("StandingOnTP has been stopped!");
    }

    public void setMain() {
        main = this;
    }

    public static Main getMain() {
        return main;
    }
}
