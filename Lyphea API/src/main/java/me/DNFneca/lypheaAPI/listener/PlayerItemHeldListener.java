package me.DNFneca.lypheaAPI.listener;

import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerItemHeldListener implements Listener {
    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        CustomPlayerManager.getPlayer(event.getPlayer()).reloadStats();
    }
}
