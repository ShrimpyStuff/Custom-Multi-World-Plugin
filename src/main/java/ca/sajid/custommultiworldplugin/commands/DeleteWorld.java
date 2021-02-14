package ca.sajid.custommultiworldplugin.commands;

import ca.sajid.custommultiworldplugin.util.BaseCommand;
import org.bukkit.Bukkit;
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
        super("delete");
    }

    private static void deleteRecursively(File directory) {
        for(File file : directory.listFiles()) {
            if(file.isDirectory()) {
                deleteRecursively(file);
            }
            else {
                file.delete();
            }
        }
    }

    @Override
    public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {

        if (Bukkit.getWorld(args[0]) == null) {
            return false;
        }

        List<Player> players = Bukkit.getWorld(args[0]).getPlayers();

        if (!players.isEmpty()) {
            for (Player player : players) {
                player.teleport(Bukkit.getWorld("Lobby").getSpawnLocation());
                player.sendTitle("World" + args[0], "is getting deleted.", 5, 100, 5);
            }
        }
        Properties pr = new Properties();
        String levelName = "";
        try
        {
            File f = new File("server.properties");
            FileInputStream in = new FileInputStream(f);
            pr.load(in);
            levelName = pr.getProperty("level-name");
        }
        catch (IOException e)
        {
            //I don't have anything to put here
        }

        if (!Bukkit.getWorld(args[0]).getName().equals(levelName) || !Bukkit.getWorld(args[0]).getName().equals(levelName + "_nether") || !Bukkit.getWorld(args[0]).getName().equals(levelName + "_the_end")) {
            Bukkit.unloadWorld(args[0], false);
            deleteRecursively(new File(args[0]));
        }


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
