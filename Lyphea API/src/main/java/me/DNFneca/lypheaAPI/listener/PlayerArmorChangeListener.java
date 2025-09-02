package me.DNFneca.lypheaAPI.listener;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerArmorChangeListener implements Listener {
    @EventHandler
    public void onPlayerArmorChange(PlayerArmorChangeEvent event) {
        CustomPlayerManager.getPlayer(event.getPlayer()).reloadStats();
    }
}
