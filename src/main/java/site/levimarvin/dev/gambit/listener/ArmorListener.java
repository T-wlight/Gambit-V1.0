package site.levimarvin.dev.gambit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.inventory.ItemStack;
import site.levimarvin.dev.gambit.manager.GameManager;

/**
 * @author Levi Marvin
 */
public class ArmorListener implements Listener {
    public static ItemStack armor;
    @EventHandler
    public void onPlayerWear(PlayerArmorStandManipulateEvent e) {
        if (GameManager.getManager().playerInGame) {

        }
    }
}