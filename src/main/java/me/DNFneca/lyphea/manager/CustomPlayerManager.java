package me.DNFneca.lyphea.manager;

import com.google.gson.Gson;
import lombok.Getter;
import me.DNFneca.lyphea.Lyphea;
import me.DNFneca.lyphea.player.CustomPlayer;
import me.DNFneca.lyphea.player.PlayerField;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
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


    // Private constructor
    private CustomPlayerManager() {
        new File(Lyphea.getInstance().getDataFolder(), "players").mkdirs();
    }

    public static void registerPlayer(UUID uuid) {
        players.put(uuid, loadPlayer(uuid));
    }

    public static void unregisterPlayer(UUID uuid) {
        savePlayer(players.get(uuid));
        players.remove(uuid);
    }

    private static void savePlayer(CustomPlayer customPlayer) {
        File file = new File(Lyphea.getInstance().getDataFolder(), "players/" + customPlayer.getUUID().toString() + ".json");
        try (FileWriter writer = new FileWriter(file)) {
            new Gson().toJson(customPlayer, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static CustomPlayer loadPlayer(UUID uuid) {
        File file = new File(Lyphea.getInstance().getDataFolder(), "players/" + uuid.toString() + ".json");
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
        customPlayer.getFields().putAll(
                new HashMap<>(0) {
                    {
                        put(new NamespacedKey(Lyphea.getInstance(), "mana").toString(), new PlayerField<>(Component.text("Mana").color(NamedTextColor.AQUA).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(Lyphea.getInstance(), "damage").toString(), new PlayerField<>(Component.text("Damage").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(Lyphea.getInstance(), "strength").toString(), new PlayerField<>(Component.text("Strength").color(NamedTextColor.DARK_RED).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(Lyphea.getInstance(), "defense").toString(), new PlayerField<>(Component.text("Defense").color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(Lyphea.getInstance(), "stealth").toString(), new PlayerField<>(Component.text("Stealth").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(Lyphea.getInstance(), "speed").toString(), new PlayerField<>(Component.text("Speed").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false), 0D));
                        put(new NamespacedKey(Lyphea.getInstance(), "intelligence").toString(), new PlayerField<>(Component.text("Intelligence").color(NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false), 0D));
                    }
                });

    }

}
