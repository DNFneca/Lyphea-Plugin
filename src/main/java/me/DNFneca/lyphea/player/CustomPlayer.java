package me.DNFneca.lyphea.player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import me.DNFneca.lyphea.Lyphea;
import me.DNFneca.lyphea.item.CustomItemAbility;
import me.DNFneca.lyphea.manager.CustomItemAbilityManager;
import me.DNFneca.lyphea.manager.CustomPlayerManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.lang.constant.Constable;
import java.util.*;

import static me.DNFneca.lyphea.item.CustomItem.isCustomItem;

@Getter
@Setter
public class CustomPlayer {
    private UUID UUID;
    private String name;
    private final Map<String, PlayerField<?>> fields = new HashMap<>(0);


    public CustomPlayer(UUID UUID, String name) {
        this.UUID = UUID;
        this.name = name;
        CustomPlayerManager.initializePlayer(this);
    }

    public CustomPlayer(UUID UUID, String name, Map<String, PlayerField<?>> fields) {
        this.UUID = UUID;
        this.name = name;
        this.fields.putAll(fields);
    }

    public CustomPlayer(Player player) {
        this.UUID = player.getUniqueId();
        this.name = player.getName();
        CustomPlayerManager.initializePlayer(this);
    }

    public CustomPlayer(Player player, Map<String, PlayerField<?>> fields) {
        this.UUID = player.getUniqueId();
        this.name = player.getName();
        this.fields.putAll(fields);
    }

    public <T extends Constable> void field(NamespacedKey key, T value, Component displayName) {
        fields.put(key.toString(), new PlayerField<>(displayName, value));
    }

    public <T extends Constable> void field(String fieldName, T value, Component displayName) {
        fields.put(new NamespacedKey(Lyphea.getInstance(), fieldName).toString(), new PlayerField<>(displayName, value));
    }

    public <T extends Constable> void field(String fieldName, T value) {
        fields.put(
                new NamespacedKey(Lyphea.getInstance(), fieldName).toString(),
                new PlayerField<>(getField(fieldName).getName(), new Gson().toJson(value, value.getClass()))
        );
    }

    public <T> T field(String name, TypeToken<T> typeToken) {
        return fields.get(new NamespacedKey(Lyphea.getInstance(), name).toString()).getCurrentValue(typeToken);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(UUID);
    }

    public net.minecraft.world.entity.player.Player getNMSPlayer() {
        return ((CraftPlayer) getPlayer()).getHandle();
    }

    public PlayerField<?> getField(String name) {
        return fields.get(new NamespacedKey(Lyphea.getInstance(), name).toString());
    }

    public void castCustomItemAbility() {
        Player player = getPlayer();
        if (player == null || !isCustomItem(player.getInventory().getItemInMainHand())) return;
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        NamespacedKey key = new NamespacedKey(Lyphea.getInstance(), "customItemAbility");
        if (!itemStack.getPersistentDataContainer().has(key)) return;
        String customItemAbilityKey = itemStack.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        if (customItemAbilityKey == null) return;
        CustomItemAbility customItemAbility = CustomItemAbilityManager.getAbilityRegistry().get(NamespacedKey.fromString(customItemAbilityKey));
        if (customItemAbility == null) return;
        customItemAbility.getCustomPlayerConsumer().accept(this);
    }

}
