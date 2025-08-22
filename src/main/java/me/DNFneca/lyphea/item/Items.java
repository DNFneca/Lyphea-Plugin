package me.DNFneca.lyphea.item;

import com.google.gson.reflect.TypeToken;
import me.DNFneca.lyphea.util.LoreUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class Items {

    public static void register() {

        CustomItemAbility customItemAbility = CustomItemAbility.registerCustomItemAbility("test_name", customPlayer -> {
            Double currentMana = customPlayer.field("mana", new TypeToken<>() {});
            if (currentMana < 10D) return;
            customPlayer.field("mana", currentMana - 10D);
            customPlayer.getPlayer().sendMessage(Component.text("Test Ability").color(NamedTextColor.WHITE).decorations(Set.of(TextDecoration.ITALIC), false));
            customPlayer.getPlayer().playSound(customPlayer.getPlayer(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
        });

        createItemStack(
                Material.DIAMOND_SWORD,
                "test_item",
                "Test Item",
                "This is a test item, it is meant to be ignored, if you're seeing this it's a bug (most likely)",
                customItemAbility
        );
    }

    private static ItemStack createItemStack(@NotNull Material material, @NotNull String name, @NotNull Component displayName, @NotNull String description, @Nullable CustomItemAbility customItemAbility) {
        ItemStack itemStack = ItemStack.of(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.lore(LoreUtils.CreateDescriptionLoreLine(description));
        itemMeta.displayName(displayName);
        itemStack.setItemMeta(itemMeta);
        CustomItem.registerCustomItem(name, itemStack, customItemAbility);
        return itemStack;
    }

    private static ItemStack createItemStack(@NotNull Material material, @NotNull String name, @NotNull String displayName, @NotNull String description, @Nullable CustomItemAbility customItemAbility) {
        ItemStack itemStack = ItemStack.of(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.lore(LoreUtils.CreateDescriptionLoreLine(description));
        itemMeta.displayName(Component.text(displayName).color(NamedTextColor.WHITE).decorations(Set.of(TextDecoration.ITALIC), false));
        itemStack.setItemMeta(itemMeta);
        CustomItem.registerCustomItem(name, itemStack, customItemAbility);
        return itemStack;
    }
}
