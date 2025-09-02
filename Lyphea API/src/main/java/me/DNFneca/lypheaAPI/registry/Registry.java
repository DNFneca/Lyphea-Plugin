package me.DNFneca.lypheaAPI.registry;

import me.DNFneca.lypheaAPI.LypheaAPI;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Registry<T> {
    protected final Map<NamespacedKey, T> registry = new HashMap<>(0);

    public void register(NamespacedKey key, T value) {
        registry.put(key, value);
    }

    public void register(String name, T stat) {
        if (registry.get(new NamespacedKey(LypheaAPI.getInstance(), name)) != null) {
            throw new IllegalStateException("Already registered " + name + " as " + stat.getClass().getSimpleName());
        }
        registry.put(new NamespacedKey(LypheaAPI.getInstance(), name), stat);
    }

    public T getValue(NamespacedKey key) {
        return registry.get(key);
    }

    public T getValue(String key) {
        return registry.get(new NamespacedKey(LypheaAPI.getInstance(), key));
    }

    public Set<Map.Entry<NamespacedKey, T>> entrySet() {
        return registry.entrySet();
    }
}
