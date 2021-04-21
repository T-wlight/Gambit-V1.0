package site.levimarvin.dev.gambit.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import site.levimarvin.dev.gambit.cache.Cache;
import site.levimarvin.dev.gambit.event.PlayerPickupMoteEvent;
import site.levimarvin.dev.gambit.manager.GameManager;

public class MotePickListener implements Listener {

    @EventHandler
    void onPick(PlayerPickupMoteEvent e) {
        Player player = e.getPlayer();
        int mote = Cache.getMote(player);

        GameManager.getManager().blueScoreBoard.setScore("number of mote", mote);
    }

}
