package me.DNFneca.lypheaAPI.player;

import lombok.Getter;
import lombok.Setter;
import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.entity.CustomStatEntity;
import me.DNFneca.lypheaAPI.entity.FieldedEntity;
import me.DNFneca.lypheaAPI.item.CustomItem;
import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import me.DNFneca.lypheaAPI.player.collection.Collection;
import me.DNFneca.lypheaAPI.player.collection.CollectionType;
import me.DNFneca.lypheaAPI.registry.CustomAbilityRegistry;
import me.DNFneca.lypheaAPI.registry.CustomStatRegistry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static me.DNFneca.lypheaAPI.item.CustomItem.isCustomItem;


public class CustomPlayer extends CustomStatEntity {
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
    private final Map<CollectionType, Collection> collections = new HashMap<>(0);
    private final Map<NamespacedKey, Long> abilitiesCastHistory = new HashMap<>(0);

    public CustomPlayer(UUID UUID, String name) {
        this.UUID = UUID;
        this.name = name;
        CustomPlayerManager.initializePlayer(this);
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
        CustomItem customItem = new CustomItem(itemStack);
        CustomAbilityRegistry.getCustomAbility(customItem.getCustomItemAbility()).customPlayerConsumer().accept(this);
    }

    public Component getName() {
        return GsonComponentSerializer.gson().deserialize(name);
    }

    public void setName(Component name) {
        this.name = GsonComponentSerializer.gson().serialize(name);
    }

    public void reloadStats() {
        Player player = getPlayer();
        if (player == null) return;
        List<CustomItem> customItems = new ArrayList<>(0);
        if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getType() != Material.AIR) {
            customItems.add(new CustomItem(player.getInventory().getHelmet()));
        }
        if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() != Material.AIR) {
            customItems.add(new CustomItem(player.getInventory().getChestplate()));
        }
        if (player.getInventory().getLeggings() != null && player.getInventory().getLeggings().getType() != Material.AIR) {
            customItems.add(new CustomItem(player.getInventory().getLeggings()));
        }
        if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getType() != Material.AIR) {
            customItems.add(new CustomItem(player.getInventory().getBoots()));
        }
        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
            customItems.add(new CustomItem(player.getInventory().getItemInMainHand()));
        }
        if (player.getInventory().getItemInOffHand().getType() != Material.AIR) {
            customItems.add(new CustomItem(player.getInventory().getItemInOffHand()));
        }
    }
}