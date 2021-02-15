package ca.sajid.custommultiworldplugin.commands;

import ca.sajid.custommultiworldplugin.Utils;
import ca.sajid.custommultiworldplugin.util.BaseCommand;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerTeleport extends BaseCommand {

    public PlayerTeleport() {
        super("wtp", true);
    }

    @Override
    public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
        Server server = getPlugin().getServer();
        if (args.length < 1) {
            sender.sendMessage(Utils.color("&cYou are missing some args, Usage: /wtp [world name | player] (world name)"));
        }
        if (args.length == 1) {
            if (server.getWorld(args[0]) == null) {
                sender.sendMessage(Utils.color("&cThat world doesn't exist!"));
                return true;
            }
            Player player = (Player) sender;
            player.teleport(server.getWorld(args[0]).getSpawnLocation());
        }
        if (args.length > 2) {
            if (server.getWorld(args[1]) == null) {
                sender.sendMessage(Utils.color("&cThat world doesn't exist!"));
                return true;
            }
            if (server.getPlayer(args[0]) == null) {
                sender.sendMessage(Utils.color("&cThat player doesn't exist!"));
                return true;
            }
                Player player = server.getPlayer(args[0]);
                player.teleport(server.getWorld(args[1]).getSpawnLocation());
        }


        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> options = new ArrayList<>();

        Server server = getPlugin().getServer();

        List<World> worlds = server.getWorlds();

        if (args.length == 1) {
            for (World world : worlds) {
                options.add(world.getName());
            }
                for (Player player : server.getOnlinePlayers()) {
                    options.add(player.getName());
                }
        } else if (args.length == 2) {
            for (World world : worlds) {
                options.add(world.getName());
            }
        }

        return options;
    }
}
