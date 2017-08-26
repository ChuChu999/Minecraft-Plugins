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

public class Kills implements Listener {

    @SuppressWarnings("unused")
    private Main main;

    public Kills(Main main) {
        this.main = main;
    }

    @EventHandler
    public void killEvent(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        // Checks if killer is a player
        if (killer instanceof Player) {
            String uuid = killer.getUniqueId().toString();
            File userData = new File(Bukkit.getServer().getPluginManager()
                    .getPlugin("MC-CoD").getDataFolder(),
                    File.separator + "UserData");
            File killerFile = new File(userData,
                    File.separator + uuid + ".yml");
            FileConfiguration killerData = YamlConfiguration
                    .loadConfiguration(killerFile);
            // Updates kills
            try {
                killerData.load(killerFile);
                int kills = killerData.getInt("Stats.Kills");
                killerData.set("Stats.Kills", kills + 1);
                killerData.save(killerFile);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
            // Updates K/D
            try {
                killerData.load(killerFile);
                double kills = killerData.getDouble("Stats.Kills");
                double deaths = killerData.getDouble("Stats.Deaths");
                if (deaths == 0) {
                    killerData.set("Stats.K/D", kills);
                    killerData.save(killerFile);
                } else {
                    DecimalFormat df = new DecimalFormat("0.00");
                    double rawKD = kills / deaths;
                    String stringKD = df.format(rawKD);
                    killerData.set("Stats.K/D", Double.parseDouble(stringKD));
                    killerData.save(killerFile);
                }
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
    }
}
