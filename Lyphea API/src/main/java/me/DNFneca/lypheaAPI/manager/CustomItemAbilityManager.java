package me.DNFneca.lypheaAPI.manager;

import lombok.Getter;
import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.item.CustomItemAbility;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.Map;

public class CustomItemAbilityManager {
    @Getter
    private static final Map<NamespacedKey, CustomItemAbility> abilityRegistry = new HashMap<>();


    public static void registerItem(String name, CustomItemAbility ability) {
        abilityRegistry.put(new NamespacedKey(LypheaAPI.getInstance(), name), ability);
    }
}
