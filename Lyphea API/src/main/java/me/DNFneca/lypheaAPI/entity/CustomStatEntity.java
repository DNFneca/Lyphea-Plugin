package me.DNFneca.lypheaAPI.entity;

import lombok.Getter;
import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.registry.CustomStatRegistry;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.Map;

public abstract class CustomStatEntity extends FieldedEntity {
    @Getter
    protected final Map<String, Float> stats = new HashMap<>(0);

    public float getStat(NamespacedKey namespacedKey) {
        return stats.get(namespacedKey.toString());
    }

    public float getStat(String entry) {
        return stats.get(new NamespacedKey(LypheaAPI.getInstance(), entry).toString());
    }

    public void appendStat(NamespacedKey namespacedKey) {
        stats.put(namespacedKey.toString(), CustomStatRegistry.getCustomStat(namespacedKey).getDefaultValue());
    }

    public void appendStat(String name) {
        NamespacedKey namespacedKey = new NamespacedKey(LypheaAPI.getInstance(), name);
        stats.put(namespacedKey.toString(), CustomStatRegistry.getCustomStat(namespacedKey).getDefaultValue());
    }

    public void stat(NamespacedKey namespacedKey, float value) {
        stats.put(namespacedKey.toString(), value);
    }

    public void stat(String name, float value) {
        stats.put(new NamespacedKey(LypheaAPI.getInstance(), name).toString(), value);
    }
}
