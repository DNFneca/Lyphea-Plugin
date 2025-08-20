package me.DNFneca.lyphea.item;

import lombok.Getter;
import me.DNFneca.lyphea.Lyphea;
import me.DNFneca.lyphea.manager.CustomItemAbilityManager;
import me.DNFneca.lyphea.player.CustomPlayer;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class CustomItemAbility {
    @Getter
    private NamespacedKey key;
    @Getter
    private Consumer<CustomPlayer> customPlayerConsumer;

    @NotNull
    public static CustomItemAbility registerCustomItemAbility(String name, Consumer<CustomPlayer> customPlayerConsumer) {
        CustomItemAbility customItemAbility = new CustomItemAbility();
        customItemAbility.customPlayerConsumer = customPlayerConsumer;
        customItemAbility.key = new NamespacedKey(Lyphea.getInstance(), name);
        CustomItemAbilityManager.registerItem(name, customItemAbility);
        return customItemAbility;
    }
}
