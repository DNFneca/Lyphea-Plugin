package me.DNFneca.lypheaAPI.item;

import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.player.CustomPlayer;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public record CustomItemAbility(Consumer<CustomPlayer> customPlayerConsumer) {

}
