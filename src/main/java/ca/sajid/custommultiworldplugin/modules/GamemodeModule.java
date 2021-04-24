package ca.sajid.custommultiworldplugin.modules;

import ca.sajid.custommultiworldplugin.util.BaseModule;
import ca.sajid.custommultiworldplugin.util.CustomConfig;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class GamemodeModule extends BaseModule implements Listener {
    private static final CustomConfig config = new CustomConfig("WorldGameModes.yml");

    @Override
    public void onEnable() {
        getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        if (e.getPlayer().isOp()) return;
        e.getPlayer().setGameMode(GameMode.valueOf(config.get().getString("gamemode." + e.getPlayer().getWorld().getName()).toUpperCase()));

    }

    public static CustomConfig getWorldGamemode() {
        return config;
    }
}
