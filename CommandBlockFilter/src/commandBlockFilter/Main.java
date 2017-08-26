package commandBlockFilter;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        registerListeners();
        registerCommands();
        getLogger().info("CommandBlockFilter has been started!");
    }

    public void onDisable() {
        getLogger().info("CommandBlockFilter has been stopped!");
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new Blacklist(this),
                this);
    }

    private void registerCommands() {
        getCommand("cbf").setExecutor(new RunCommands(this));
    }
}
