package org.dafy.fishing.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.dafy.fishing.Fishing;

@CommandAlias("Fishing") @CommandPermission("Fishing.admin.enable")
public class Enable extends BaseCommand {
    @Dependency
    private Fishing plugin;

    public void enablePlugin(Player player) {
        if (this.plugin.getConfig().getBoolean("Fishing-Enabled")){
            player.sendMessage("Fishing: Already enabled.");
            return;
    }
        player.sendMessage(ChatColor.GREEN + "Fishing: now enabled.");
        this.plugin.getConfig().set("Fishing-Enabled", true);
    }
}
