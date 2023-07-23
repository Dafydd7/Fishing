package org.dafy.fishing.reward;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;

import java.util.*;

public class ItemBuilder {
    private final int amount;

    private final Material material;

    private String name;

    private List<String> lore;

    private boolean unbreakable;

    private final double chance;

    private String message;

    private List<String> commands;
    private final HashMap<Enchantment,Integer> enchantments = new HashMap<>();

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
        if (section.contains("enchantments"))
            setEnchants(section.getStringList("enchantments"));
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

    public void setEnchants(List<String> enchants) {
        for (String enchantString : enchants) {
            String[] enchantmentData = enchantString.split(":");
            int enchantmentLevel = Integer.parseInt(enchantmentData[1]);
            Enchantment enchantment = Enchantment.getByName(enchantmentData[0]);
            if (enchantment != null)
                this.enchantments.put(enchantment, enchantmentLevel);
        }
    }
    public Map<Enchantment,Integer> getEnchants(){
        return this.enchantments;
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
