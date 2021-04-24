package ca.sajid.custommultiworldplugin.modules;

import ca.sajid.custommultiworldplugin.util.BaseModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnWorld extends BaseModule implements Listener {

    @Override
    public void onEnable() {
        getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
    }

    @EventHandler
    public void PlayerRespawnEvent(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        e.setRespawnLocation(p.getWorld().getSpawnLocation());
    }
}
