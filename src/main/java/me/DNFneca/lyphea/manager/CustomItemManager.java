package me.DNFneca.lyphea.manager;

import lombok.Getter;
import me.DNFneca.lyphea.Lyphea;
import me.DNFneca.lyphea.item.CustomItem;
import org.bukkit.NamespacedKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomItemManager {
    @Getter
    private static final Map<NamespacedKey, CustomItem> itemRegistry = new HashMap<>();

    public static void registerItem(String name, CustomItem item) {
        itemRegistry.put(new NamespacedKey(Lyphea.getInstance(), name), item);
    }
}
