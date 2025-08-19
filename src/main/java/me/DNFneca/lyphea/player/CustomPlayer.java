package me.DNFneca.lyphea.player;

import me.DNFneca.lyphea.Lyphea;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.lang.constant.Constable;
import java.util.*;

public class CustomPlayer {
    public UUID uuid;
    public String name;
    public final Map<String, PlayerField<?>> fields = new HashMap<>(0);

    public CustomPlayer(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.fields.put(new NamespacedKey(Lyphea.getInstance(), "mana").toString(), new PlayerField<>("Mana", 0D));
    }

    public CustomPlayer(UUID uuid, String name, Map<String, PlayerField<?>> fields) {
        this.uuid = uuid;
        this.name = name;
        this.fields.putAll(fields);
    }

    public CustomPlayer(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.fields.put(new NamespacedKey(Lyphea.getInstance(), "mana").toString(), new PlayerField<>("Mana", 0D));
    }

    public CustomPlayer(Player player, Map<String, PlayerField<?>> fields) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.fields.putAll(fields);
    }

    public <T extends Constable> void registerField(NamespacedKey key, T value, String displayName) {
        fields.put(key.toString(), new PlayerField<>(displayName, value));
    }

    public <T extends Constable> void registerField(String fieldName, T value, String displayName) {
        fields.put(new NamespacedKey(Lyphea.getInstance(), fieldName).toString(), new PlayerField<>(displayName, value));
    }

    public PlayerField<?> getField(String name) {
        return fields.get(new NamespacedKey(Lyphea.getInstance(), name).toString());
    }
}
