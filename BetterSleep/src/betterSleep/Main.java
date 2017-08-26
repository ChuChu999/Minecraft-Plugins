package betterSleep;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SkipNight(this),
                this);
    }
}
