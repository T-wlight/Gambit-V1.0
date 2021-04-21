package site.levimarvin.dev.gambit.manager;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Levi Marvin
 */
public class MenuManager {
    private static MenuManager manager;
    public static MenuManager getManager() {
        if (manager == null) {
            manager = new MenuManager();
        }
        return manager;
    }

    Player player;
    public Inventory teamMenu = Bukkit.createInventory(player, 3*9, "§l§6Gambit - §a选择你的队伍");
    public Inventory occupationMenu = Bukkit.createInventory(player, 3*9, "§l§6Gambit - §a选择你的职业");
    ItemStack item_fence = new ItemStack(Material.IRON_FENCE);
    ItemStack item_redTeam = new Wool(DyeColor.RED).toItemStack(1);
    ItemStack item_blueTeam = new Wool(DyeColor.BLUE).toItemStack(1);



    private ItemStack item_invader() {
        ItemStack invader = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = invader.getItemMeta();
        meta.setDisplayName("Invader");
        List<String> lore = new ArrayList<>();
        lore.add("choose to be invader");
        meta.setLore(lore);
        invader.setItemMeta(meta);
        return invader;
    }

    private ItemStack item_collector() {
        ItemStack collector = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = collector.getItemMeta();
        meta.setDisplayName("Collector");
        List<String> lore = new ArrayList<>();
        lore.add("choose to be Collector");
        meta.setLore(lore);
        collector.setItemMeta(meta);
        return collector;
    }

    private ItemStack item_reaper() {
        ItemStack reaper = new ItemStack(Material.REDSTONE);
        ItemMeta meta = reaper.getItemMeta();
        meta.setDisplayName("reaper");
        List<String> lore = new ArrayList<>();
        lore.add("choose to be reaper");
        meta.setLore(lore);
        reaper.setItemMeta(meta);
        return reaper;
    }

    private ItemStack item_sentry() {
        ItemStack sentry = new ItemStack(Material.DIAMOND);
        ItemMeta meta = sentry.getItemMeta();
        meta.setDisplayName("sentry");
        List<String> lore = new ArrayList<>();
        lore.add("choose to be sentry");
        meta.setLore(lore);
        sentry.setItemMeta(meta);
        return sentry;
    }


    /**
     * open menu
     *
     * @param id 1: team select, 2: class select
     */
    private void loadMenu(int id) {
        initMeta();
        ArmorManager.getManager().initArmor();
        if (id == 1) {
            for (int i = 0; i < 10; i++) {
                teamMenu.setItem(i, item_fence);
            }
            teamMenu.setItem(12, item_redTeam);
            teamMenu.setItem(14, item_blueTeam);
            for (int i = 17; i < 26; i++) {
                teamMenu.setItem(i, item_fence);
            }
        } else if (id == 2) {
            for (int i = 0; i < 10; i++) {
                occupationMenu.setItem(i, item_fence);
            }
            occupationMenu.setItem(11, item_invader());
            occupationMenu.setItem(12, item_collector());
            occupationMenu.setItem(13, item_reaper());
            occupationMenu.setItem(14, item_sentry());
            for (int i = 17; i < 26; i++) {
                occupationMenu.setItem(i, item_fence);
            }
        }
    }

    public void initMeta() {
        //init fence
        ItemMeta fenceMeta = item_fence.getItemMeta();
        ItemMeta item_redTeamMeta = item_redTeam.getItemMeta();
        ItemMeta item_blueTeamMeta = item_blueTeam.getItemMeta();
        setMeta(fenceMeta, "bruhhhhhhhhhh");
        setMeta(item_redTeamMeta, "RED");
        setMeta(item_blueTeamMeta, "BLLUE");
        item_fence.setItemMeta(fenceMeta);
        item_redTeam.setItemMeta(item_redTeamMeta);
        item_blueTeam.setItemMeta(item_blueTeamMeta);
    }

    public void setMeta(ItemMeta itemMeta, String displayName) {
        itemMeta.setDisplayName(displayName);
    }

    public void openMenu(Player player, Inventory menu, int i) {
        loadMenu(i);
        player.closeInventory();
        player.openInventory(menu);
    }
}
