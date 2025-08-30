package me.DNFneca.lyphea.item;

import me.DNFneca.lyphea.Lyphea;
import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.item.CustomItem;
import me.DNFneca.lypheaAPI.item.CustomItemAbility;
import me.DNFneca.lypheaAPI.registry.CustomAbilityRegistry;
import me.DNFneca.lypheaAPI.registry.CustomItemRegistry;
import me.DNFneca.lypheaAPI.registry.CustomStatRegistry;
import me.DNFneca.lypheaAPI.util.LoreUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Items {

    public static void register() {
        CustomAbilityRegistry.INSTANCE.register("test_name",
                new CustomItemAbility(customPlayer -> {
                    float currentMana = customPlayer.getStat("mana");
                    if (currentMana < 10) {
                        customPlayer.getPlayer().sendMessage(Component.text("You don't have enough mana").color(NamedTextColor.RED).decorations(Set.of(TextDecoration.ITALIC), false));
                        return;
                    }
                    customPlayer.stat("mana", currentMana - 10);
                    customPlayer.getPlayer().sendMessage(Component.text("Test Ability").color(NamedTextColor.WHITE).decorations(Set.of(TextDecoration.ITALIC), false));
                    customPlayer.getPlayer().playSound(customPlayer.getPlayer(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                }));

        CustomItem itemStack = createItemStack(
                Material.DIAMOND_SWORD,
                "test_item",
                "Test Item",
                "This is a test item, it is meant to be ignored, if you're seeing this it's a bug (most likely)",
                new NamespacedKey(LypheaAPI.getInstance(), "test_name")
        );
        itemStack.setCustomModelData(1F);
    }

    private static CustomItem createItemStack(@NotNull Material material, @NotNull String name, @NotNull Component displayName, @NotNull String description, @Nullable NamespacedKey customItemAbility) {
        CustomItem customItem = new CustomItem(ItemStack.of(material));
        customItem.setCustomItemAbility(customItemAbility);
        customItem.setCustomDisplayName(displayName);
        customItem.setCustomLore(LoreUtils.createDescriptionLoreLine(description));
        customItem.setCustomItemStats(new HashMap<>(0) {
            {
                put("mana", 0F);
            }
        });
        CustomItemRegistry.INSTANCE.register(name, customItem);
        return customItem;
    }

    private static CustomItem createItemStack(@NotNull Material material, @NotNull String name, @NotNull String displayName, @NotNull String description, @Nullable NamespacedKey customItemAbility) {
        CustomItem customItem = new CustomItem(ItemStack.of(material));
        customItem.setCustomItemAbility(customItemAbility);
        customItem.setCustomDisplayName(Component.text(displayName).color(NamedTextColor.WHITE).decorations(Set.of(TextDecoration.ITALIC), false));
        customItem.setCustomLore(LoreUtils.createDescriptionLoreLine(description));
        customItem.setCustomItemStats(new HashMap<>() {
            {
                put("mana", 0F);
            }
        });
        CustomItemRegistry.INSTANCE.register(name, customItem);
        return customItem;
    }
}
