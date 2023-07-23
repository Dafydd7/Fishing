package org.dafy.fishing.reward;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.dafy.fishing.Fishing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public class FishingReward {
    private final Fishing plugin;
    private final List<ItemBuilder> rewards = new ArrayList<>();
    private final Random random;

    public FishingReward(Fishing plugin) {
        this.plugin = plugin;
        this.random = new Random();
    }

    public void initRewards() {
        FileConfiguration config = this.plugin.getConfig();
        ConfigurationSection rewardsSection = config.getConfigurationSection("rewards.");
        rewards.clear();

        if (rewardsSection == null) {
            plugin.getLogger().log(Level.WARNING, "Unable to initialize fishing rewards - data null");
            return;
        }

        for (String key : rewardsSection.getKeys(false)) {
            ItemBuilder itemBuilder = new ItemBuilder(config.getConfigurationSection("rewards." + key));
            rewards.add(itemBuilder);
        }
    }

    public ItemBuilder pickReward() {
        double totalChance = calculateTotalChance();
        double randomValue = this.random.nextDouble() * totalChance;
        double cumulativeChance = 0.0D;
        for (ItemBuilder reward : this.rewards) {
            cumulativeChance += reward.getChance();
            if (randomValue <= cumulativeChance)
                return reward;
        }
        return null;
    }

    private double calculateTotalChance() {
        double totalChance = 0.0D;
        for (ItemBuilder reward : this.rewards)
            totalChance += reward.getChance();
        return totalChance;
    }

    public void giveRandomReward(Player player) {
        ItemBuilder randomReward = pickReward();
        //Check to see whether any reward exists.
        if (randomReward == null) {
            System.out.println("Error: Please check your rewards.yml or contact the developer.");
            return;
        }
        //Create the ItemStack, and give the player the item.
        Material material = randomReward.getMaterial();
        if (material != null) {
            ItemStack fishItem = new ItemStack(material);
            ItemMeta meta = fishItem.getItemMeta();
            fishItem.setAmount(randomReward.getAmount());
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', randomReward.getName()));
            if (randomReward.isUnbreakable()) {
                meta.spigot().setUnbreakable(true);
            }
            fishItem.setItemMeta(meta);
            fishItem.addUnsafeEnchantments(randomReward.getEnchants());
            player.getInventory().addItem(fishItem);
        }
        //Execute any commands if found
        if (randomReward.getCommands() != null && !randomReward.getCommands().isEmpty()) {
            Server server = this.plugin.getServer();
            for (String command : randomReward.getCommands())
                server.dispatchCommand(server.getConsoleSender(), command.replace("%player%", player.getName()));
        }
        //Send the player a message if there is any.
        String rawMessage = randomReward.getMessage();
        if (rawMessage != null && !rawMessage.isEmpty()) {
            String processedMessage = ChatColor.translateAlternateColorCodes('&', rawMessage.replace("%item%", randomReward.getName()));
            player.sendMessage(processedMessage);
        }
    }
}
