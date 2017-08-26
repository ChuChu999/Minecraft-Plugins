package commandBlockFilter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RunCommands implements CommandExecutor {

    @SuppressWarnings("unused")
    private Main main;

    public RunCommands(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
            String[] args) {
        if (cmd.getName().equalsIgnoreCase("cbf")) {
            if (sender instanceof BlockCommandSender) {
                return false;
            } else if (args.length == 0) {
                if (Blacklist.getIsToggled() == false) {
                    Bukkit.broadcastMessage("CommandBlockFinder has been: "
                            + ChatColor.GREEN + "Enabled!");
                    Blacklist.setIsToggled(true);
                    return true;
                } else {
                    Bukkit.broadcastMessage("CommandBlockFinder has been: "
                            + ChatColor.RED + "Disabled!");
                    Blacklist.setIsToggled(false);
                    return true;
                }
                // Too many args
            } else {
                sender.sendMessage("Too many arguments!");
                return false;
            }
        }
        return false;
    }
}
