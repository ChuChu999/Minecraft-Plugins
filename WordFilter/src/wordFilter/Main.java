package wordFilter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("deprecation")
public class Main extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        getLogger().info("WordFilter has been started!");
    }

    public void onDisable() {
        getLogger().info("WordFilter has been stopped!");
    }

    @EventHandler
    public void ChatEvent(PlayerChatEvent event) {
        Player sender = event.getPlayer();
        String senderMessage = event.getMessage();
        Object[] wordList = getConfig().getList("Words").toArray();
        for (Object word : wordList) {
            String wordString = word.toString();
            String removeChars = wordString.replace("{", "").replace("}", "")
                    .replace("[", "").replace("]", "");
            String[] split = removeChars.split("=");
            String targetWord = split[0];
            String commandList = split[1];
            String[] splitCommands = commandList.split("/");
            if (senderMessage.toLowerCase().replace(" ", "")
                    .contains(targetWord.toLowerCase())) {
                for (String command : splitCommands) {
                    // Removes ", " from command
                    if (command.endsWith(", ")) {
                        String newCommand = command.substring(0,
                                command.length() - 2);
                        if (newCommand.contains("%pm")
                                && newCommand.contains("%sender")) {
                            String nonColored = newCommand.replace("%pm ", "")
                                    .replace("%sender", sender.getName());
                            String colored = ChatColor
                                    .translateAlternateColorCodes('&',
                                            nonColored);
                            sender.sendMessage(colored);
                        } else if (newCommand.contains("%pm")) {
                            String nonColored = newCommand.replace("%pm ", "");
                            String colored = ChatColor
                                    .translateAlternateColorCodes('&',
                                            nonColored);
                            sender.sendMessage(colored);
                        } else if (newCommand.contains("%sender")) {
                            String replacePlayer = newCommand.replace("%sender",
                                    sender.getName());
                            Bukkit.getServer().dispatchCommand(
                                    Bukkit.getConsoleSender(), replacePlayer);
                        } else if (!command.isEmpty()) {
                            Bukkit.getServer().dispatchCommand(
                                    Bukkit.getConsoleSender(), command);
                        }
                    } else {
                        if (command.contains("%pm")
                                && command.contains("%sender")) {
                            String nonColored = command.replace("%pm ", "")
                                    .replace("%sender", sender.getName());
                            String colored = ChatColor
                                    .translateAlternateColorCodes('&',
                                            nonColored);
                            sender.sendMessage(colored);
                        } else if (command.contains("%pm")) {
                            String nonColored = command.replace("%pm ", "");
                            String colored = ChatColor
                                    .translateAlternateColorCodes('&',
                                            nonColored);
                            sender.sendMessage(colored);
                        } else if (command.contains("%sender")) {
                            String newCommand = command.replace("%sender",
                                    sender.getName());
                            Bukkit.getServer().dispatchCommand(
                                    Bukkit.getConsoleSender(), newCommand);
                        } else if (!command.isEmpty()) {
                            Bukkit.getServer().dispatchCommand(
                                    Bukkit.getConsoleSender(), command);
                        }
                    }
                }
                event.setCancelled(true);
            }
        }
    }
}
