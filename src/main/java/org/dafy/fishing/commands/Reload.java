package org.dafy.fishing.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.entity.Player;
import org.dafy.fishing.Fishing;

public class Relaod {
    @CommandAlias("Fishing")
    @CommandPermission("Fishing.admin.reload")
    public class Reload extends BaseCommand {
        private final Fishing plugin;

        private final FishingReward fishingReward;

        public Reload(Fishing plugin) {
            this.fishingReward = plugin.getReward();
            this.plugin = plugin;
        }

        @Subcommand("Reload")
        @CommandPermission("fishing.commands.admin.reload")
        public void reload(Player player) {
            this.plugin.reloadConfig();
            this.fishingReward.initRewards();
            player.sendMessage(ChatColor.GREEN + "Fishing: config reloaded.");
        }
    }

