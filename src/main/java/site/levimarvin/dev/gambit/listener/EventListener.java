package site.levimarvin.dev.gambit.listener;

import me.McVier3ck.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import site.levimarvin.dev.gambit.Gambit;
import site.levimarvin.dev.gambit.cache.Cache;
import site.levimarvin.dev.gambit.cache.PlayerCache;
import site.levimarvin.dev.gambit.event.PlayerInvadeEvent;
import site.levimarvin.dev.gambit.event.PlayerPickupMoteEvent;
import site.levimarvin.dev.gambit.game.GambitClass;
import site.levimarvin.dev.gambit.game.entity.Mote;
import site.levimarvin.dev.gambit.manager.GameManager;
import site.levimarvin.dev.gambit.manager.MathManager;

/**
 * @author ziheng
 */
public class EventListener implements Listener {

    @EventHandler
    void onPickUpEvent(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getItem().getItemStack().isSimilar(Mote.Mote(1))) {
                PlayerPickupMoteEvent ev = new PlayerPickupMoteEvent(
                        (Player) e.getEntity(),
                        GameManager.getManager().getTeam((Player)e.getEntity()),
                        e.getItem().getItemStack().getAmount());
                Bukkit.getPluginManager().callEvent(ev);
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    void onPickMoteEvent(PlayerPickupMoteEvent e) {
        int oldMote = MathManager.calculateMote(e.getTeam());
        Cache.getMoteMap().put(e.getPlayer(), e.getAmount());
        int mote = MathManager.calculateMote(e.getTeam());
        if (mote - oldMote >= 25 && mote <= 100) {
            // active invade
            GameManager.getManager().activeInvadeGate(e.getTeam());
        }
    }

    @EventHandler
    void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            GameManager.getManager().saveMote(e.getPlayer(), e.getClickedBlock().getLocation(), e.getPlayer().getInventory().getItemInMainHand().getAmount());
        }
        if (GameManager.getManager().invadeFlag) {
            if (e.getClickedBlock().getType().equals(Material.DIAMOND_BLOCK)) {
                // tp player to opponents side
                e.getPlayer().teleport(GameManager.getManager().getTeamBeInvaded(e.getPlayer()).getSpawn());
                //call PlayerInvadeEvent
                Bukkit.getPluginManager().callEvent(new PlayerInvadeEvent(
                        e.getPlayer(),
                        GameManager.getManager().getTeamBeInvaded(e.getPlayer())
                ));
                PlayerCache.setInvader(e.getPlayer());
            } else { return; }
        }
    }

    @EventHandler
    void onInvadeEvent(PlayerInvadeEvent e) {
        for (Player player : e.getTeamBeInvaded().getPlayers()) {
            player.sendTitle("You have been invaded!", "find the invader and kill him", 10, 30, 10);
        }

        if (GambitClass.getGambitClass().getClass(e.getInvader()) == 1) {
            if (e.getTeamBeInvaded().equals(GameManager.getManager().teamBlue)) {
                GameManager.getManager().blueSave = false;
            } else {
                GameManager.getManager().redSave = false;
            }
            for (Player player : e.getTeamBeInvaded().getPlayers()) {
                player.sendMessage("Mote Bank has been locked down!");
            }
        }

    }

    @EventHandler
    void onKillPlayerEvent(PlayerDeathEvent e) {
        int invaderTeamId = Cache.getTeamMap().get(PlayerCache.getInvader());
        int death = Cache.getTeamMap().get(e.getEntity());

        if (PlayerCache.getKillMap().get(PlayerCache.getInvader()) >= 4) {
            //tp invader back to their side.
            Player invader = PlayerCache.getInvader();
            Team team = GameManager.getManager().getTeam(Cache.getTeamMap().get(invader));
            invader.teleport(team.getSpawn());
        } else if (invaderTeamId != death && PlayerCache.getInvader() != null) {
            PlayerCache.addKill(PlayerCache.getInvader());
            GameManager.getManager().sendTeamMessage(GameManager.getManager().getTeam(death), "&a" + e.getEntity().getDisplayName() + "&bhas been killed");
        }

    }

}
