package mcCoD;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;

public class Quit implements Listener {

    @SuppressWarnings("unused")
    private Main main;

    public Quit(Main main) {
        this.main = main;
    }

    @EventHandler
    public void stopTimer(PlayerQuitEvent event) {
        Scoreboard board = RunTimer.getBoard();
        if (Bukkit.getOnlinePlayers().size() <= 1
                && RunTimer.isTimerStarted()) {
            board.resetScores(ChatColor.GREEN + "Time Left:");
            RunTimer.setTimerStarted(false);
        }
    }
}
