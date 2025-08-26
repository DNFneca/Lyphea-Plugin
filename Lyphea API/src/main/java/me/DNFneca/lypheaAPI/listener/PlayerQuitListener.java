package me.DNFneca.lypheaAPI.listener;

import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event) {
        CustomPlayerManager.unregisterPlayer(event.getPlayer().getUniqueId());
    }
}