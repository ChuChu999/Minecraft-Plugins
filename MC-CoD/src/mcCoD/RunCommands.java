package mcCoD;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RunCommands implements CommandExecutor {

    @SuppressWarnings("unused")
    private Main main;

    public RunCommands(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label,
            String[] args) {
        if (cmd.getName().equalsIgnoreCase("cod")) {
            if (args.length >= 1) {
                // Stats command
                if (args[0].equalsIgnoreCase("stats")) {
                    return CommandStats.stats(sender, cmd, label, args);
                    // Timer command
                } else if (args[0].equalsIgnoreCase("timer")) {
                    // Has permission
                    if (sender.hasPermission("cod.timer")) {
                        return CommandTimer.timer(sender, cmd, label, args);
                        // Doesn't have permission
                    } else {
                        sender.sendMessage(ChatColor.RED
                                + "You do not have Permission to execute this command!");
                        sender.sendMessage(
                                ChatColor.RED + "You can only execute "
                                        + ChatColor.GOLD + "/cod stats!");
                        return true;
                    }
                    // Maps Command
                } else if (args[0].equalsIgnoreCase("maps")) {
                    // Has permission
                    if (sender.hasPermission("cod.maps")) {
                        return CommandMaps.maps(sender, cmd, label, args);
                        // Doesn't have permission
                    } else {
                        sender.sendMessage(ChatColor.RED
                                + "You do not have Permission to execute this command!");
                        sender.sendMessage(
                                ChatColor.RED + "You can only execute "
                                        + ChatColor.GOLD + "/cod stats!");
                        return true;
                    }
                    // Wrong arguments
                } else {
                    sender.sendMessage(
                            ChatColor.RED + "That command doesn't exist!");
                    return false;
                }
                // No arguments
            } else if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                return false;
            }
        }
        return false;
    }
}
