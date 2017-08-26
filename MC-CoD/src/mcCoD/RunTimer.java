package mcCoD;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class RunTimer {

    private static File folder = new File(
            "plugins" + File.separator + "MC-CoD");
    private static File timer = new File(folder, File.separator + "Timer.yml");
    private static FileConfiguration timerData = YamlConfiguration
            .loadConfiguration(timer);
    private static int taskID;
    private static ScoreboardManager manager = Bukkit.getScoreboardManager();
    private static Scoreboard board = manager.getNewScoreboard();
    private static Objective obj = getBoard().registerNewObjective("Time",
            "dummy");
    private static boolean isTimerStarted = false;

    public static void start() {
        final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

        setTimerStarted(true);
        obj.setDisplayName("Countdown");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Read Start Time
        try {
            timerData.load(timer);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        // Get Start Time
        final int startTime = timerData.getInt("startTime");
        // Save Start Time
        try {
            timerData.save(timer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set Scoreboard
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(getBoard());
        }

        // Countdown
        taskID = scheduler.scheduleSyncRepeatingTask(Main.getMain(),
                new Runnable() {
                    private Score score = obj
                            .getScore(ChatColor.GREEN + "Time Left:");
                    private int time = startTime;

                    public void run() {
                        if (time >= 11 && isTimerStarted()) {
                            score.setScore(time);
                            time--;
                        } else if (time <= 10 && time >= 2
                                && isTimerStarted()) {
                            score.setScore(time);
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                Location pLoc = player.getLocation();
                                player.playSound(pLoc,
                                        Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1,
                                        1);
                                player.sendMessage("" + ChatColor.GREEN + time
                                        + ChatColor.AQUA
                                        + " Seconds Remaining!");
                            }
                            time--;
                        } else if (time == 1 && isTimerStarted()) {
                            score.setScore(time);
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                Location pLoc = player.getLocation();
                                player.playSound(pLoc,
                                        Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1,
                                        1);
                                player.sendMessage("" + ChatColor.GREEN + time
                                        + ChatColor.AQUA
                                        + " Second Remaining!");
                            }
                            time--;
                        } else if (time == 0 && isTimerStarted()) {
                            score.setScore(time);
                            getBoard().resetScores(
                                    ChatColor.GREEN + "Time Left:");
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                Location pLoc = player.getLocation();
                                player.playSound(pLoc,
                                        Sound.BLOCK_STONE_BUTTON_CLICK_ON, 1,
                                        1);
                                player.sendMessage(
                                        ChatColor.GREEN + "Match is starting!");
                            }
                            setTimerStarted(false);
                        } else {
                            // TP players
                            // Maps.tpAll();
                            scheduler.cancelTask(taskID);
                        }
                    }
                }, 0, 20);
    }

    public static Scoreboard getBoard() {
        return board;
    }

    public static void setTimerStarted(boolean value) {
        isTimerStarted = value;
    }

    public static boolean isTimerStarted() {
        return isTimerStarted;
    }
}
