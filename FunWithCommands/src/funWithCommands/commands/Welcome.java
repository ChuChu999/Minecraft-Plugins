package funWithCommands.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Welcome {

    public boolean welcome(CommandSender sender, Command cmd, String label,
            String[] args) {
        sender.sendMessage(ChatColor.GREEN + "Welcome!");
        return true;
    }
}
