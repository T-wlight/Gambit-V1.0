package site.levimarvin.dev.gambit.manager;

import me.McVier3ck.team.Team;
import org.bukkit.entity.Player;
import site.levimarvin.dev.gambit.cache.Cache;

public class MathManager {

    public static int calculateMote(Team team) {
        int mote = 0;

        for (Player player : team.getPlayers()) {
            mote += Cache.getMote(player);
        }
        return mote;
    }

    public static boolean checkMote(Team team, int mote) {
        int previous = Cache.getMoteMap().get(team);

        if (previous + mote >= 25 || previous + mote >= 50) {}
        return false;
    }

}
