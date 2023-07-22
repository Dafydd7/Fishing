package org.dafy.fishing.reward;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ItemBuilder {
    private final int amount;

    private final Material material;

    private String name;

    private List<String> lore;

    private boolean unbreakable;

    private final double chance;

    private String message;

    private List<String> commands;

    public ItemBuilder(ConfigurationSection section) {
        this.material = Material.valueOf(section.getString("material", "cod").toUpperCase(Locale.ENGLISH));
        this.amount = section.getInt("amount", 1);
        this.chance = section.getDouble("chance", 0.1D);
        if (section.contains("message"))
            this.message = section.getString("message");
        if (section.contains("commands"))
            setCommands(section.getStringList("commands"));
        if (section.contains("display.name"))
            setName(section.getString("display.name"));
        if (section.contains("display.lore"))
            setLore(section.getStringList("display.lore"));
        setUnbreakable(section.getBoolean("unbreakable", false));
    }

    public Material getMaterial() {
        return this.material;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLore() {
        return this.lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public boolean isUnbreakable() {
        return this.unbreakable;
    }

    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
    }

    public void addEnchants(ConfigurationSection section) {
        if (!section.contains("enchantments"))
            return;
        ItemStack stack = new ItemStack(this.material);
        Objects.requireNonNull(section.getConfigurationSection("enchantments")).getKeys(false).forEach(enchant -> {
            section.get("enchantments." + enchant);
            String[] enchantString = enchant.split(":");
            stack.addEnchantment(Enchantment.getByName(enchantString[0]), Integer.parseInt(enchantString[1]));
        });
    }

    public List<String> getCommands() {
        return this.commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public int getAmount() {
        return this.amount;
    }

    public double getChance() {
        return this.chance;
    }

    public String getMessage() {
        return this.message;
    }
}
