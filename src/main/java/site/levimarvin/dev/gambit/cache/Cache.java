package site.levimarvin.dev.gambit.cache;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    /**
     * player, class
     */
    private static Map<Player, Integer> map = new HashMap<>();

    /**
     * player, number of mote
     */
    private static Map<Player, Integer> moteMap = new HashMap<>();
    /**
     * player, team ID
     */
    private static Map<Player, Integer> teamMap = new HashMap<>();

    public static Map<Player, Integer> getMap() {
        return map;
    }

    public static Map<Player, Integer> getMoteMap() {
        return moteMap;
    }

    /**
     * save mote
     *
     * @param player
     * @param mote
     */
    public static void save(Player player, int mote) {
        if (moteMap.get(player) != null) {
            mote += moteMap.get(player);
            moteMap.remove(player);
        }
        moteMap.put(player, mote);
    }

    /**
     * get players mote in cache map. if player dont have mote, return 0
     *
     * @param player
     * @return if player dont have mote, return 0
     */
    public static int getMote(Player player) {
        if (moteMap.get(player) == null) {
            return 0;
        }
        return moteMap.get(player);
    }

    public static void put(Player player, int i) {
        if (map.get(player) != null) {
            map.remove(player);
            map.put(player, i);
        }
        map.put(player, i);
    }

    /**
     * put the player into the map. 1 is red, 2 is blue
     *
     * @param player
     * @param id
     */
    public static void addTeam(Player player, int id) {
        if (teamMap.get(player) != null) {
            teamMap.remove(player);
            teamMap.put(player, id);
        }
        teamMap.put(player, id);
    }

    public static Map<Player, Integer> getTeamMap() {
        return teamMap;
    }

}
