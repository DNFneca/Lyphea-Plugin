package me.DNFneca.lypheaAPI.listener;

import me.DNFneca.lypheaAPI.GUI.MainMenu;
import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class PlayerInventoryClickListener implements Listener {
    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof CraftPlayer player) {
            CustomPlayerManager.getPlayer(player).reloadStats();
            if (event.getSlot() == 8) {
                event.setCancelled(true);
                new MainMenu().open(player);
            }
        }
    }
}
