package me.DNFneca.lyphea.player;

import me.DNFneca.lypheaAPI.player.PlayerField;
import org.bukkit.entity.Player;

import java.util.*;

public class CustomPlayer extends me.DNFneca.lypheaAPI.player.CustomPlayer {
    public CustomPlayer(UUID UUID, String name) {
        super(UUID, name);
    }

    public CustomPlayer(UUID UUID, String name, Map<String, PlayerField<?>> fields) {
        super(UUID, name, fields);
    }

    public CustomPlayer(Player player) {
        super(player);
    }

    public CustomPlayer(Player player, Map<String, PlayerField<?>> fields) {
        super(player, fields);
    }
}