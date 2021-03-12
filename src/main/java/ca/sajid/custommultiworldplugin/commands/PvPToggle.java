package ca.sajid.custommultiworldplugin.commands;

import ca.sajid.custommultiworldplugin.util.BaseCommand;
import ca.sajid.custommultiworldplugin.util.CustomConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class PvPToggle extends BaseCommand {

    private static final CustomConfig config = new CustomConfig("PvPConfig.yml");

    public PvPToggle() {
        super("pvp");
    }


    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        FileConfiguration pvpconfig = config.get();

        if (!pvpconfig.getBoolean("pvp_on." + player.getWorld().getName())) {
            pvpconfig.set("pvp_on." + player.getWorld().getName(), true);
        } else if (pvpconfig.getBoolean("PvPOn." + player.getWorld().getName())) {
            pvpconfig.set("pvp_on." + player.getWorld().getName(), false);
        } else {
            pvpconfig.set("pvp_on." + player.getWorld().getName(), true);
        }

        config.save();

        return false;
    }

    public static CustomConfig getPVPWorlds() {
        return config;
    }
}
