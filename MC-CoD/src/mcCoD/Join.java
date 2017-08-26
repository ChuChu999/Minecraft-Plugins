package mcCoD;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @SuppressWarnings("unused")
    private Main main;

    public Join(Main main) {
        this.main = main;
    }

    @EventHandler
    public void createPlayerData(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        String uuid = player.getUniqueId().toString();
        File userData = new File(Bukkit.getServer().getPluginManager()
                .getPlugin("MC-CoD").getDataFolder(),
                File.separator + "UserData");
        File file = new File(userData, File.separator + uuid + ".yml");
        FileConfiguration playerData = YamlConfiguration
                .loadConfiguration(file);
        // Creates playerData file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
                playerData.load(file);
                playerData.set("Name", name);
                playerData.set("Stats.Kills", 0);
                playerData.set("Stats.Deaths", 0);
                playerData.set("Stats.K/D", 0);
                playerData.save(file);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    @EventHandler
    public void startTimer(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setScoreboard(RunTimer.getBoard());
        if (Bukkit.getServer().getOnlinePlayers().size() >= 2
                && !RunTimer.isTimerStarted()) {
            RunTimer.start();
        }
    }

    @EventHandler
    public void setHealth(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setHealthScale(20.0);
        player.setHealth(100.0);
        player.sendMessage("" + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC
                + "fat " + ChatColor.GREEN + "[MC-CoD] " + ChatColor.AQUA
                + "Type " + ChatColor.GOLD + "/cod stats " + ChatColor.AQUA
                + "to see your stats!!!" + ChatColor.LIGHT_PURPLE
                + ChatColor.MAGIC + " fat");
    }
}
