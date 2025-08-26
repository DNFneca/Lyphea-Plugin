package me.DNFneca.lypheaAPI.listener;

import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerRightClickListener implements Listener {
    @EventHandler
    public void onPlayerRightClickEvent(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;
        CustomPlayerManager.getPlayer(event.getPlayer().getUniqueId()).castCustomItemAbility();
    }
}
