package me.DNFneca.lyphea.listener;

import me.DNFneca.lyphea.Lyphea;
import me.DNFneca.lyphea.item.CustomItem;
import me.DNFneca.lyphea.item.CustomItemAbility;
import me.DNFneca.lyphea.manager.CustomItemManager;
import me.DNFneca.lyphea.manager.CustomPlayerManager;
import me.DNFneca.lyphea.player.CustomPlayer;
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
