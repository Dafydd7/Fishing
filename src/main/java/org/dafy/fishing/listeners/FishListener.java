package org.dafy.fishing.listeners;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.dafy.fishing.Fishing;
import org.dafy.fishing.reward.FishingReward;

public class FishListener implements Listener {
    private final Fishing plugin;

    private final FishingReward reward;

    public FishListener(Fishing plugin) {
        this.plugin = plugin;
        this.reward = plugin.getReward();
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (!this.plugin.getConfig().getBoolean("Fishing-Enabled"))
            return;
        if (event.isCancelled())
            return;
        if (!event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH))
            return;
        if (!(event.getCaught() instanceof Item))
            return;
        Item item = (Item)event.getCaught();
        item.remove();
        this.reward.giveRandomReward(event.getPlayer());
    }
}
