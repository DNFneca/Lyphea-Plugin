package me.DNFneca.lypheaAPI.player;

import lombok.Getter;
import lombok.Setter;
import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.entity.FieldedEntity;
import me.DNFneca.lypheaAPI.item.CustomItemAbility;
import me.DNFneca.lypheaAPI.manager.CustomItemAbilityManager;
import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import me.DNFneca.lypheaAPI.player.collection.Collection;
import me.DNFneca.lypheaAPI.player.collection.CollectionType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

import static me.DNFneca.lypheaAPI.item.CustomItem.isCustomItem;


public class CustomPlayer extends FieldedEntity {
    @Getter
    @Setter
    private UUID UUID;
    @Getter
    @Setter
    private UUID currentGUI;
    @Getter
    @Setter
    private UUID nextGUI;
    private String name;
    @Getter
    private final Map<CollectionType, Collection> collections = new HashMap<>();
    @Getter
    private final Map<NamespacedKey, Long> abilitiesCastHistory = new HashMap<>();

    public CustomPlayer(UUID UUID, String name) {
        this.UUID = UUID;
        this.name = name;
        CustomPlayerManager.initializePlayer(this);
    }

    public CustomPlayer(UUID UUID, String name, Map<String, PlayerField<?>> fields) {
        this.UUID = UUID;
        this.name = name;
        super.addAllFields(fields);
    }

    public CustomPlayer(Player player) {
        this.UUID = player.getUniqueId();
        this.name = player.getName();
        CustomPlayerManager.initializePlayer(this);
    }

    public CustomPlayer(Player player, Map<String, PlayerField<?>> fields) {
        this.UUID = player.getUniqueId();
        this.name = player.getName();
        super.addAllFields(fields);
    }

    public double collection(CollectionType collectionType) {
        return ((double) Math.round(collections.get(collectionType).getCollectedAmount() * 100) / 100);
    }



    public Player getPlayer() {
        return Bukkit.getPlayer(UUID);
    }

    public net.minecraft.world.entity.player.Player getNMSPlayer() {
        return ((CraftPlayer) getPlayer()).getHandle();
    }

    public void castCustomItemAbility() {
        Player player = getPlayer();
        if (player == null || !isCustomItem(player.getInventory().getItemInMainHand())) return;
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        NamespacedKey key = new NamespacedKey(LypheaAPI.getInstance(), "customItemAbility");
        if (!itemStack.getPersistentDataContainer().has(key)) return;
        String customItemAbilityKey = itemStack.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        if (customItemAbilityKey == null) return;
        CustomItemAbility customItemAbility = CustomItemAbilityManager.getAbilityRegistry().get(NamespacedKey.fromString(customItemAbilityKey));
        if (customItemAbility == null) return;
        customItemAbility.getCustomPlayerConsumer().accept(this);
    }

    public Component getName() {
        return GsonComponentSerializer.gson().deserialize(name);
    }

    public void setName(Component name) {
        this.name = GsonComponentSerializer.gson().serialize(name);
    }
}