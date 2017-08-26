package mcCoD;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Deaths implements Listener {

    @SuppressWarnings("unused")
    private Main main;

    public Deaths(Main main) {
        this.main = main;
    }

    @EventHandler
    public void deathEvent(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if (killer instanceof Player) {
            Player player = event.getEntity().getPlayer();
            String uuid = player.getUniqueId().toString();
            File userData = new File(Bukkit.getServer().getPluginManager()
                    .getPlugin("MC-CoD").getDataFolder(),
                    File.separator + "UserData");
            File victimFile = new File(userData,
                    File.separator + uuid + ".yml");
            FileConfiguration victimData = YamlConfiguration
                    .loadConfiguration(victimFile);
            // Updates Deaths
            try {
                victimData.load(victimFile);
                int deaths = victimData.getInt("Stats.Deaths");
                victimData.set("Stats.Deaths", deaths + 1);
                victimData.save(victimFile);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
            // Updates K/D
            try {
                DecimalFormat df = new DecimalFormat("0.00");
                victimData.load(victimFile);
                double kills = victimData.getDouble("Stats.Kills");
                double deaths = victimData.getDouble("Stats.Deaths");
                double rawKD = kills / deaths;
                String stringKD = df.format(rawKD);
                victimData.set("Stats.K/D", Double.parseDouble(stringKD));
                victimData.save(victimFile);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
    }
}
