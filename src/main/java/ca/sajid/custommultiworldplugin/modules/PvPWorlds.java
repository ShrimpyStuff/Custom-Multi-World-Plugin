package ca.sajid.custommultiworldplugin.modules;

import ca.sajid.custommultiworldplugin.util.BaseModule;
import ca.sajid.custommultiworldplugin.util.CustomConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PvPWorlds extends BaseModule implements Listener {

    private static final CustomConfig config = new CustomConfig("PvPConfig.yml");

    @Override
    public void onEnable() {
        getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        config.reload();
        if (e.getDamager() instanceof Player) {
            if (e.getEntity() instanceof Player) {
                if (config.get().getBoolean("pvp_off." + e.getEntity().getWorld().getName())) {
                    e.setCancelled(true);
                }
            }
        }
    }

    public static CustomConfig getPVPWorlds() {
        return config;
    }
}
