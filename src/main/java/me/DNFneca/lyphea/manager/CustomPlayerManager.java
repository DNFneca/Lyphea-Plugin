package me.DNFneca.lyphea.manager;

import com.google.gson.Gson;
import me.DNFneca.lyphea.Lyphea;
import me.DNFneca.lyphea.player.CustomPlayer;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CustomPlayerManager {
    public static final Map<UUID, CustomPlayer> players = new HashMap<>();
    public static CustomPlayerManager INSTANCE = new CustomPlayerManager();


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

}
