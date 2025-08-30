package me.DNFneca.lypheaAPI.registry;

import lombok.NoArgsConstructor;
import me.DNFneca.lypheaAPI.item.CustomItem;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class CustomItemRegistry extends Registry<CustomItem> {
    public static final CustomItemRegistry INSTANCE = new CustomItemRegistry();

    public static CustomItem getCustomItem(NamespacedKey key) {
        return INSTANCE.getValue(key);
    }
}
