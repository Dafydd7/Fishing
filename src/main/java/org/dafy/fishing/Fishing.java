package org.dafy.fishing;

import co.aikar.commands.BukkitCommandManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.dafy.fishing.commands.Debug;
import org.dafy.fishing.commands.Disable;
import org.dafy.fishing.commands.Enable;
import org.dafy.fishing.commands.Reload;
import org.dafy.fishing.listeners.FishListener;
import org.dafy.fishing.reward.FishingReward;

public final class Fishing extends JavaPlugin {

    private FishingReward reward;

    private Reload reload;

    public FishingReward getReward() {
        return this.reward;
    }

    public Reload getReload() {
        return this.reload;
    }

    public void onEnable() {
        //Initialise classes & initialise rewards using the initRewards() function.
        reward = new FishingReward(this);
        reload = new Reload(this);
        reward.initRewards();
        //Register commands
        BukkitCommandManager manager = new BukkitCommandManager(this);
        manager.registerCommand(new Disable());
        manager.registerCommand(new Enable());
        manager.registerCommand(new Reload(this));
        manager.registerCommand(new Debug());
        //Register listener
        getServer().getPluginManager().registerEvents(new FishListener(this),this);
        saveDefaultConfig();
    }
    public void onDisable() {

    }
}
