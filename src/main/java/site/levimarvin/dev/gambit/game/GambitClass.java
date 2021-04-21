package site.levimarvin.dev.gambit.game;

import org.bukkit.entity.Player;
import site.levimarvin.dev.gambit.cache.Cache;
import site.levimarvin.dev.gambit.manager.GameManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Levi Marvin
 */
public class GambitClass {
    private static GambitClass instance;
    private List<String> list = new ArrayList<>();

    public static GambitClass getGambitClass() {
        if (instance == null) {
            instance = new GambitClass();
        }
        return instance;
    }

    public int occupation;

    public int getClass(Player player) {
        if (GameManager.getManager().playerInGame) {
            occupation = Cache.getMap().get(player);
            //Invader
            if (occupation == 1) {
                return 1;
            }
            //Collector
            if (occupation == 2) {
                return 2;
            }
            //Reaper
            if (occupation == 3) {
                return 3;
            }
            //Sentry
            if (occupation == 4) {
                return 4;
            }
        }
        return 2;
    }

    public String getClass(int i) {
        if (i == 1) {
            return "Invader";
        } else if (i == 2) {
            return "Collector";
        } else if (i == 3) {
            return "Reaper";
        } else {
            return "Sentry";
        }
    }
}
