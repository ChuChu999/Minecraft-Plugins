package hidePlayerName;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        registerClasses();
        getLogger().info("HidePlayerName has been started!");
    }

    public void onDisable() {
        getLogger().info("HidePlayerName has been stopped!");
    }

    public void registerClasses() {
        getServer().getPluginManager().registerEvents(new Sneak(this), this);
    }

}
