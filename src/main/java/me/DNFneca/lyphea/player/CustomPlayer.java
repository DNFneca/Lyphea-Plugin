package me.DNFneca.lyphea.player;

import lombok.Getter;
import lombok.Setter;
import me.DNFneca.lyphea.Lyphea;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.lang.constant.Constable;
import java.util.*;

@Getter
@Setter
public class CustomPlayer {
    private UUID UUID;
    private String name;
    private final Map<String, PlayerField<?>> fields = new HashMap<>(0);


    public CustomPlayer(UUID UUID, String name) {
        this.UUID = UUID;
        this.name = name;
        this.fields.put(new NamespacedKey(Lyphea.getInstance(), "mana").toString(), new PlayerField<>("Mana", 0D));
    }

    public CustomPlayer(UUID UUID, String name, Map<String, PlayerField<?>> fields) {
        this.UUID = UUID;
        this.name = name;
        this.fields.putAll(fields);
    }

    public CustomPlayer(Player player) {
        this.UUID = player.getUniqueId();
        this.name = player.getName();
        this.fields.put(new NamespacedKey(Lyphea.getInstance(), "mana").toString(), new PlayerField<>("Mana", 0D));
    }

    public CustomPlayer(Player player, Map<String, PlayerField<?>> fields) {
        this.UUID = player.getUniqueId();
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
