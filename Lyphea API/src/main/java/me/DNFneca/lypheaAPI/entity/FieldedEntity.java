package me.DNFneca.lypheaAPI.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.player.PlayerField;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;

import java.lang.constant.Constable;
import java.util.HashMap;
import java.util.Map;

public abstract class FieldedEntity {
    protected final Map<String, PlayerField<?>> fields = new HashMap<>(0);

    public <T extends Constable> void field(NamespacedKey key, T value, Component displayName) {
        fields.put(key.toString(), new PlayerField<>(displayName, value));
    }

    public <T extends Constable> void field(String fieldName, T value, Component displayName) {
        fields.put(new NamespacedKey(LypheaAPI.getInstance(), fieldName).toString(), new PlayerField<>(displayName, value));
    }

    public <T extends Constable> void field(String fieldName, T value) {
        fields.put(
                new NamespacedKey(LypheaAPI.getInstance(), fieldName).toString(),
                new PlayerField<>(getField(fieldName).getName(), new Gson().toJson(value, value.getClass()))
        );
    }

    public <T> T field(String name, TypeToken<T> typeToken) {
        return fields.get(new NamespacedKey(LypheaAPI.getInstance(), name).toString()).getCurrentValue(typeToken);
    }

    public PlayerField<?> getField(String name) {
        return fields.get(new NamespacedKey(LypheaAPI.getInstance(), name).toString());
    }

    public void addAllFields(Map<String, PlayerField<?>> fields) {
        this.fields.putAll(fields);
    }
}
