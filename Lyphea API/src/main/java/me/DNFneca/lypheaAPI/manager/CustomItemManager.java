package me.DNFneca.lypheaAPI.manager;

import lombok.Getter;
import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.item.CustomItem;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.Map;

public class CustomItemManager {
    @Getter
    private static final Map<NamespacedKey, CustomItem> itemRegistry = new HashMap<>();

    public static void registerItem(String name, CustomItem item) {
        itemRegistry.put(new NamespacedKey(LypheaAPI.getInstance(), name), item);
    }
}
