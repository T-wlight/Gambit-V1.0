package site.levimarvin.dev.gambit.event;

import me.McVier3ck.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerInvadeEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private Player invader;
    private Team teamBeInvaded;
    private boolean cancelledFlag = false;


    public PlayerInvadeEvent(Player invader, Team teamBeInvaded) {
        super(invader);
        this.teamBeInvaded = teamBeInvaded;
    }

    public Player getInvader() {
        return invader;
    }

    public Team getTeamBeInvaded() {
        return this.teamBeInvaded;
    }


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelledFlag;
    }

    @Override
    public void setCancelled(boolean cancelledFlag) {
        this.cancelledFlag = cancelledFlag;
    }

}
