package ca.sajid.custommultiworldplugin.commands;

import ca.sajid.custommultiworldplugin.Utils;
import ca.sajid.custommultiworldplugin.util.BaseCommand;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CreateWorld extends BaseCommand {

    public CreateWorld() {
        super("create");
    }
    @Override
    public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Utils.color("&c Missing arguments [Name] [Normal|Nether|End] (AMPLIFIED|FLAT|LARGE_BIOMES)"));
            return false;
        }

        WorldCreator wc = new WorldCreator(args[0]);

        switch (args[1].toUpperCase()) {
            case "NORMAL":
                wc.environment(World.Environment.NORMAL);
                break;
            case "NETHER":
                wc.environment(World.Environment.NETHER);
                break;
            case "END":
                wc.environment(World.Environment.THE_END);
                break;
        }

        if (args.length < 3 || args[2].toUpperCase().equals("NORMAL")) {
            wc.type(WorldType.NORMAL);
        } else if (args[2].toUpperCase().equals("AMPLIFIED")) {
            wc.type(WorldType.AMPLIFIED);
        } else if (args[2].toUpperCase().equals("FLAT")) {
            wc.type(WorldType.FLAT);
        } else if (args[2].toUpperCase().equals("LARGE_BIOMES") || args[2].toUpperCase().equals("LARGE BIOMES")) {
            wc.type(WorldType.LARGE_BIOMES);
        }

        wc.createWorld();

        return true;
    }
}
