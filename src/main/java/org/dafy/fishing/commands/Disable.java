package org.dafy.fishing.commands;


import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.entity.Player;
import org.dafy.fishing.Fishing;

@CommandAlias("Fishing") @CommandPermission("Fishing.admin.disable")
public class Disable extends BaseCommand {
    @Dependency
    private Fishing plugin;
    public void enableFishing(Player player) {
        if (!this.plugin.getConfig().getBoolean("Fishing-Enabled")){
            player.sendMessage("Fishing: Already disabled.");
            return;
        }
        player.sendMessage("Fishing: now disabled.");
        this.plugin.getConfig().set("Fishing-Enabled", true);
    }
}