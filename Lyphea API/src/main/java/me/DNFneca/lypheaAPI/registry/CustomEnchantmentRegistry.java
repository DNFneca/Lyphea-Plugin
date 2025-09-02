package me.DNFneca.lypheaAPI.registry;

import me.DNFneca.lypheaAPI.ability.CustomItemEnchantment;
import me.DNFneca.lypheaAPI.ability.CustomItemEnchantmentApplicationType;
import me.DNFneca.lypheaAPI.ability.CustomItemEnchantmentType;
import me.DNFneca.lypheaAPI.item.CustomItem;
import me.DNFneca.lypheaAPI.util.LoreUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class CustomEnchantmentRegistry extends Registry<CustomItemEnchantment> {
    public static final CustomEnchantmentRegistry INSTANCE = new CustomEnchantmentRegistry();

    public static void init() {
        INSTANCE.register("test", new CustomItemEnchantment(
                LoreUtils.createLoreLine("Test"),
                LoreUtils.createDescriptionLoreLine("Test Lore for The Test Enchantment"),
                CustomItemEnchantmentApplicationType.ANY,
                CustomItemEnchantmentType.FLAT,
                (customItemEnchantmentEvent, level) -> {
            Entity subject = customItemEnchantmentEvent.getSubject();
            Entity object = customItemEnchantmentEvent.getObject();
            return level;
        }));
        INSTANCE.register("sharpness", new CustomItemEnchantment(
                LoreUtils.createLoreLine("Sharpness"),
                LoreUtils.createDescriptionLoreLine("Enhances the effectiveness of the weapon by 10% per level"),
                CustomItemEnchantmentApplicationType.SWORD,
                CustomItemEnchantmentType.PERCENTAGE,
                (customItemEnchantmentEvent, level) -> .1F * level));
    }
}
