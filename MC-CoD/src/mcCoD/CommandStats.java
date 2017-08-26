package mcCoD;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandStats {

    private static Plugin plugin = Bukkit.getPluginManager()
            .getPlugin("MC-CoD");
    private static File userData = new File(plugin.getDataFolder(),
            File.separator + "UserData");

    @SuppressWarnings("deprecation")
    public static boolean stats(CommandSender sender, Command cmd, String label,
            String[] args) {
        if (args.length > 2) {
            sender.sendMessage(ChatColor.RED + "Too many arguments!");
            sender.sendMessage("Usage: /cod stats [target player]");
            return true;
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("stats")) {
                String targetName = Bukkit.getServer().getOfflinePlayer(args[1])
                        .getName();
                String targetUUID = Bukkit.getServer().getOfflinePlayer(args[1])
                        .getUniqueId().toString();
                File targetFile = new File(userData,
                        File.separator + targetUUID + ".yml");
                FileConfiguration targetData = YamlConfiguration
                        .loadConfiguration(targetFile);

                if (targetFile.exists()) {
                    try {
                        targetData.load(targetFile);
                        int kills = targetData.getInt("Stats.Kills");
                        int deaths = targetData.getInt("Stats.Deaths");
                        String kd = targetData.getString("Stats.K/D");
                        sender.sendMessage(ChatColor.GOLD + targetName + "'s "
                                + ChatColor.AQUA + "stats:");
                        sender.sendMessage(ChatColor.GREEN + "Kills: "
                                + ChatColor.GOLD + kills);
                        sender.sendMessage(ChatColor.RED + "Deaths: "
                                + ChatColor.GOLD + deaths);
                        sender.sendMessage(ChatColor.GREEN + "Kill"
                                + ChatColor.AQUA + "/" + ChatColor.RED
                                + "Death " + ChatColor.AQUA + "Ratio: "
                                + ChatColor.GOLD + kd);
                        targetData.save(targetFile);
                        return true;
                    } catch (IOException | InvalidConfigurationException e) {
                        e.printStackTrace();
                    }
                } else {
                    sender.sendMessage(ChatColor.GOLD + "\"" + args[1] + "\""
                            + ChatColor.RED + " has not played a game yet!");
                    return true;
                }
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("stats")) {
                if (sender instanceof Player) {
                    String senderName = sender.getName();
                    String senderUUID = Bukkit.getServer().getPlayer(senderName)
                            .getUniqueId().toString();
                    File senderFile = new File(userData,
                            File.separator + senderUUID + ".yml");
                    FileConfiguration senderData = YamlConfiguration
                            .loadConfiguration(senderFile);
                    if (senderFile.exists()) {
                        try {
                            senderData.load(senderFile);
                            int kills = senderData.getInt("Stats.Kills");
                            int deaths = senderData.getInt("Stats.Deaths");
                            String kd = senderData.getString("Stats.K/D");
                            sender.sendMessage(ChatColor.GOLD + senderName
                                    + "'s " + ChatColor.AQUA + "stats:");
                            sender.sendMessage(ChatColor.GREEN + "Kills: "
                                    + ChatColor.GOLD + kills);
                            sender.sendMessage(ChatColor.RED + "Deaths: "
                                    + ChatColor.GOLD + deaths);
                            sender.sendMessage(ChatColor.GREEN + "Kill"
                                    + ChatColor.AQUA + "/" + ChatColor.RED
                                    + "Death " + ChatColor.AQUA + "Ratio: "
                                    + ChatColor.GOLD + kd);
                            senderData.save(senderFile);
                            sender.sendMessage(
                                    ChatColor.GOLD + "Type " + ChatColor.AQUA
                                            + "/cod stats [target player] "
                                            + ChatColor.GOLD
                                            + "to see other player's stats!");
                            return true;
                        } catch (IOException
                                | InvalidConfigurationException e) {
                            e.printStackTrace();
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED
                                + "You have not played a game yet!");
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You can only execute "
                            + ChatColor.GREEN + "/cod stats [target player]!");
                    return true;
                }
            }
        }
        // Doesn't match
        return false;
    }
}
