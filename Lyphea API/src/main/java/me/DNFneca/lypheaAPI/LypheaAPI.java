package me.DNFneca.lypheaAPI;

import me.DNFneca.lypheaAPI.ability.CustomItemEnchantment;
import me.DNFneca.lypheaAPI.listener.*;
import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import me.DNFneca.lypheaAPI.manager.GUIManager;
import me.DNFneca.lypheaAPI.manager.PacketManager;
import me.DNFneca.lypheaAPI.player.CustomPlayer;
import me.DNFneca.lypheaAPI.registry.CustomEnchantmentRegistry;
import me.DNFneca.lypheaAPI.registry.CustomStatRegistry;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class LypheaAPI extends JavaPlugin {
    public static Logger logger;

    @Override
    public void onEnable() {
        logger = getLogger();
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new GUIManager(), this);
        getServer().getPluginManager().registerEvents(new PlayerRightClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerBreakBlockListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerArmorChangeListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerBreakItemListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemHeldListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerSwapHandItemsListener(), this);
        PacketManager.init();
        CustomStatRegistry.init();
        CustomEnchantmentRegistry.init();

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

    public static LypheaAPI getInstance() {
        return JavaPlugin.getPlugin(LypheaAPI.class);
    }

}
