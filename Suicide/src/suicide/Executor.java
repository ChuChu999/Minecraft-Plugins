package suicide;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Executor implements CommandExecutor {

    @SuppressWarnings("unused")
    private Main main;

    public Executor(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
            String[] args) {
        if (cmd.getName().equalsIgnoreCase("suicide")
                && sender instanceof Player && args.length == 0) {
            String senderName = sender.getName();
            Player player = Bukkit.getPlayer(senderName);

            for (Player online : Bukkit.getOnlinePlayers()) {
                online.sendMessage(senderName + " committed suicide");
            }

            player.setHealth(0);
            return true;
        } else if (cmd.getName().equalsIgnoreCase("suicide")
                && sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Only players can execute this command!");
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "No parameters after command!");
            return true;
        }
    }
}
