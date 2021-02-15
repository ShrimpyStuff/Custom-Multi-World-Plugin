package ca.sajid.custommultiworldplugin;

import ca.sajid.custommultiworldplugin.commands.CreateWorld;
import ca.sajid.custommultiworldplugin.commands.DeleteWorld;
import ca.sajid.custommultiworldplugin.commands.PlayerTeleport;
import ca.sajid.custommultiworldplugin.modules.RespawnWorld;
import org.bukkit.plugin.java.JavaPlugin;

import ca.sajid.custommultiworldplugin.util.ModuleManager;

public final class CustomMultiWorldPlugin extends JavaPlugin {

    private static CustomMultiWorldPlugin plugin;
    private static final ModuleManager modules = new ModuleManager();

    @Override
    public void onEnable() {
        plugin = this;

        new CreateWorld().register();
        new DeleteWorld().register();
        new PlayerTeleport().register();
        
        modules.load(RespawnWorld.class);

        Utils.log("&a%s v%s enabled!", getName(), getDescription().getVersion());
    }

    @Override
    public void onDisable() {
        modules.disable();
    }

    public static CustomMultiWorldPlugin getPlugin() {
        return plugin;
    }
}
