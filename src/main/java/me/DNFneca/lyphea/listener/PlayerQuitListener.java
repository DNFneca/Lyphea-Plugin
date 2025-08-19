package me.DNFneca.lyphea.listener;

import me.DNFneca.lyphea.manager.CustomPlayerManager;
import me.DNFneca.lyphea.player.CustomPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event) {
        CustomPlayerManager.unregisterPlayer(event.getPlayer().getUniqueId());
    }
}
