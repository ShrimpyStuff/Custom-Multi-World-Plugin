package ca.sajid.custommultiworldplugin.modules;

import ca.sajid.custommultiworldplugin.commands.PvPToggle;
import ca.sajid.custommultiworldplugin.util.BaseModule;
import ca.sajid.custommultiworldplugin.util.CustomConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PvPWorlds extends BaseModule implements Listener {

    public static CustomConfig pvpconfig = PvPToggle.getPVPWorlds();

    @Override
    public void onEnable() {
        getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player) && !(e.getEntity() instanceof Player)) return;
        if (!(pvpconfig.get().getBoolean("pvp_on." + e.getEntity().getWorld().getName()))) {
            e.setCancelled(true);
        }
    }
}
