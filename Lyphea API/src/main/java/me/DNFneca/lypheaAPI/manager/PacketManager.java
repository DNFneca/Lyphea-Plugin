package me.DNFneca.lypheaAPI.manager;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import me.DNFneca.lypheaAPI.GUI.ListGUI;
import me.DNFneca.lypheaAPI.GUI.SearchSignGUI;
import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.option.GUIPlaceOption;
import me.DNFneca.lypheaAPI.player.CustomPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PacketManager {
    public static ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
    
    public static void init() {
        protocolManager.addPacketListener(new PacketAdapter(LypheaAPI.getInstance(), PacketType.Play.Server.OPEN_SIGN_EDITOR) {
            @Override
            public void onPacketSending(PacketEvent packetEvent) {
                LypheaAPI.logger.info(String.valueOf(packetEvent.getPacket().getBooleans().read(0)));
            }
        });
        protocolManager.addPacketListener(new PacketAdapter(LypheaAPI.getInstance(), PacketType.Play.Client.UPDATE_SIGN) {
            @Override
            public void onPacketReceiving(PacketEvent packetEvent) {
                Player player = packetEvent.getPlayer();

                BlockPosition blockPosition = new BlockPosition((int) player.getLocation().getX(), (int) player.getLocation().getY() - 2, (int) player.getLocation().getZ());
                player.sendBlockChange(blockPosition.toLocation(player.getWorld()), player.getWorld().getBlockAt(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ()).getBlockData());


                new BukkitRunnable() {
                    @Override
                    public void run() {
                        ListGUI openGUI = new ListGUI(Component.text("Search Results"), SearchSignGUI.searchItemsAndBlocks(packetEvent.getPacket().getStringArrays().read(0)[0]));
                        CustomPlayer customPlayer = CustomPlayerManager.getPlayer(player.getUniqueId());
                        openGUI.setParentGUI(GUIManager.getGUI(customPlayer.getCurrentGUI()));
                        openGUI.getOptions().put(GUIPlaceOption.SHOULD_PLACE_BACK, true);
                        openGUI.getOptions().put(GUIPlaceOption.SHOULD_PLACE_SEARCH, false);
                        openGUI.open(player);
                    }
                }.runTaskLater(LypheaAPI.getInstance(), 2L);
            }
        });
    }
}


