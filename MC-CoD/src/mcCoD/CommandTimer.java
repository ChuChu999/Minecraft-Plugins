package mcCoD;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scoreboard.Scoreboard;

public class CommandTimer {

    private static File folder = new File(
            "plugins" + File.separator + "MC-CoD");
    private static File timer = new File(folder, File.separator + "Timer.yml");
    private static FileConfiguration timerData = YamlConfiguration
            .loadConfiguration(timer);

    public static boolean timer(CommandSender sender, Command cmd, String label,
            String[] args) {
        // Too many arguments
        if (args.length > 3) {
            sender.sendMessage(ChatColor.RED + "Too many arguments!");
            sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.WHITE
                    + "/cod timer [start | stop | set]");
            return true;
        } else if (args.length == 3) {
            // Set Timer
            if (args[0].equalsIgnoreCase("timer")
                    && args[1].equalsIgnoreCase("set")) {
                String input = args[2];
                // Checks for number
                if (input.matches("\\d+")) {
                    int timerInt = Integer.parseInt(input);
                    if (timerInt > 0) {
                        try {
                            timerData.load(timer);
                            timerData.set("startTime", timerInt);
                            timerData.save(timer);
                            sender.sendMessage(ChatColor.GOLD
                                    + "Start Time set to " + ChatColor.RED
                                    + timerInt + ChatColor.GOLD + " Seconds!");
                            return true;
                        } catch (IOException
                                | InvalidConfigurationException e) {
                            e.printStackTrace();
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED
                                + "You must enter a positive integer!");
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED
                            + "You must enter a positive integer!");
                    return true;
                }
            }
        } else if (args.length == 2) {
            // Start timer
            if (args[1].equalsIgnoreCase("start")) {
                if (!RunTimer.isTimerStarted()) {
                    sender.sendMessage(
                            ChatColor.GOLD + "Timer has been started!");
                    RunTimer.start();
                    return true;
                } else {
                    sender.sendMessage(
                            ChatColor.RED + "Timer has already been started!");
                    return true;
                }
                // Stop timer
            } else if (args[0].equalsIgnoreCase("timer")
                    && args[1].equalsIgnoreCase("stop")) {
                if (RunTimer.isTimerStarted()) {
                    Scoreboard board = RunTimer.getBoard();
                    RunTimer.setTimerStarted(false);
                    board.resetScores(ChatColor.GREEN + "Time Left:");
                    sender.sendMessage(
                            ChatColor.GOLD + "Timer has been stopped!");
                    return true;
                } else {
                    sender.sendMessage(
                            ChatColor.RED + "Timer is already stopped!");
                    return true;
                }
                // Set timer without arguments
            } else if (args[0].equalsIgnoreCase("timer")
                    && args[1].equalsIgnoreCase("set")) {
                sender.sendMessage(ChatColor.RED + "Please enter the "
                        + ChatColor.GREEN + "Start Time " + ChatColor.RED
                        + "of the timer");
                sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.RESET
                        + "/cod timer set [start time in seconds]");
                return true;
            }
            // Not enough arguments
        } else if (args.length == 1) {
            sender.sendMessage(ChatColor.RED + "Not enough arguments!");
            sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.WHITE
                    + "/cod timer [start | stop | set]");
            return true;
        }
        // Doesn't match
        return false;
    }
}
