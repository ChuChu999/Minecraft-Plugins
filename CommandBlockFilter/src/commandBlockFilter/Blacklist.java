package commandBlockFilter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class Blacklist implements Listener {

    private Main main;
    private static Block block;
    private static Material material;
    private static String cmd;
    private static CommandBlock cb;
    private static String player;
    private static String world;
    private static int x;
    private static int y;
    private static int z;
    private static int time;
    private static int taskID;
    private static boolean isToggled = false;

    public Blacklist(Main main) {
        this.main = main;
    }

    @EventHandler
    public void disableSpecialCommandBlocks(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            block = event.getClickedBlock();
            material = event.getMaterial();
            player = event.getPlayer().getName();
            world = block.getLocation().getWorld().getName();
            x = block.getLocation().getBlockX();
            y = block.getLocation().getBlockY();
            z = block.getLocation().getBlockZ();

            if (block.getType().equals(Material.COMMAND)) {
                if (taskID != 0) {
                    scheduler.cancelTask(taskID);
                }
                time = 0;
                taskID = scheduler.scheduleSyncRepeatingTask(main,
                        new Runnable() {
                            public void run() {
                                // Disables use of special command blocks
                                if (block.getType()
                                        .equals(Material.COMMAND_CHAIN)) {
                                    event.setCancelled(true);
                                    block.setType(Material.AIR);
                                    scheduler.cancelTask(taskID);
                                    Bukkit.broadcastMessage(ChatColor.DARK_RED
                                            + player + ChatColor.RESET
                                            + " attempted use of "
                                            + ChatColor.DARK_GREEN
                                            + "Chain Command Block");
                                    Bukkit.broadcastMessage(
                                            "@ " + ChatColor.YELLOW + world
                                                    + " " + ChatColor.GOLD + x
                                                    + ", " + y + ", " + z);
                                } else if (block.getType()
                                        .equals(Material.COMMAND_MINECART)) {
                                    event.setCancelled(true);
                                    block.setType(Material.AIR);
                                    scheduler.cancelTask(taskID);
                                    Bukkit.broadcastMessage(ChatColor.DARK_RED
                                            + player + ChatColor.RESET
                                            + " attempted use of "
                                            + ChatColor.DARK_GREEN
                                            + "Command Minecart");
                                    Bukkit.broadcastMessage(
                                            "@ " + ChatColor.YELLOW + world
                                                    + " " + ChatColor.GOLD + x
                                                    + ", " + y + ", " + z);
                                } else if (block.getType()
                                        .equals(Material.COMMAND_REPEATING)) {
                                    event.setCancelled(true);
                                    block.setType(Material.AIR);
                                    scheduler.cancelTask(taskID);
                                    Bukkit.broadcastMessage(ChatColor.DARK_RED
                                            + player + ChatColor.RESET
                                            + " attempted use of "
                                            + ChatColor.DARK_GREEN
                                            + "Repeating Command Block");
                                    Bukkit.broadcastMessage(
                                            "@ " + ChatColor.YELLOW + world
                                                    + " " + ChatColor.GOLD + x
                                                    + ", " + y + ", " + z);
                                    // Time limit of 3 minutes
                                } else if (time >= 20 * 60 * 3) {
                                    scheduler.cancelTask(taskID);
                                    // Adds to time every tick
                                } else {
                                    time++;
                                }
                            }
                        }, 0, 1);
                // Disables use through /give
            } else if (block.getType().equals(Material.COMMAND_CHAIN)) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                Bukkit.broadcastMessage(ChatColor.DARK_RED + player
                        + ChatColor.RESET + " attempted use of "
                        + ChatColor.DARK_GREEN + "Chain Command Block");
                Bukkit.broadcastMessage("@ " + ChatColor.YELLOW + world + " "
                        + ChatColor.GOLD + x + ", " + y + ", " + z);
            } else if (material.equals(Material.COMMAND_MINECART)) {
                event.setCancelled(true);
                Bukkit.broadcastMessage(ChatColor.DARK_RED + player
                        + ChatColor.RESET + " attempted use of "
                        + ChatColor.DARK_GREEN + "Command Minecart");
                Bukkit.broadcastMessage("@ " + ChatColor.YELLOW + world + " "
                        + ChatColor.GOLD + x + ", " + y + ", " + z);
            } else if (block.getType().equals(Material.COMMAND_REPEATING)) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                Bukkit.broadcastMessage(ChatColor.DARK_RED + player
                        + ChatColor.RESET + " attempted use of "
                        + ChatColor.DARK_GREEN + "Repeating Command Block");
                Bukkit.broadcastMessage("@ " + ChatColor.YELLOW + world + " "
                        + ChatColor.GOLD + x + ", " + y + ", " + z);
            }
        }
    }

    @EventHandler
    public void commandBlockEvent(BlockRedstoneEvent event) {
        block = event.getBlock();
        world = block.getLocation().getWorld().getName();
        x = block.getLocation().getBlockX();
        y = block.getLocation().getBlockY();
        z = block.getLocation().getBlockZ();

        if (block.getType().equals(Material.COMMAND)) {
            cb = (CommandBlock) block.getState();
            cmd = cb.getCommand().toLowerCase();
            checkBlacklist();
            broadcastCommands();
        }
    }

    public void checkBlacklist() {
        if (cmd.contains("kill")) {
            block.setType(Material.AIR);
            Bukkit.broadcastMessage("Attempted use of " + ChatColor.DARK_GREEN
                    + "kill " + ChatColor.RESET + "command");
            Bukkit.broadcastMessage("@ " + ChatColor.YELLOW + world + " "
                    + ChatColor.GOLD + x + ", " + y + ", " + z);
        } else if (cmd.contains("kick")) {
            block.setType(Material.AIR);
            Bukkit.broadcastMessage("Attempted use of " + ChatColor.DARK_GREEN
                    + "kick " + ChatColor.RESET + "command");
            Bukkit.broadcastMessage("@ " + ChatColor.YELLOW + world + " "
                    + ChatColor.GOLD + x + ", " + y + ", " + z);
        } else if (cmd.contains("ban")) {
            block.setType(Material.AIR);
            Bukkit.broadcastMessage("Attempted use of " + ChatColor.DARK_GREEN
                    + "ban " + ChatColor.RESET + "command");
            Bukkit.broadcastMessage("@ " + ChatColor.YELLOW + world + " "
                    + ChatColor.GOLD + x + ", " + y + ", " + z);
        } else if (cmd.contains("nuke")) {
            block.setType(Material.AIR);
            Bukkit.broadcastMessage("attempted use of " + ChatColor.DARK_GREEN
                    + "nuke " + ChatColor.RESET + "command");
            Bukkit.broadcastMessage("@ " + ChatColor.YELLOW + world + " "
                    + ChatColor.GOLD + x + ", " + y + ", " + z);
        } else if (cmd.contains("pex") || cmd.contains("promote")) {
            block.setType(Material.AIR);
            Bukkit.broadcastMessage("Attempted use of " + ChatColor.DARK_GREEN
                    + "promote " + ChatColor.RESET + "command");
            Bukkit.broadcastMessage("@ " + ChatColor.YELLOW + world + " "
                    + ChatColor.GOLD + x + ", " + y + ", " + z);
        } else if (cmd.contains("randomtickspeed")) {
            block.setType(Material.AIR);
            Bukkit.broadcastMessage("Attempted use of " + ChatColor.DARK_GREEN
                    + "randomTickSpeed " + ChatColor.RESET + "command");
            Bukkit.broadcastMessage("@ " + ChatColor.YELLOW + world + " "
                    + ChatColor.GOLD + x + ", " + y + ", " + z);
        }
    }

    public static void broadcastCommands() {
        if (getIsToggled() == true) {
            Bukkit.broadcastMessage("Command: " + ChatColor.DARK_GREEN + cmd);
            Bukkit.broadcastMessage("@ " + ChatColor.YELLOW + world + " "
                    + ChatColor.GOLD + x + ", " + y + ", " + z);
        }
    }

    public static boolean getIsToggled() {
        return isToggled;
    }

    public static void setIsToggled(boolean isToggled) {
        Blacklist.isToggled = isToggled;
    }
}
