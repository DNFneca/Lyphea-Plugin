package me.DNFneca.lyphea.GUI;

import me.DNFneca.lypheaAPI.GUI.GUI;
import me.DNFneca.lypheaAPI.GUI.GUIItem;
import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import me.DNFneca.lypheaAPI.option.GUIPlaceOption;
import me.DNFneca.lypheaAPI.player.CustomPlayer;
import me.DNFneca.lypheaAPI.player.collection.CollectionType;
import me.DNFneca.lypheaAPI.util.ItemUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class StatsGUI extends GUI {
    public StatsGUI() {
        super(Component.text("Stats"), 54);
        getOptions().put(GUIPlaceOption.SHOULD_PLACE_EXIT, true);
        getOptions().put(GUIPlaceOption.SHOULD_PLACE_BACK, true);
    }

    @Override
    public void open(Player player) {
        GUIItem miningCollection = ItemUtils.makeGUIItemOfType(Material.IRON_PICKAXE, "Mining Collection");
        CustomPlayer customPlayer = CustomPlayerManager.getPlayer(player.getUniqueId());

        double collection = customPlayer.collection(CollectionType.MINING);

        collection = Math.min(collection, 70000);

        for (int i = 0; i*10000 < collection; i++) {
            getItems().put(10 + i, ItemUtils.makeGUIItemOfType(Material.GREEN_STAINED_GLASS_PANE, "Your " + getNumber(i) + " Collection Tier"));
        }

        getItems().put((int) (10 + collection/10000), miningCollection);




        super.open(player);
    }

    private String getNumber(int number) {
        return switch (number+1) {
            case 1 -> "First";
            case 2 -> "Second";
            case 3 -> "Third";
            case 4 -> "Fourth";
            case 5 -> "Fifth";
            case 6 -> "Sixth";
            case 7 -> "Seventh";
            case 8 -> "Eighth";
            case 9 -> "Ninth";
            case 10 -> "Tenth";
            default -> throw new IllegalStateException("Unexpected value: " + number);
        };
    }
}
