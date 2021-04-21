package site.levimarvin.dev.gambit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import site.levimarvin.dev.gambit.manager.MenuManager;

/**
 * @author Levi Marvin
 */
public class Menu implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only player can execute this!");
        }
        assert sender instanceof Player;
        Player player = (Player) sender;
        MenuManager.getManager().openMenu(player, MenuManager.getManager().teamMenu, 1);
        return true;
    }
}