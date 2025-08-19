package me.DNFneca.lyphea.listener;

import me.DNFneca.lyphea.manager.CustomPlayerManager;
import me.DNFneca.lyphea.player.CustomPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        CustomPlayerManager.registerPlayer(event.getPlayer().getUniqueId());
    }
}
