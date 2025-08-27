package me.DNFneca.lypheaAPI.manager;

import com.google.gson.Gson;
import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.player.CustomPlayer;
import me.DNFneca.lypheaAPI.player.PlayerField;
import me.DNFneca.lypheaAPI.player.Race;
import me.DNFneca.lypheaAPI.player.collection.Collection;
import me.DNFneca.lypheaAPI.player.collection.CollectionType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CustomPlayerManager {
    private static final Map<UUID, CustomPlayer> players = new HashMap<>();
    public static final CustomPlayerManager INSTANCE = new CustomPlayerManager();

    private CustomPlayerManager() {
        new File(LypheaAPI.getInstance().getDataFolder(), "players").mkdirs();
    }

    public static void registerPlayer(UUID uuid) {
        players.put(uuid, loadPlayer(uuid));
    }

    public static void unregisterPlayer(UUID uuid) {
        savePlayer(players.get(uuid));
        players.remove(uuid);
    }

    private static void savePlayer(CustomPlayer customPlayer) {
        File file = new File(LypheaAPI.getInstance().getDataFolder(), "players/" + customPlayer.getUUID().toString() + ".json");
        try (FileWriter writer = new FileWriter(file)) {
            new Gson().toJson(customPlayer, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static CustomPlayer loadPlayer(UUID uuid) {
        File file = new File(LypheaAPI.getInstance().getDataFolder(), "players/" + uuid.toString() + ".json");
        if (!file.exists()) {
            CustomPlayer customPlayer = new CustomPlayer(uuid, Bukkit.getPlayer(uuid).getName());
            savePlayer(customPlayer);
            return customPlayer;
        }
        try (FileReader reader = new FileReader(file)) {
            return new Gson().fromJson(reader, CustomPlayer.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CustomPlayer getPlayer(UUID uuid) {
        return players.get(uuid);
    }

    public static void initializePlayer(CustomPlayer customPlayer) {
        customPlayer.getCollections().put(CollectionType.COMBAT, new Collection(CollectionType.COMBAT, Component.text("Combat"), 0));
        customPlayer.getCollections().put(CollectionType.MINING, new Collection(CollectionType.MINING, Component.text("Mining"), 0));
        customPlayer.addAllFields(
                new HashMap<>(0) {
                    {
                        put(new NamespacedKey(LypheaAPI.getInstance(), "mana").toString(), new PlayerField<>(Component.text("Mana").color(NamedTextColor.AQUA).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(LypheaAPI.getInstance(), "damage").toString(), new PlayerField<>(Component.text("Damage").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(LypheaAPI.getInstance(), "strength").toString(), new PlayerField<>(Component.text("Strength").color(NamedTextColor.DARK_RED).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(LypheaAPI.getInstance(), "defense").toString(), new PlayerField<>(Component.text("Defense").color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(LypheaAPI.getInstance(), "stealth").toString(), new PlayerField<>(Component.text("Stealth").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(LypheaAPI.getInstance(), "speed").toString(), new PlayerField<>(Component.text("Speed").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(LypheaAPI.getInstance(), "intelligence").toString(), new PlayerField<>(Component.text("Intelligence").color(NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(LypheaAPI.getInstance(), "race").toString(), new PlayerField<>(Component.text("Race").color(NamedTextColor.AQUA).decoration(TextDecoration.ITALIC, false), Race.HUMAN));
                        put(new NamespacedKey(LypheaAPI.getInstance(), "color").toString(), new PlayerField<>(Component.text("Color").color(NamedTextColor.AQUA).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(LypheaAPI.getInstance(), "height").toString(), new PlayerField<>(Component.text("Height").color(NamedTextColor.AQUA).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(LypheaAPI.getInstance(), "rank").toString(), new PlayerField<>(Component.text("Rank").color(NamedTextColor.AQUA).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(LypheaAPI.getInstance(), "level").toString(), new PlayerField<>(Component.text("Level").color(NamedTextColor.AQUA).decoration(TextDecoration.ITALIC, false), 0D));
                    }
                });

    }
}
