package me.DNFneca.lypheaAPI;

import me.DNFneca.lypheaAPI.listener.PlayerBreakBlockListener;
import me.DNFneca.lypheaAPI.listener.PlayerJoinListener;
import me.DNFneca.lypheaAPI.listener.PlayerQuitListener;
import me.DNFneca.lypheaAPI.listener.PlayerRightClickListener;
import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import me.DNFneca.lypheaAPI.manager.GUIManager;
import me.DNFneca.lypheaAPI.manager.PacketManager;
import me.DNFneca.lypheaAPI.player.CustomPlayer;
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
        PacketManager.init();
        CustomStatRegistry.init();

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
