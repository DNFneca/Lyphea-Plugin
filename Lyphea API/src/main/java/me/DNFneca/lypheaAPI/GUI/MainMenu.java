package me.DNFneca.lypheaAPI.GUI;

import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import me.DNFneca.lypheaAPI.util.ItemUtils;
import me.DNFneca.lypheaAPI.util.LoreUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainMenu extends GUI{
    public MainMenu() {
        super(Component.text("Main Menu"), 54);
    }

    @Override
    public void open(Player player) {
        GUIItem yourStats = ItemUtils.makeGUIItemOfType(Material.PAPER, "Your stats");
        yourStats.getItem().editMeta(itemMeta -> {
            itemMeta.displayName(Component.text("Your stats"));
            itemMeta.lore(new ArrayList<>(0));
            List<Component> lore = new ArrayList<>(0);
            for (Map.Entry<String, Float> entry : CustomPlayerManager.getPlayer(player).getStats().entrySet()) {
                lore.add(LoreUtils.createLoreLine(entry.getKey() + ": " + entry.getValue()));
            }
            itemMeta.lore(lore);
        });
        getItems().put(26, yourStats);
        super.open(player);
    }
}
