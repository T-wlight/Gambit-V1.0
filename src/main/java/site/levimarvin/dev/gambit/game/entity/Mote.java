package site.levimarvin.dev.gambit.game.entity;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Mote extends ItemStack {
    public static ItemStack Mote(int amount) {
        ItemStack mote = new ItemStack(Material.GLOWSTONE_DUST, amount);
        ItemMeta meta = mote.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lMote"));
        mote.setItemMeta(meta);
        return mote;
    }
}
