package codes.domino.homesystemcore.cmds;

import codes.domino.homesystemcore.manager.SimpleHomeManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    private static final String HOME_PERMISSION = "homesystem.home";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission(HOME_PERMISSION)) return true;
        Location homeLocation = SimpleHomeManager.getInstance().getHomeLocation(player.getUniqueId());
        if (homeLocation == null) {
            player.sendMessage(ChatColor.RED + "You do not have a home assigned.");
            return true;
        }
        player.teleport(homeLocation);
        player.sendMessage(ChatColor.GREEN + "Teleported to home.");
        return true;
    }
}
