package me.DNFneca.lyphea;

import co.aikar.commands.PaperCommandManager;
import me.DNFneca.lyphea.command.GiveCustomItemExecutor;
import me.DNFneca.lyphea.command.RemoveManaExecutor;
import me.DNFneca.lyphea.item.CustomItem;
import me.DNFneca.lyphea.item.CustomItemAbility;
import me.DNFneca.lyphea.item.Items;
import me.DNFneca.lyphea.listener.PlayerJoinListener;
import me.DNFneca.lyphea.listener.PlayerQuitListener;
import me.DNFneca.lyphea.listener.PlayerRightClickListener;
import me.DNFneca.lyphea.manager.CustomItemManager;
import me.DNFneca.lyphea.manager.CustomPlayerManager;
import me.DNFneca.lyphea.util.LoreUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class Lyphea extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new RemoveManaExecutor());
        commandManager.getCommandCompletions().registerCompletion("customItems", c -> {
            List<String> listOfCustomItems = new ArrayList<>(0);
            for (Map.Entry<NamespacedKey, CustomItem> item : CustomItemManager.getItemRegistry().entrySet()) {
                listOfCustomItems.add(item.getKey().toString());
            }
            return listOfCustomItems;
        });
        commandManager.registerCommand(new GiveCustomItemExecutor());
//
//        this.getServer().getPluginManager().registerEvents(new ChatMessageListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerRightClickListener(), this);

        Items.register();

        for (Player player : this.getServer().getOnlinePlayers()) {
            CustomPlayerManager.registerPlayer(player.getUniqueId());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Player player : this.getServer().getOnlinePlayers()) {
            CustomPlayerManager.unregisterPlayer(player.getUniqueId());
        }
    }


    public static Lyphea getInstance() {
        return JavaPlugin.getPlugin(Lyphea.class);
    }
}
