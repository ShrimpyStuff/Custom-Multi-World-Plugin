package ca.sajid.custommultiworldplugin.util;

import ca.sajid.custommultiworldplugin.CustomMultiWorldPlugin;
import ca.sajid.custommultiworldplugin.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCommand implements CommandExecutor, TabCompleter {

    private final CustomMultiWorldPlugin plugin = CustomMultiWorldPlugin.getPlugin();
    private final String name;
    private final boolean playerOnly;

    protected BaseCommand(String name) {
        this(name, false);
    }

    protected BaseCommand(String name, boolean playerOnly) {
        this.name = name;
        this.playerOnly = playerOnly;
    }

    @Override
    public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (playerOnly && !(sender instanceof Player)) {
            sender.sendMessage(Utils.color("&cYou must be a player to use this command"));
            return true;
        }

        return execute(sender, command, label, args);
    }

    public abstract boolean execute(CommandSender sender, Command command, String label, String[] args);

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }

    public void register() {
        PluginCommand command = plugin.getCommand(name);
        if (command != null) command.setExecutor(this);
    }

    public String getName() {
        return name;
    }

    public boolean isPlayerOnly() {
        return playerOnly;
    }

    public CustomMultiWorldPlugin getPlugin() {
        return plugin;
    }
}
