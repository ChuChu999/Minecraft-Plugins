package autoUnloadChunks;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class UnloadChunks implements Listener {

    private static Main main;

    public UnloadChunks(Main main) {
        UnloadChunks.main = main;
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        unloadChunks();
    }

    public static void unloadChunks() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

        // Delay 1 second before running
        scheduler.scheduleSyncDelayedTask(main, new Runnable() {
            public void run() {
                // For every world
                for (World world : Bukkit.getWorlds()) {
                    // Unload all chunks
                    for (Chunk chunk : world.getLoadedChunks()) {
                        chunk.unload();
                    }
                }
            }
        }, 20);
    }
}
