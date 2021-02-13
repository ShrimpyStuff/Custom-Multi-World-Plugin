package ca.sajid.custommultiworldplugin.util;

import ca.sajid.custommultiworldplugin.CustomMultiWorldPlugin;

public class BaseModule {

    private final CustomMultiWorldPlugin plugin = CustomMultiWorldPlugin.getPlugin();

    public void onEnable() {
    }

    public void onDisable() {
    }

    public CustomMultiWorldPlugin getPlugin() {
        return plugin;
    }
}
