package rotationTP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRotTP implements CommandExecutor {

    @SuppressWarnings("unused")
    private Main main;

    public CommandRotTP(Main main) {
        this.main = main;
    }

    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command cmd, String label,
            String[] args) {

        Player senderPlayer = Bukkit.getPlayer(sender.getName());
        double xDouble;
        double yDouble;
        double zDouble;
        float yawFloat;
        float pitchFloat;

        if (cmd.getName().equalsIgnoreCase("rottp")) {
            // Too many arguments
            if (args.length >= 8) {
                sender.sendMessage(ChatColor.RED + "Too many arguments!");
                return false;
                // "rotTP [X] [Y] [Z] [Yaw] [Pitch] [Target Player] [World]"
            } else if (args.length == 7) {
                String x = args[0];
                String y = args[1];
                String z = args[2];
                String yaw = args[3];
                String pitch = args[4];
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[5]);
                String world = args[6];
                World getWorld = Bukkit.getWorld(world);
                try {
                    xDouble = Double.parseDouble(x);
                    yDouble = Double.parseDouble(y);
                    zDouble = Double.parseDouble(z);
                    yawFloat = Float.parseFloat(yaw);
                    pitchFloat = Float.parseFloat(pitch);
                    // Not numbers
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED
                            + "You must enter a NUMBER for the X, Y, Z, Yaw, & Pitch values!");
                    return false;
                }
                // If world doesn't exist
                if (getWorld == null) {
                    sender.sendMessage(ChatColor.RED + "World " + ChatColor.GOLD
                            + world + ChatColor.RED + " Doesn't exist!");
                    return false;
                    // Target Player isn't online
                } else if (!offlinePlayer.isOnline()) {
                    sender.sendMessage(ChatColor.GOLD + offlinePlayer.getName()
                            + ChatColor.RED + " is Offline!");
                    return false;
                    // Target Player is online
                } else {
                    // Get target player
                    Player onlinePlayer = Bukkit.getPlayer(args[5]);
                    // sender name == targetPlayer name
                    if (sender.getName()
                            .equalsIgnoreCase(onlinePlayer.getName())) {
                        senderPlayer.teleport(new Location(getWorld, xDouble,
                                yDouble, zDouble, yawFloat, pitchFloat));
                        // senderPlayer.sendMessage(ChatColor.GREEN +
                        // "Teleported to " +
                        // ChatColor.RED + x + ", " + y
                        // + ", " + z + ", " + yaw + ", " + pitch);
                        return true;
                        // sender name != targetPlayer name
                    } else {
                        onlinePlayer.teleport(new Location(getWorld, xDouble,
                                yDouble, zDouble, yawFloat, pitchFloat));
                        sender.sendMessage(
                                ChatColor.GREEN + "Teleported " + ChatColor.GOLD
                                        + onlinePlayer.getName() + "!");
                        return true;
                    }
                }
                // Sender is Player
            } else if (sender instanceof Player) {
                // "rotTP [X] [Y] [Z] [Yaw] [Pitch] [Target Player]"
                if (args.length == 6) {
                    World world = senderPlayer.getWorld();
                    String x = args[0];
                    String y = args[1];
                    String z = args[2];
                    String yaw = args[3];
                    String pitch = args[4];
                    OfflinePlayer offlinePlayer = Bukkit
                            .getOfflinePlayer(args[5]);
                    // Checks if Strings are numbers
                    try {
                        xDouble = Double.parseDouble(x);
                        yDouble = Double.parseDouble(y);
                        zDouble = Double.parseDouble(z);
                        yawFloat = Float.parseFloat(yaw);
                        pitchFloat = Float.parseFloat(pitch);
                        // Not numbers
                    } catch (NumberFormatException e) {
                        sender.sendMessage(ChatColor.RED
                                + "You must enter a NUMBER for the X, Y, Z, Yaw, & Pitch values!");
                        return false;
                    }
                    // Target Player == @a
                    if (args[5].equalsIgnoreCase("@a")) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.teleport(new Location(world, xDouble, yDouble,
                                    zDouble, yawFloat, pitchFloat));
                            // all.sendMessage(ChatColor.GREEN +
                            // sender.getName() + ChatColor.RED
                            // + " has Teleported you to " + ChatColor.WHITE + x
                            // + ", " + y + ", " +
                            // z);
                        }
                        sender.sendMessage(ChatColor.GREEN + "Teleported ALL to"
                                + ChatColor.WHITE + x + ", " + y + ", " + z);
                        return true;
                        // Target Player isn't online
                    } else if (!offlinePlayer.isOnline()) {
                        sender.sendMessage(
                                ChatColor.GOLD + offlinePlayer.getName()
                                        + ChatColor.RED + " is Offline!");
                        return false;
                        // Target Player is online
                    } else {
                        // Get target player
                        Player onlinePlayer = Bukkit.getPlayer(args[5]);
                        // sender name == targetPlayer name
                        if (sender.getName()
                                .equalsIgnoreCase(onlinePlayer.getName())) {
                            senderPlayer.teleport(new Location(world, xDouble,
                                    yDouble, zDouble, yawFloat, pitchFloat));
                            // senderPlayer.sendMessage(ChatColor.GREEN +
                            // "Teleported to " +
                            // ChatColor.RED + x + ", " + y
                            // + ", " + z + ", " + yaw + ", " + pitch);
                            return true;
                            // sender name != targetPlayer name
                        } else {
                            onlinePlayer.teleport(new Location(world, xDouble,
                                    yDouble, zDouble, yawFloat, pitchFloat));
                            // onlinePlayer.sendMessage(ChatColor.GREEN +
                            // sender.getName() +
                            // ChatColor.RED
                            // + " has Teleported you to" + ChatColor.WHITE + x
                            // + ", " + y + ", " +
                            // z);
                            sender.sendMessage(ChatColor.GREEN + "Teleported "
                                    + ChatColor.GOLD + onlinePlayer.getName()
                                    + "!");
                            return true;
                        }
                    }
                    // "rottp [X] [Y] [Z] [Yaw] [Pitch]"
                } else if (args.length == 5) {
                    World world = senderPlayer.getWorld();
                    String x = args[0];
                    String y = args[1];
                    String z = args[2];
                    String yaw = args[3];
                    String pitch = args[4];
                    // Checks if Strings are numbers
                    try {
                        xDouble = Double.parseDouble(x);
                        yDouble = Double.parseDouble(y);
                        zDouble = Double.parseDouble(z);
                        yawFloat = Float.parseFloat(yaw);
                        pitchFloat = Float.parseFloat(pitch);
                        // Not numbers
                    } catch (NumberFormatException e) {
                        sender.sendMessage(ChatColor.RED
                                + "You must enter a NUMBER for the X, Y, Z, Yaw, & Pitch values!");
                        return false;
                    }
                    // Are numbers
                    senderPlayer.teleport(new Location(world, xDouble, yDouble,
                            zDouble, yawFloat, pitchFloat));
                    // senderPlayer.sendMessage(ChatColor.GREEN + "Teleported to
                    // " + ChatColor.RED +
                    // x + ", " + y + ", "
                    // + z + ", " + yaw + ", " + pitch);
                    return true;
                    // "rotTP [X] [Y] [Z] [Yaw]"
                } else if (args.length == 4) {
                    World world = senderPlayer.getWorld();
                    String x = args[0];
                    String y = args[1];
                    String z = args[2];
                    String yaw = args[3];
                    // Checks if Strings are numbers
                    try {
                        xDouble = Double.parseDouble(x);
                        yDouble = Double.parseDouble(y);
                        zDouble = Double.parseDouble(z);
                        yawFloat = Float.parseFloat(yaw);
                        // Not numbers
                    } catch (NumberFormatException e) {
                        sender.sendMessage(ChatColor.RED
                                + "You must enter a NUMBER for the X, Y, Z, Yaw, & Pitch values!");
                        return false;
                    }
                    // Are numbers
                    senderPlayer.teleport(new Location(world, xDouble, yDouble,
                            zDouble, yawFloat, 0));
                    // senderPlayer.sendMessage(ChatColor.GREEN + "Teleported to
                    // " + ChatColor.RED +
                    // x + ", " + y + ", "
                    // + z + ", " + yaw);
                    return true;
                    // "rotTP [X] [Y] [Z]"
                } else if (args.length == 3) {
                    World world = senderPlayer.getWorld();
                    String x = args[0];
                    String y = args[1];
                    String z = args[2];
                    // Checks if Strings are numbers
                    try {
                        xDouble = Double.parseDouble(x);
                        yDouble = Double.parseDouble(y);
                        zDouble = Double.parseDouble(z);
                        // Not numbers
                    } catch (NumberFormatException e) {
                        sender.sendMessage(ChatColor.RED
                                + "You must enter a NUMBER for the X, Y, Z, Yaw, & Pitch values!");
                        return false;
                    }
                    // Are numbers
                    senderPlayer.teleport(
                            new Location(world, xDouble, yDouble, zDouble));
                    // senderPlayer.sendMessage(ChatColor.GREEN + "Teleported to
                    // " + ChatColor.RED +
                    // x + ", " + y + ", "
                    // + z);
                    return true;
                    // Not enough arguments
                } else {
                    senderPlayer.sendMessage(
                            ChatColor.RED + "Not enough arguments!");
                    return false;
                }
                // Sender isn't Player
            } else {
                sender.sendMessage(ChatColor.RED
                        + "You must be a Player to execute this command!");
                sender.sendMessage(ChatColor.RED
                        + "Please add [World] if you want to execute this command!");
                return false;
            }
        }
        return false;
    }
}
