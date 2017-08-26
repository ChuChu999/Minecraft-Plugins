package autoUnloadChunks;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new UnloadChunks(this),
                this);
        UnloadChunks.unloadChunks();
    }
}
