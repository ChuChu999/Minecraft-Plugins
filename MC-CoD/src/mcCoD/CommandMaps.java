package mcCoD;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandMaps {

    private static CommandSender sender;
    private static String[] args;

    public static boolean maps(CommandSender sender, Command cmd, String label,
            String[] args) {
        // Set sender
        setSender(sender);
        // Set args
        setArgs(args);
        // Too many arguments
        if (args.length >= 3) {
            sender.sendMessage(ChatColor.RED + "Too many arguments!");
            sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.WHITE
                    + "/cod maps [random | choose | list]");
            return true;
            // Maps command
        } else if (args.length == 2) {
            // Random map command
            if (args[1].equalsIgnoreCase("random")) {
                // Maps.random();
                return true;
                // Choose map command
            } else if (args[1].equalsIgnoreCase("choose")) {
                // Maps.choose();
                return true;
                // List maps command
            } else if (args[1].equalsIgnoreCase("list")) {
                // Maps.list();
                return true;
            }
            // Not enough arguments
        } else if (args.length == 1) {
            sender.sendMessage(ChatColor.RED + "Not enough arguments!");
            sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.WHITE
                    + "/cod maps [random | choose | list]");
            return true;
        }
        // Doesn't match
        return false;
    }

    public static void setSender(CommandSender value) {
        sender = value;
    }

    public static CommandSender getSender() {
        return sender;
    }

    public static void setArgs(String[] value) {
        args = value;
    }

    public static String[] getArgs() {
        return args;
    }
}
