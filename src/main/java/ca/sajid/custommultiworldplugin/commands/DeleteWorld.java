package ca.sajid.custommultiworldplugin.commands;

import ca.sajid.custommultiworldplugin.Utils;
import ca.sajid.custommultiworldplugin.util.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class DeleteWorld extends BaseCommand {

    public DeleteWorld() {
        super("deleteworld");
    }

    private void deleteRecursively(File dir) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                deleteRecursively(file);
            }

            file.delete();
        }
    }

    @Override
    public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
        Server server = getPlugin().getServer();
        if (server.getWorld(args[0]) == null) {
            return false;
        }

        World world = server.getWorld(args[0]);
        if (world == null) {
            sender.sendMessage(Utils.color("&cThat world doesn't exist!"));
            return true;
        }

        List<Player> players = world.getPlayers();

        if (!players.isEmpty()) {
            for (Player player : players) {
                player.teleport(server.getWorlds().get(0).getSpawnLocation());
                player.sendTitle("World " + args[0], "is getting deleted.", 5, 100, 5);
            }
        }

        File serverFolder = server.getWorldContainer();
        String name = world.getName();

        try {
            File f = new File(serverFolder, "server.properties");
            FileInputStream in = new FileInputStream(f);

            Properties props = new Properties();
            props.load(in);
            String levelName = props.getProperty("level-name");

            if (name.equals(levelName)) {
                sender.sendMessage(Utils.color("&cYou can't delete the default world!"));
                return true;
            }
        } catch (IOException ignored) {
            // eh
        }

        server.unloadWorld(args[0], false);
        deleteRecursively(new File(serverFolder, name));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> options = new ArrayList<>();

        List<World> worlds = Bukkit.getWorlds();


        for (World world : worlds) {
            options.add(world.getName());
        }

        return options;
    }
}
