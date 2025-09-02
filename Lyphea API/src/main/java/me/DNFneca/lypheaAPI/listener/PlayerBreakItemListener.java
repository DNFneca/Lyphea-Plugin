package me.DNFneca.lypheaAPI.listener;

import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;

public class PlayerBreakItemListener implements Listener {
    @EventHandler
    public void onPlayerBreakItem(PlayerItemBreakEvent event) {
        CustomPlayerManager.getPlayer(event.getPlayer()).reloadStats();
    }
}
