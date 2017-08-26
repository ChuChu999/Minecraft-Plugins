package rotationTP;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        getCommand("rottp").setExecutor(new CommandRotTP(this));
        getLogger().info("RotationTP has been started!");
    }

    public void onDisable() {
        getLogger().info("RotationTP has been stopped!");
    }
}
