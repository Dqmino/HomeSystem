package codes.domino.homesystemcore.cmds;

import codes.domino.homesystemcore.manager.SimpleHomeManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {
    private static final String SET_HOME_PERMISSION = "homesystem.sethome";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission(SET_HOME_PERMISSION)) return true;
        SimpleHomeManager.getInstance().setHomeLocation(player.getUniqueId(), player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Location has been set as your home, run /home to teleport.");
        return true;
    }
}
