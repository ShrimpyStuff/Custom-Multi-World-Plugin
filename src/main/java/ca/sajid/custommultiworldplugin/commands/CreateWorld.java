package ca.sajid.custommultiworldplugin.commands;

import ca.sajid.custommultiworldplugin.Utils;
import ca.sajid.custommultiworldplugin.modules.GamemodeModule;
import ca.sajid.custommultiworldplugin.util.BaseCommand;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CreateWorld extends BaseCommand {

    public CreateWorld() {
        super("createworld");
    }

    @Override
    public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length < 3) return false;

        WorldCreator wc = new WorldCreator(args[0]);

        try {
            wc.environment(World.Environment.valueOf(args[1].toUpperCase()));
        } catch (IllegalArgumentException e) {
            sender.sendMessage(Utils.color("&cInvalid world enviroment!"));
            return true;
        }

        try {
            wc.type(WorldType.valueOf(args[2].toUpperCase()));
        } catch (IllegalArgumentException e) {
            sender.sendMessage(Utils.color("6cInvalid world type!"));
            return true;
        }

        if (args[3] == null) {
            args[3] = "Adventure";
        }

        wc.createWorld();
        GamemodeModule.getWorldGamemode().get().set("gamemode." + args[0], args[3]);
        GamemodeModule.getWorldGamemode().save();
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> options = new ArrayList<>();

        if(args.length == 2) {
            for (World.Environment env : World.Environment.values()) {
                options.add(env.name().toLowerCase());
            }
        } else if (args.length == 3) {
            for(WorldType type : WorldType.values()) {
                options.add(type.name().toLowerCase());
            }
        } else if (args.length == 4) {
            for (GameMode mode : GameMode.values()) {
                options.add(mode.name());
            }
        }

        options.removeIf(s -> !s.startsWith(args[args.length - 1]));
        return options;
    }
}
