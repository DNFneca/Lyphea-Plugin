package me.DNFneca.lypheaAPI.manager;

import me.DNFneca.lypheaAPI.GUI.GUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.*;

public class GUIManager implements Listener {
    private static final Map<UUID, GUI> GUIs = new HashMap<>(0);

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        for (Map.Entry<UUID, GUI> gui : GUIs.entrySet()) {
            if (gui.getValue().getInventory().equals(event.getView().getTopInventory())) {
                event.setCancelled(true);
                gui.getValue().click(event.getSlot());
                return;
            }
        }
    }

    public static void addGUI(GUI gui) {
        GUIs.put(gui.getId(), gui);
    }

    public static GUI getGUI(UUID id) {
        return GUIs.get(id);
    }
}
