package mcCoD;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main main;
    private File folder = new File("plugins" + File.separator + "MC-CoD");

    public void onEnable() {
        registerClasses();
        registerCommands();
        createFolders();
        createFiles();
        getMain();
        setMain();
        getLogger().info("MC-CoD has been started!");
    }

    public void onDisable() {
        getLogger().info("MC-CoD has been stopped!");
    }

    public void registerClasses() {
        getServer().getPluginManager().registerEvents(new Join(this), this);
        getServer().getPluginManager().registerEvents(new Quit(this), this);
        getServer().getPluginManager().registerEvents(new Kills(this), this);
        getServer().getPluginManager().registerEvents(new Deaths(this), this);
    }

    public void registerCommands() {
        this.getCommand("cod").setExecutor(new RunCommands(this));
    }

    public void createFolders() {
        File userData = new File(folder, File.separator + "UserData");
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (!userData.exists()) {
            userData.mkdir();
        }
    }

    public void createFiles() {
        File timer = new File(folder, File.separator + "Timer.yml");
        FileConfiguration timerData = YamlConfiguration
                .loadConfiguration(timer);
        if (!timer.exists()) {
            try {
                timer.createNewFile();
                timerData.load(timer);
                timerData.set("startTime", 60);
                timerData.save(timer);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    public void setMain() {
        main = this;
    }

    public static Main getMain() {
        return main;
    }
}
