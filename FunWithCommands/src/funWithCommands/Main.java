package funWithCommands;

import org.bukkit.plugin.java.JavaPlugin;

import funWithCommands.commands.Executor;

public class Main extends JavaPlugin {

    public void onEnable() {
        registerCommands();
        getLogger().info("FunWithCommands has been started!");
    }

    public void onDisable() {
        getLogger().info("FunWithCommands has been stopped!");
    }

    public void registerCommands() {
        getCommand("Test").setExecutor(new Executor(this));
    }
}
