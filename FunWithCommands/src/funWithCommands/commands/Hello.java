package funWithCommands.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Hello {

    public boolean hello(CommandSender sender, Command cmd, String label,
            String[] args) {
        sender.sendMessage(ChatColor.DARK_GREEN + "Hello!");
        return true;
    }
}
