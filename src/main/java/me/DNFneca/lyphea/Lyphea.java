package me.DNFneca.lyphea;

import co.aikar.commands.PaperCommandManager;
import me.DNFneca.lyphea.commands.RemoveManaExecutor;
import me.DNFneca.lyphea.listener.PlayerJoinListener;
import me.DNFneca.lyphea.listener.PlayerQuitListener;
import me.DNFneca.lyphea.manager.CustomPlayerManager;
import me.DNFneca.lyphea.player.CustomPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lyphea extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new RemoveManaExecutor());
//
//        this.getServer().getPluginManager().registerEvents(new ChatMessageListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
//        this.getServer().getPluginManager().registerEvents(new PlayerLeftServerListener(), this);


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
