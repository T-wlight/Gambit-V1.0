package site.levimarvin.dev.gambit.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import site.levimarvin.dev.gambit.cache.Cache;
import site.levimarvin.dev.gambit.manager.ArmorManager;
import site.levimarvin.dev.gambit.manager.GameManager;
import site.levimarvin.dev.gambit.manager.MenuManager;

public class MenuListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if (e.getWhoClicked().getOpenInventory().getTitle().equals("§l§6Gambit - §a选择你的队伍")) {
            e.setCancelled(true);
            if (e.getRawSlot() < 0 || e.getRawSlot() > e.getInventory().getSize() || e.getInventory() == null) {return;}
            if (e.getRawSlot() == 12){
                p.sendMessage("§a选择了§c红队§a!");
                p.closeInventory();
                GameManager.getManager().addToTeam(p, GameManager.getManager().teamRed);
                GameManager.getManager().getTeam(1).joinTeam(p);
                GameManager.getManager().redScoreBoard.setScoreboard(p);
                p.sendMessage("§aYou have successfully join the RED Team!");
                GameManager.getManager().joinGame(p, "red");
                MenuManager.getManager().openMenu(p, MenuManager.getManager().occupationMenu, 2);
                Cache.addTeam(p, 1);
            } else if (e.getRawSlot() == 14) {
                p.sendMessage("§a选择了§1蓝队§a!");
                p.closeInventory();
                GameManager.getManager().addToTeam(p, GameManager.getManager().teamBlue);
                GameManager.getManager().getTeam(2).joinTeam(p);
                GameManager.getManager().blueScoreBoard.setScoreboard(p);
                p.sendMessage("§aYou have successfully join the BLUE Team!");
                GameManager.getManager().joinGame(p, "blue");
                MenuManager.getManager().openMenu(p, MenuManager.getManager().occupationMenu, 2);
                Cache.addTeam(p, 2);
            } else if (e.getRawSlot() == 26) {
                p.closeInventory();
            } else {
                p.sendMessage("请选择队伍或点击右下角退出!");
            }
        } else if (e.getWhoClicked().getOpenInventory().getTitle().equals("§l§6Gambit - §a选择你的职业")) {
            e.setCancelled(true);
            if (e.getRawSlot() < 0 || e.getRawSlot() > e.getInventory().getSize() || e.getInventory() == null) { return; }
            /**
             * 1: invader
             * 2: collector
             * 3: reaper
             * 4: sentry
             */
            if (e.getSlot() == 11) {
                Cache.put(p, 1);
            } else if (e.getSlot() == 12) {
                Cache.put(p, 2);
            } else if (e.getSlot() == 13) {
                Cache.put(p, 3);
            } else if (e.getSlot() == 14) {
                Cache.put(p, 4);
            }

            if (Cache.getMap().get(p) != null) {
                ArmorManager.getManager().giveArmor(p);
                p.sendMessage("你的职业: " + Cache.getMap().get(p));
            }
        }










    }
}
