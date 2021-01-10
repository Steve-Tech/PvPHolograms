package me.stevetech.pvpholograms;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PvPHolograms extends JavaPlugin {
    String[] placeholders = {
            "kills_name",
            "kills",
            "deaths_name",
            "deaths",
            "xp_name",
            "xp",
            "level_name",
            "level",
            "killstreak_name",
            "killstreak",
            "killstreak_top_name",
            "killstreak_top"
    };

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        regPlaceholders();

        getLogger().info(getDescription().getName() + ' ' + getDescription().getVersion() + " has been Enabled");
    }

    @Override
    public void onDisable() {
        saveConfig();

        getLogger().info(getDescription().getName() + ' ' + getDescription().getVersion() + " has been Disabled");
    }

    private void regPlaceholders() {
        for (String placeholder: placeholders) {
            for (int i = 1; i < getConfig().getInt("max-lines"); i++) {
                String fullPlaceholder = "{pvplevels_top_" + i + '_' + placeholder + '}';
                HologramsAPI.registerPlaceholder(this, fullPlaceholder, getConfig().getInt("refresh"), new PvPPlaceholders(placeholder, i));
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("PvPHolograms") && args.length == 1 && args[0].equals("reload")) {
            reloadConfig();
            HologramsAPI.unregisterPlaceholders(this);
            regPlaceholders();
            sender.sendMessage("Reloaded Config! You will want to reload Holographic Displays' config.");
            //getServer().dispatchCommand(sender, "holograms reload");

            return true;
        } else return false;
    }
}

