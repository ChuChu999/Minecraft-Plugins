package funWithCommands.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import funWithCommands.Main;

public class Executor implements CommandExecutor {

    @SuppressWarnings("unused")
    private Main main;
    private Welcome welcome = new Welcome();
    private Hello hello = new Hello();

    public Executor(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label,
            String[] args) {
        if (cmd.getName().equalsIgnoreCase("test")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("welcome")) {
                    return welcome.welcome(sender, cmd, label, args);
                } else if (args[0].equalsIgnoreCase("hello")) {
                    return hello.hello(sender, cmd, label, args);
                } else {
                    sender.sendMessage(
                            ChatColor.RED + "That is an unknown command!");
                    return false;
                }
            }
        }
        return false;
    }
}
