package site.levimarvin.dev.gambit.event;

import me.McVier3ck.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import site.levimarvin.dev.gambit.game.entity.Mote;

public class PlayerPickupMoteEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Team team;
    private boolean cancelledFlag = false;
    private int amount;

    public PlayerPickupMoteEvent(Player player, Team team, int amount) {
        super(player);
        this.team = team;
        this.amount = amount;
    }

    public Team getTeam() {
        return this.team;
    }

    public int getAmount() {
        return this.amount;
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
