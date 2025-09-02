package me.DNFneca.lypheaAPI.ability;

import lombok.Getter;
import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.ability.event.CustomItemEnchantmentEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
public class CustomItemEnchantment {
    private BiFunction<CustomItemEnchantmentEvent, Float, Float> applyEffect;
    private Component displayName;
    private List<Component> description;
    private CustomItemEnchantmentApplicationType applicationType;
    private CustomItemEnchantmentType type;

    public CustomItemEnchantment(Component displayName, List<Component> description,CustomItemEnchantmentApplicationType customItemEnchantmentApplicationType, CustomItemEnchantmentType customItemEnchantmentType , BiFunction<CustomItemEnchantmentEvent, Float, Float> applyEffect) {
        this.displayName = displayName;
        this.description = description;
        this.applicationType = customItemEnchantmentApplicationType;
        this.type = customItemEnchantmentType;
        this.applyEffect = applyEffect;
    }
}
