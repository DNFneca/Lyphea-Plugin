package me.DNFneca.lypheaAPI.item;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.manager.CustomItemManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomItem {
    @Getter
    private ItemStack itemStack;


    public static void registerCustomItem(@NotNull String name, @NotNull ItemStack item, @Nullable CustomItemAbility customItemAbility) {
        item.editPersistentDataContainer(persistentDataContainer ->
        {
            persistentDataContainer.set(new NamespacedKey(LypheaAPI.getInstance(), "customItem"), PersistentDataType.BOOLEAN, true);
            if (item.getItemMeta().displayName() != null) {
                persistentDataContainer.set(
                        new NamespacedKey(LypheaAPI.getInstance(), "customItemName"),
                        PersistentDataType.STRING,
                        GsonComponentSerializer.gson().serialize(item.getItemMeta().displayName()));
            }
            if (item.getItemMeta().lore() != null) {
                List<String> stringLore = new ArrayList<>(0);
                for (Component lore : item.getItemMeta().lore()) {
                    stringLore.add(GsonComponentSerializer.gson().serialize(lore));
                }
                persistentDataContainer.set(
                        new NamespacedKey(LypheaAPI.getInstance(), "customItemLore"),
                        PersistentDataType.STRING,
                        new Gson().toJson(stringLore, new TypeToken<List<String>>() {}.getType()));
            }
            if (customItemAbility != null) {
                persistentDataContainer.set(
                        new NamespacedKey(LypheaAPI.getInstance(), "customItemAbility"),
                        PersistentDataType.STRING,
                        customItemAbility.getKey().toString());
            }
        });
        CustomItem customItem = new CustomItem();
        customItem.itemStack = item;
        CustomItemManager.registerItem(name, customItem);
    }

    public static boolean isCustomItem(ItemStack item) {
        return item.getPersistentDataContainer().has(new NamespacedKey(LypheaAPI.getInstance(), "customItem"));
    }

}
