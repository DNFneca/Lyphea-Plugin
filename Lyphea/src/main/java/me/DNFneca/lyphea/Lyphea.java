package me.DNFneca.lyphea;

import co.aikar.commands.PaperCommandManager;
import me.DNFneca.lyphea.command.GiveCustomItemExecutor;
import me.DNFneca.lyphea.command.RemoveManaExecutor;
import me.DNFneca.lyphea.command.StatsExecutor;
import me.DNFneca.lyphea.item.Items;
import me.DNFneca.lypheaAPI.item.CustomItem;
import me.DNFneca.lypheaAPI.registry.CustomItemRegistry;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Lyphea extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new RemoveManaExecutor());
        commandManager.getCommandCompletions().registerCompletion("customItems", c -> {
            List<String> listOfCustomItems = new ArrayList<>(0);
            for (Map.Entry<NamespacedKey, CustomItem> item : CustomItemRegistry.INSTANCE.entrySet()) {
                listOfCustomItems.add(item.getKey().toString());
            }
            return listOfCustomItems;
        });
        commandManager.registerCommand(new GiveCustomItemExecutor());
        commandManager.registerCommand(new StatsExecutor());

        Items.register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static Lyphea getInstance() {
        return JavaPlugin.getPlugin(Lyphea.class);
    }
}
