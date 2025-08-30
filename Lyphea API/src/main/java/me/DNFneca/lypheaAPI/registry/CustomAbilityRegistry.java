package me.DNFneca.lypheaAPI.registry;

import me.DNFneca.lypheaAPI.item.CustomItemAbility;
import org.bukkit.NamespacedKey;

public class CustomAbilityRegistry extends Registry<CustomItemAbility> {
    public static final CustomAbilityRegistry INSTANCE = new CustomAbilityRegistry();

    public static CustomItemAbility getCustomAbility(NamespacedKey key) {
        return INSTANCE.getValue(key);
    }
}
