package joinItem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Join implements Listener {

    @SuppressWarnings("unused")
    private Main main;

    public Join(Main main) {
        this.main = main;
    }

    @EventHandler
    public void giveItem(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack emerald = new ItemStack(Material.EMERALD);
        ItemMeta meta = emerald.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "Game Menu "
                + ChatColor.DARK_GREEN + "[Right Click]");
        emerald.setItemMeta(meta);
        player.getInventory().setItem(0, emerald);
    }
}
