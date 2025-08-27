package me.DNFneca.lypheaAPI.listener;

import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import me.DNFneca.lypheaAPI.player.CustomPlayer;
import me.DNFneca.lypheaAPI.player.collection.CollectionType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBreakBlockListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        CustomPlayer customPlayer = CustomPlayerManager.getPlayer(event.getPlayer().getUniqueId());

        Block nmsBlock = ((CraftBlock) event.getBlock()).getNMS().getBlock();
        double hardness = ((double) Math.round(nmsBlock.defaultDestroyTime() * 100) / 100);

        customPlayer.getCollections().get(CollectionType.MINING).addCollectedAmount((float) hardness);
        event.getPlayer().sendMessage(
                Component.text("You received ").color(NamedTextColor.WHITE)
                        .append(Component.text(" ( +" + hardness + " )").color(NamedTextColor.GRAY))
                        .append(Component.text(" in Mining Collection, you now have")).color(NamedTextColor.WHITE)
                        .append(Component.text(" ( " + customPlayer.collection(CollectionType.MINING) + " )").color(NamedTextColor.GRAY)));
    }
}
