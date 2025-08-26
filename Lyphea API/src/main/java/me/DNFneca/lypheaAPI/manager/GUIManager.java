package me.DNFneca.lypheaAPI.manager;

import lombok.Getter;
import me.DNFneca.lypheaAPI.GUI.GUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GUIManager implements Listener {
    private static final ArrayList<GUI> GUIs = new ArrayList<>(0);

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        List<GUI> relevantGUIs = new ArrayList<>(0);
        for (GUI gui : GUIs) {
            if (gui.getInventory().equals(event.getClickedInventory())) {
                relevantGUIs.add(gui);
                event.setCancelled(true);
            }
        }
        for (GUI gui : relevantGUIs) {
            gui.click(event.getSlot());
        }
    }

    public static void addGUI(GUI gui) {
        GUIs.add(gui);
    }

    public static GUI findGUIById(UUID id) {
        for (GUI baseGUI : GUIs) {
            if (baseGUI.getId().equals(id)) {
                return baseGUI;
            }
        }
        return null;
    }

    public static GUI findGUIById(String id) {
        return findGUIById(UUID.fromString(id));
    }
}
