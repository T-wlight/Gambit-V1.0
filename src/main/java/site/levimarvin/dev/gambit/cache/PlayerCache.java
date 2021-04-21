package site.levimarvin.dev.gambit.cache;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerCache {

    private static Map<Player, Integer> killMap = new HashMap<>();
    private static Player invader;

    public static void setInvader(Player player) {
        invader = player;
    }

    public static Player getInvader() {
        return invader;
    }

    public static Map<Player, Integer> getKillMap() {
        return killMap;
    }

    public static void addKill(Player player) {
        if (killMap.get(player) == null) {
            killMap.put(player, 1);
        }
        int old = killMap.get(player);
        killMap.replace(player, old++);
    }


}
