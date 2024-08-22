package codes.domino.homesystemcore;

import codes.domino.homesystemapi.HomeSystemAPI;
import codes.domino.homesystemcore.cmds.HomeCommand;
import codes.domino.homesystemcore.cmds.SetHomeCommand;
import codes.domino.homesystemcore.manager.SimpleHomeManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class HomeSystemPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        HomeSystemAPI.setManager(SimpleHomeManager.getInstance());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        SimpleHomeManager.getInstance().loadFromDatabase();

    }

    @Override
    public void onDisable() {
        SimpleHomeManager.getInstance().saveToDatabase();
    }
}
