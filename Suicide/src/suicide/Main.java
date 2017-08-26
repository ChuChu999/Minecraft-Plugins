package suicide;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        getCommand("suicide").setExecutor(new Executor(this));
    }
}
