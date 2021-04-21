package site.levimarvin.dev.gambit;

import me.McVier3ck.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import site.levimarvin.dev.gambit.command.*;
import site.levimarvin.dev.gambit.listener.ArmorListener;
import site.levimarvin.dev.gambit.listener.EventListener;
import site.levimarvin.dev.gambit.listener.MenuListener;
import site.levimarvin.dev.gambit.manager.ArmorManager;
import site.levimarvin.dev.gambit.manager.GameManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @author Levi Marvin
 * @apiNote Name: Minigame-Api
 *          Version: 3.3.2
 */
public final class Gambit extends JavaPlugin {
    private static Gambit plugin;
    public static Gambit getPlugin() {
        return plugin;
    }
    public final static String PREFIX = "§e[§6Gambit§e]§f ";
    public final static String VERSION = "1.0-SNAPSHOT";

    public Config config;
    public Location arenaCenter;
    public Location teamRedLocation;
    public Location teamBlueLocation;
    public Location redMoteBank;
    public Location blueMoteBank;
    public Location redInvade;
    public Location blueInvade;

    public int redTeamMote = 0;
    public int blueTeamMote = 0;


    HashMap<String, Class<?>> cmdMap = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;
        try {
            initConfig();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        initCommand();
        GameManager.getManager().initGame();
        initListener();
        ArmorManager.getManager().initArmor();
    }

    private void initListener() {
        Bukkit.getPluginManager().registerEvents(new ArmorListener(), this);
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
    }

    private void initConfig() throws IOException, InvalidConfigurationException {
        config = new Config("config", "Gambit");
        File configFile = new File(getDataFolder() + "/config.yml");
        if (!configFile.exists()) {
            saveDefaultConfig();
        }
        config.getConfig().load(configFile);
        arenaCenter = config.getLocation("arenaCenter", true);
        teamRedLocation = config.getLocation("teamRed", true);
        teamBlueLocation = config.getLocation("teamBlue", true);
        redMoteBank = config.getLocation("redMoteBank", true);
        blueMoteBank = config.getLocation("blueMoteBank", true);
        redInvade = config.getLocation("redInvade", true);
        blueInvade = config.getLocation("blueInvade", true);
    }

    private void initCommand() {
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§a + 初始化命令...");
        cmdMap.put("debug", Debug.class);
        cmdMap.put("menu", Menu.class);
        cmdMap.put("leave", Leave.class);
        cmdMap.put("join", Join.class);
        cmdMap.put("mote", Mote.class);
        cmdMap.forEach((k, v) -> {
            CommandExecutor commandExecutor = null;
            try {
                commandExecutor = (CommandExecutor) v.getDeclaredConstructor().newInstance();
            } catch (
                    InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e
            ) {
                e.printStackTrace();
            }
            Bukkit.getConsoleSender().sendMessage(PREFIX + "§a |   注册命令：" + k);
            Bukkit.getPluginCommand(k + "").setExecutor(commandExecutor);
        });
    }


    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§aClosing...");
    }
}
