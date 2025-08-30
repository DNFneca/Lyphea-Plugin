package me.DNFneca.lypheaAPI.registry;

import com.google.gson.reflect.TypeToken;
import lombok.NoArgsConstructor;
import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.stat.Stat;
import me.DNFneca.lypheaAPI.util.LoreUtils;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;

import java.lang.constant.Constable;
import java.util.ArrayList;

@NoArgsConstructor
public class CustomStatRegistry extends Registry<Stat> {
    public static final CustomStatRegistry INSTANCE = new CustomStatRegistry();

    public static Stat getCustomStat(NamespacedKey key) {
        return INSTANCE.getValue(key);
    }

    public static Stat getCustomStat(String name) {
        return INSTANCE.getValue(new NamespacedKey(LypheaAPI.getInstance(), name));
    }

    public static void init() {
        INSTANCE.register(new NamespacedKey(LypheaAPI.getInstance(), "mana"), new Stat(
                LoreUtils.createLoreLine("Mana"),
                LoreUtils.createDescriptionLoreLine("Bonus Mana gifted to you by this item."),
                LoreUtils.createDescriptionLoreLine("Mana is the energy that powers your spells and abilities."),
                NamedTextColor.AQUA,
                0
        ));
        INSTANCE.register(new NamespacedKey(LypheaAPI.getInstance(), "damage"), new Stat(
                LoreUtils.createLoreLine("Damage"),
                LoreUtils.createDescriptionLoreLine("Bonus Damage gifted to you by this item."),
                LoreUtils.createDescriptionLoreLine("Damage is the amount of raw damage dealt to an entity."),
                NamedTextColor.RED,
                0
        ));
    }
}
