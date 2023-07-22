package org.dafy.fishing.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.entity.Player;
import org.dafy.fishing.Fishing;

@CommandAlias("FishDebug") @CommandPermission("Fishing.admin.debug")
public class Debug extends BaseCommand {
    @Dependency
    private Fishing plugin;
    @Subcommand("giveReward")
    public void debugReward(Player player) {
        this.plugin.getReward().giveRandomReward(player);
        player.sendMessage("Reward: " + this.plugin.getReward().pickReward().toString());
    }
}