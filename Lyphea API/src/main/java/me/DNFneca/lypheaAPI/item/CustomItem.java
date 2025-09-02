package me.DNFneca.lypheaAPI.item;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import me.DNFneca.lypheaAPI.LypheaAPI;
import me.DNFneca.lypheaAPI.registry.CustomEnchantmentRegistry;
import me.DNFneca.lypheaAPI.registry.CustomStatRegistry;
import me.DNFneca.lypheaAPI.util.LoreUtils;
import me.DNFneca.lypheaAPI.util.RomanNumeral;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public record CustomItem(@Getter ItemStack itemStack) {
    public CustomItem(ItemStack itemStack) {
        this.itemStack = itemStack;
        setCustomItem();
        reloadItem();
    }

    private void setDisplayName(Component displayName) {
        setCustomItem();
        itemStack.editMeta(itemMeta -> itemMeta.displayName(displayName));
    }

    public void setCustomDisplayName(Component displayName) {
        setCustomItem();
        setPersistentData("customItemDisplayName", GsonComponentSerializer.gson().serialize(displayName));
    }

    private void setLore(List<Component> lore) {
        setCustomItem();
        itemStack.editMeta(itemMeta -> itemMeta.lore(lore));
    }

    public void setCustomLore(List<Component> lore) {
        setCustomItem();
        List<String> loreStrings = new ArrayList<>(lore.size());
        for (Component component : lore) {
            loreStrings.add(GsonComponentSerializer.gson().serialize(component));
        }
        setPersistentData("customItemLore", loreStrings);
    }

    public void setCustomItemAbility(NamespacedKey customItemAbility) {
        setCustomItem();
        setPersistentData("customItemAbility", customItemAbility.key().toString());
    }

    public void setCustomItemEnchantments(Map<NamespacedKey, Integer> enchantments) {
        setCustomItem();
        Map<String, Integer> enchantmentsList = new HashMap<>(enchantments.size());
        for (NamespacedKey namespacedKeyIntegerEntry : enchantments.keySet()) {
            enchantmentsList.put(namespacedKeyIntegerEntry.toString(), enchantments.get(namespacedKeyIntegerEntry));
        }
        setPersistentData("customItemEnchantments", enchantmentsList);
    }


    public void setCustomItemEnchantmentsSimple(Map<String, Integer> enchantments) {
        setCustomItem();
        Map<String, Integer> enchantmentsList = new HashMap<>(enchantments.size());
        for (String namespacedKeyIntegerEntry : enchantments.keySet()) {
            enchantmentsList.put(namespacedKeyIntegerEntry, enchantments.get(namespacedKeyIntegerEntry));
        }
        setPersistentData("customItemEnchantments", enchantmentsList);
    }

    public Map<NamespacedKey, Integer> getCustomItemEnchantments() {
        Map<String, Integer> enchantmentsList = getPersistentData("customItemEnchantments", new TypeToken<Map<String, Integer>>() {});
        if (enchantmentsList == null) return new HashMap<>(0);
        Map<NamespacedKey, Integer> enchantments = new HashMap<>(0);
        for (Map.Entry<String, Integer> namespacedKeyIntegerEntry : enchantmentsList.entrySet()) {
            enchantments.put(NamespacedKey.fromString(namespacedKeyIntegerEntry.getKey()), namespacedKeyIntegerEntry.getValue());
        }
        return enchantments;
    }

    public void addCustomItemEnchantment(NamespacedKey enchantment, int level) {
        setCustomItem();
        Map<NamespacedKey, Integer> enchantmentsList = getCustomItemEnchantments();
        enchantmentsList.put(enchantment, level);
        setCustomItemEnchantments(enchantmentsList);
    }

    public void addCustomItemEnchantment(String enchantment, int level) {
        setCustomItem();
        Map<NamespacedKey, Integer> enchantmentsList = getCustomItemEnchantments();
        enchantmentsList.put(new NamespacedKey(LypheaAPI.getInstance(), enchantment), level);
        setCustomItemEnchantments(enchantmentsList);
    }

    public void setItemStats(Map<NamespacedKey, Float> stats) {
        setCustomItem();
        Map<String, Float> statsList = new HashMap<>(stats.size());
        for (NamespacedKey namespacedKey : stats.keySet()) {
            statsList.put(namespacedKey.toString(), stats.get(namespacedKey));
        }
        setPersistentData("customItemStats", statsList);
    }

    public void setItemStatsSimple(Map<String, Float> stats) {
        setCustomItem();
        Map<String, Float> statsList = new HashMap<>(stats.size());
        for (String namespacedKey : stats.keySet()) {
            statsList.put(new NamespacedKey(LypheaAPI.getInstance(), namespacedKey).toString(), stats.get(namespacedKey));
        }
        setPersistentData("customItemStats", statsList);
    }

    private Map<NamespacedKey, Float> getItemStats() {
        Map<String, Float> customItemStats = getPersistentData("customItemStats", new TypeToken<>() {
        });
        if (customItemStats == null) return new HashMap<>(0);
        Map<NamespacedKey, Float> stats = new HashMap<>(0);
        for (Map.Entry<String, Float> stat : customItemStats.entrySet()) {
            stats.put(NamespacedKey.fromString(stat.getKey()), stat.getValue());
        }
        return stats;
    }

    public void addCustomStat(NamespacedKey stat, float value) {
        setCustomItem();
        Map<NamespacedKey, Float> stats = getItemStats();
        stats.put(stat, value);
        setItemStats(stats);
    }

    public void addCustomStat(String stat, float value) {
        setCustomItem();
        Map<NamespacedKey, Float> stats = getItemStats();
        stats.put(new NamespacedKey(LypheaAPI.getInstance(), stat), value);
        setItemStats(stats);
    }

    public void removeCustomStat(NamespacedKey stat) {
        setCustomItem();
        Map<NamespacedKey, Float> stats = getItemStats();
        stats.remove(stat);
        setItemStats(stats);
    }

    public void removeCustomStat(String stat) {
        setCustomItem();
        Map<NamespacedKey, Float> stats = getItemStats();
        stats.remove(new NamespacedKey(LypheaAPI.getInstance(), stat));
        setItemStats(stats);
    }

    public Component getDisplayName() {
        return itemStack.getItemMeta().displayName();
    }

    public Component getCustomItemDisplayName() {
        if (!hasPersistentData("customItemDisplayName")) return null;
        return GsonComponentSerializer.gson().deserialize(getPersistentData("customItemDisplayName", new TypeToken<>() {
        }));
    }

    public List<Component> getLore() {
        return itemStack.getItemMeta().lore();
    }

    public List<Component> getCustomItemLore() {
        List<Component> lore = new ArrayList<>(0);
        if (getPersistentData("customItemLore", new TypeToken<List<String>>() {
        }) == null) return lore;
        for (String string : getPersistentData("customItemLore", new TypeToken<List<String>>() {
        })) {
            lore.add(GsonComponentSerializer.gson().deserialize(string));
        }
        return lore;
    }

    public NamespacedKey getCustomItemAbility() {
        return NamespacedKey.fromString(getPersistentData("customItemAbility", new TypeToken<>() {
        }));
    }

    private void setCustomItem() {
        if (itemStack.getPersistentDataContainer().has(new NamespacedKey(LypheaAPI.getInstance(), "customItemAbility"), PersistentDataType.STRING))
            return;
        itemStack.addItemFlags(ItemFlag.values());
        itemStack.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.editPersistentDataContainer(persistentDataContainer -> {
            persistentDataContainer.set(new NamespacedKey(LypheaAPI.getInstance(), "customItem"), PersistentDataType.BOOLEAN, true);
        });
    }

    @SuppressWarnings("UnstableApiUsage")
    public void setCustomModelData(float customModelData) {
        CustomModelDataComponent customModelDataComponent = itemStack.getItemMeta().getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(customModelData));
        itemStack.editMeta(itemMeta -> itemMeta.setCustomModelDataComponent(customModelDataComponent));
    }

    public static boolean isCustomItem(ItemStack item) {
        return item.getPersistentDataContainer().has(new NamespacedKey(LypheaAPI.getInstance(), "customItem"));
    }


    private void setPersistentData(String key, Object value) {
        itemStack.editPersistentDataContainer(persistentDataContainer -> {
            persistentDataContainer.set(new NamespacedKey(LypheaAPI.getInstance(), key), PersistentDataType.STRING, new Gson().toJson(value));
        });
        reloadItem();
    }

    private <T> T getPersistentData(String key, TypeToken<T> typeToken) {
        return new Gson().fromJson(itemStack.getPersistentDataContainer().get(new NamespacedKey(LypheaAPI.getInstance(), key), PersistentDataType.STRING), typeToken);
    }

    private boolean hasPersistentData(String key) {
        return itemStack.getPersistentDataContainer().has(new NamespacedKey(LypheaAPI.getInstance(), key), PersistentDataType.STRING);
    }

    private void reloadItem() {
        if (!isCustomItem(itemStack)) return;
        if (getCustomItemDisplayName() != null) {
            setDisplayName(getCustomItemDisplayName());
        }
        List<Component> lore = new ArrayList<>(0);
        getItemStats().forEach(((namespacedKey, aFloat) -> {
            lore.add(CustomStatRegistry.getCustomStat(namespacedKey).getDisplayName().color(NamedTextColor.GRAY)
                    .append(
                            LoreUtils.createLoreLine(aFloat > 0 ? " +" + aFloat : " " + aFloat, CustomStatRegistry.getCustomStat(namespacedKey).getHexTextColor())
                    )
            );
        }));

        if (!getCustomItemEnchantments().isEmpty()) {
            int i = 0;
            lore.add(Component.empty());
            Component enchantLine = Component.empty();
            for (NamespacedKey namespacedKey : getCustomItemEnchantments().keySet()) {
                enchantLine = enchantLine.append(
                        LoreUtils.createLoreLine(i > 0 ? ", " : "", NamedTextColor.BLUE)
                        .append(CustomEnchantmentRegistry.INSTANCE.getValue(namespacedKey).getDisplayName().color(NamedTextColor.BLUE))
                        .append(LoreUtils.createLoreLine(" " + RomanNumeral.toRoman(getCustomItemEnchantments().get(namespacedKey)), NamedTextColor.BLUE))
                );
                i++;
            }
            lore.add(enchantLine);
            if (getCustomItemEnchantments().size() <= 5){
                for (NamespacedKey namespacedKey : getCustomItemEnchantments().keySet()) {
                    lore.add(Component.empty());
                    lore.addAll(CustomEnchantmentRegistry.INSTANCE.getValue(namespacedKey).getDescription());
                }
            }
        }

        if (!getItemStats().isEmpty()) {
            lore.add(Component.empty());
            for (NamespacedKey namespacedKey : getItemStats().keySet()) {
                lore.addAll(CustomStatRegistry.getCustomStat(namespacedKey).getItemDescription());
            }
        }

        if (!getCustomItemLore().isEmpty()) {
            lore.add(Component.empty());
            lore.addAll(getCustomItemLore());
        }

        setLore(lore);
    }
}
