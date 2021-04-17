package ca.sajid.custommultiworldplugin.commands;

import ca.sajid.custommultiworldplugin.modules.PvPWorlds;
import ca.sajid.custommultiworldplugin.util.BaseCommand;
import ca.sajid.custommultiworldplugin.util.CustomConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class PvPToggle extends BaseCommand {


    private final CustomConfig config = PvPWorlds.getPVPWorlds();

    public PvPToggle() {
        super("pvpToggle", true);
    }


    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        FileConfiguration pvpConfig = config.get();

        if (!pvpConfig.getBoolean("pvp_on." + player.getWorld().getName())) {
            pvpConfig.set("pvp_on." + player.getWorld().getName(), true);
        } else if (pvpConfig.getBoolean("pvp_on." + player.getWorld().getName())) {
            pvpConfig.set("pvp_on." + player.getWorld().getName(), false);
        }

        config.save();

        return false;
    }
}
