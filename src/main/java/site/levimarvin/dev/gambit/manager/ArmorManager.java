package site.levimarvin.dev.gambit.manager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import site.levimarvin.dev.gambit.Gambit;
import site.levimarvin.dev.gambit.game.GambitClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Levi Marvin
 */
public class ArmorManager {
    private static ArmorManager manager;
    public static ArmorManager getManager() {
        if (manager == null) {
            manager = new ArmorManager();
        }
        return manager;
    }
    /**
     * Occupations' helmets.
     */
    private ItemStack invaderHelmet() {
        ItemStack invader = new ItemStack(Material.GOLD_HELMET);
        ItemMeta meta = invader.getItemMeta();
        meta.setDisplayName("Invader");
        List<String> lore = new ArrayList<>();
        lore.add("Invader");
        meta.setLore(lore);
        invader.setItemMeta(meta);
        return invader;
    }

    private ItemStack collectorHelmet() {
        ItemStack collector = new ItemStack(Material.IRON_HELMET);
        ItemMeta meta = collector.getItemMeta();
        meta.setDisplayName("Collector");
        List<String> lore = new ArrayList<>();
        lore.add("Collector");
        meta.setLore(lore);
        collector.setItemMeta(meta);
        return collector;
    }

    private ItemStack reaperHelmet() {
        ItemStack reaper = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta meta = reaper.getItemMeta();
        meta.setDisplayName("reaper");
        List<String> lore = new ArrayList<>();
        lore.add("reaper");
        meta.setLore(lore);
        reaper.setItemMeta(meta);
        return reaper;
    }

    private ItemStack sentryHelmet() {
        ItemStack sentry = new ItemStack(Material.LEATHER_HELMET);
        ItemMeta meta = sentry.getItemMeta();
        meta.setDisplayName("sentry");
        List<String> lore = new ArrayList<>();
        lore.add("sentry");
        meta.setLore(lore);
        sentry.setItemMeta(meta);
        return sentry;
    }


    /**
    * Other armor.
    */
    public ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
    public ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
    public ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

    /**
     * metas.
     */
    public LeatherArmorMeta chestplateMeta =
            (LeatherArmorMeta) new ItemStack(Material.LEATHER_CHESTPLATE).getItemMeta();
    public LeatherArmorMeta leggingsMeta =
            (LeatherArmorMeta) new ItemStack(Material.LEATHER_LEGGINGS).getItemMeta();
    public LeatherArmorMeta bootsMeta =
            (LeatherArmorMeta) new ItemStack(Material.LEATHER_BOOTS).getItemMeta();
    private boolean initialized = false;

    public void giveArmor(Player player) {
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);
        switch (GambitClass.getGambitClass().getClass(player)) {
            case 1:
                player.getInventory().setHelmet(invaderHelmet());
                return;
            case 2:
                player.getInventory().setHelmet(collectorHelmet());
                return;
            case 3:
                player.getInventory().setHelmet(reaperHelmet());
                return;
            case 4:
                player.getInventory().setHelmet(sentryHelmet());
                return;
            default:
                player.getInventory().setHelmet(invaderHelmet());
                return;
        }

    }

    public void initArmor() {
        Gambit.getPlugin().getServer().getConsoleSender().sendMessage(Gambit.PREFIX + "§a + 初始化装备...");
        preInit();
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);
        initialized = true;
    }

    private void preInit() {
        Bukkit.getConsoleSender().sendMessage(Gambit.PREFIX + "§a |   处理数据...");
        //MetaData
        setArmorMeta(chestplateMeta, true,
                "Chest", "Chest", null);
        setArmorMeta(leggingsMeta, true,
                "Leggings", "Leggings", null);
        setArmorMeta(bootsMeta, true,
                "Boots", "Boots", null);
    }

    /**
     * @param itemMeta LeatherArmorMeta - The changed armor meta.
     * @param unbreakable boolean - true or false.
     * @param displayName String - Display name.
     * @param localName String - Localized Name.
     * @param lore List<String> - The item's lore.
     */
    //@Deprecated
    public void setArmorMeta(LeatherArmorMeta itemMeta, boolean unbreakable,
                             String localName, String displayName, List<String> lore) {
        itemMeta.setUnbreakable(unbreakable);
        itemMeta.setLocalizedName(localName);
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
    }


    /**
     * @param itemMeta LeatherArmorMeta - The changed armor meta.
     * @param unbreakable boolean - true or false.
     * @param displayName String - Display name.
     * @param localName String - Localized Name.
     * @param lore List<String> - The item's lore.
     * @param color Color - The item's color.
     */
    /*
    public void setArmorMeta(LeatherArmorMeta itemMeta, boolean unbreakable,
                             String localName, String displayName, List<String> lore, Color color) {
        itemMeta.setUnbreakable(unbreakable);
        itemMeta.setLocalizedName(localName);
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemMeta.setColor(color);
    }
*/
    /**
     * @param itemMeta LeatherArmorMeta - The changed armor meta.
     * @param unbreakable boolean - true or false.
     * @param displayName String - Display name.
     * @param localName String - Localized Name.
     * @param lore List<String> - The item's lore.
     * @param color Color - The item's color.
     * @param enchantment Enchantment - The item's enchantment.
     * @param enchantmentLevel int - The enchantment's level.
     * @param ignoreLevelRestriction boolean - This indicates the enchantment should be applied,
     *                              ignoring the level limit.
     */
    /*
    public void setArmorMeta(LeatherArmorMeta itemMeta, boolean unbreakable,
                             String localName, String displayName, List<String> lore,
                             Color color,
                             Enchantment enchantment, int enchantmentLevel, boolean ignoreLevelRestriction) {
        itemMeta.setUnbreakable(unbreakable);
        itemMeta.setLocalizedName(localName);
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemMeta.setColor(color);
        itemMeta.addEnchant(enchantment, enchantmentLevel, ignoreLevelRestriction);
    }
*/
}
