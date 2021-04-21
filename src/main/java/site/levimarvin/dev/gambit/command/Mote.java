package site.levimarvin.dev.gambit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import site.levimarvin.dev.gambit.manager.GameManager;

import java.util.Arrays;

public class Mote implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only player can execute this!");
        }
        assert sender instanceof Player;
        Player player = (Player) sender;

        player.getInventory().addItem(site.levimarvin.dev.gambit.game.entity.Mote.Mote(1));
        return true;
    }

}
