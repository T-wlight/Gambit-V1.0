package site.levimarvin.dev.gambit.manager;

import me.McVier3ck.arena.Arena;
import me.McVier3ck.scoreboard.CustomScoreboard;
import me.McVier3ck.team.Team;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_12_R1.BlockLever;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import site.levimarvin.dev.gambit.Gambit;
import site.levimarvin.dev.gambit.cache.Cache;
import site.levimarvin.dev.gambit.game.entity.Mote;

import java.util.HashMap;

/**
 * @author Levi Marvin
 */
public class GameManager {
    private static GameManager manager;
    public static GameManager getManager() {
        if (manager == null) {
            manager = new GameManager();
        }
        return manager;
    }

    public final static String RED = "red";
    public final static String BLUE = "blue";

    public Arena arena;
    public Team teamRed;
    public Team teamBlue;
    public CustomScoreboard redScoreBoard;
    public CustomScoreboard blueScoreBoard;
    public CustomScoreboard defaultScoreBoard;


    public HashMap<Team, Integer> teamRedMoteNumber = new HashMap<>();
    public HashMap<Team, Integer> teamBlueMoteNumber = new HashMap<>();
    public boolean playerInGame;
    public boolean invaderExist;
    public boolean collectorExist;
    public boolean sentryExist;

    public boolean invadeFlag = false;
    public boolean redSave = true;
    public boolean blueSave = true;

    //public GameManager () {}

    public Arena getArena() {
        return this.arena;
    }

    /**
     * @param id int, "1" is red team; "2" is blue team.
     * @return Team
     */
    public Team getTeam(int id) {
        switch (id) {
            case 1:
                return this.teamRed;
            case 2:
                return this.teamBlue;
            default:
                return null;
        }
    }

    public void initGame() {
        Gambit.getPlugin().getServer().getConsoleSender().sendMessage(Gambit.PREFIX + "§a + 初始化游戏...");
        //Init team
        Bukkit.getConsoleSender().sendMessage(Gambit.PREFIX + "§a |   注册队伍：");
        Bukkit.getConsoleSender().sendMessage(Gambit.PREFIX + "§a |     注册红队...");
        teamRed = new Team("红队");
        teamRed.setMinSize(4);
        teamRed.setMaxSize(4);
        teamRed.setColor(ChatColor.RED);
        teamRed.setSpawn(Gambit.getPlugin().teamRedLocation);
        Bukkit.getConsoleSender().sendMessage(Gambit.PREFIX + "§a |     注册蓝队...");
        teamBlue = new Team("蓝队");
        teamBlue.setMinSize(4);
        teamBlue.setMaxSize(4);
        teamBlue.setColor(ChatColor.BLUE);
        teamBlue.setSpawn(Gambit.getPlugin().teamBlueLocation);
        //Init Scoreboard
        Gambit.getPlugin().getServer().getConsoleSender().sendMessage(Gambit.PREFIX + "§a + 初始化计分板...");
        defaultScoreBoard = new CustomScoreboard("default", DisplaySlot.SIDEBAR);
        initScoreBoard();
    }

    /**
     * init score board
     */
    public void initScoreBoard() {
        redScoreBoard = new CustomScoreboard("计分板 - §c红队", DisplaySlot.SIDEBAR);
        redScoreBoard.setScore("number of mote", 0);
        redScoreBoard.setScoreboard(teamRed);
        blueScoreBoard = new CustomScoreboard("计分板 - §1蓝队", DisplaySlot.SIDEBAR);
        blueScoreBoard.setScore("number of mote", 0);
        blueScoreBoard.setScore("total motes of team", 0);
        blueScoreBoard.setScoreboard(teamBlue);
    }

    public void joinGame(Player player, String team) {
        playerInGame = true;
        if (RED.equals(team)) {
            //TODO teleport
            player.teleport(teamRed.getSpawn());
            //open menu
            MenuManager.getManager().openMenu(player, MenuManager.getManager().occupationMenu, 2);
        } else if (BLUE.equals(team)){
            //TODO teleport
            player.teleport(teamBlue.getSpawn());
            //open menu
            MenuManager.getManager().openMenu(player, MenuManager.getManager().occupationMenu, 2);
        }
    }

    public void addToTeam(Player player, Team team) {
        team.joinTeam(player);
    }

    public void sendTeamMessage(Team team, String message) {
        for (Player player : team.getPlayers()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
    /**
     * check if the block is Mote bank or not and save
     */
    public void saveMote(Player player, Location location, int moteAmount) {
        int id = Cache.getTeamMap().get(player);
        int mote = Cache.getMote(player);

        if (id == 1) {
            if (Gambit.getPlugin().redMoteBank.equals(location)) {
                if (player.getInventory().getItemInMainHand().isSimilar(Mote.Mote(1))) {
                    // save mote
                    mote += moteAmount;
                    Cache.getMoteMap().put(player, mote);
                    Gambit.getPlugin().redTeamMote = MathManager.calculateMote(teamRed);
                    player.getInventory().remove(player.getInventory().getItemInMainHand());
                } else { return; }
            } else { return; }
        } else if (id == 2) {
            if (Gambit.getPlugin().blueMoteBank.equals(location)) {
                if (player.getInventory().getItemInMainHand().isSimilar(Mote.Mote(1))) {
                    // save mote
                    mote += moteAmount;
                    Cache.getMoteMap().put(player, mote);
                    Gambit.getPlugin().blueTeamMote = MathManager.calculateMote(teamBlue);
                    player.getInventory().remove(player.getInventory().getItemInMainHand());
                } else { return; }
            } else { return; }
        }
    }

    public Team getTeam(Player player) {
        int id = Cache.getTeamMap().get(player);
        if (id == 1) {
            return teamRed;
        } else if (id == 2) {
            return teamBlue;
        }
        return null;
    }

    /**
     * get Team's number, 1 is red, 2 is blue
     *
     * @param team
     * @return
     */
    public int getTeam(Team team) {
        if (team.equals(teamRed)) {
            return 1;
        }
        return 2;
    }

    public Team getTeamBeInvaded(Player player) {
        return getTeam(Cache.getTeamMap().get(player)).equals(teamBlue) ? teamRed : teamBlue;
    }


    /**
     * when team's mote is enough, active the invade gate
     * @param team
     */
    public void activeInvadeGate(Team team) {
        invadeFlag = true;
        int id = getTeam(team);
        Location invadeGate = id == 1 ? Gambit.getPlugin().redInvade : Gambit.getPlugin().blueInvade;

        invadeGate.getBlock().setType(Material.DIAMOND_BLOCK);

        for (Player player : team.getPlayers()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aInvade Gate has opened!"));
        }

    }



}
