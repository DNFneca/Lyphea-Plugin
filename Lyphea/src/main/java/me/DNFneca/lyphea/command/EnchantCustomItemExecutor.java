package me.DNFneca.lyphea.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.DNFneca.lypheaAPI.item.CustomItem;
import me.DNFneca.lypheaAPI.registry.CustomEnchantmentRegistry;
import me.DNFneca.lypheaAPI.registry.CustomItemRegistry;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("ench")
@Description("Enchants the custom item you're currently holding")
public class EnchantCustomItemExecutor extends BaseCommand {
    @Default
    @Syntax("<custom enchantment> <enchantment level>")
    @CommandCompletion("@customEnchant @range:1-100")
    public boolean onCommand(@NotNull CommandSender sender, @Name("custom enchantment") @NotNull NamespacedKey customItemNamespace, @Name("enchantment level") @Default("1") @NotNull Integer level) {
        if (!sender.isOp() || !(sender instanceof Player player)) return false;
        CustomItem customItem = new CustomItem(player.getInventory().getItemInMainHand());
        customItem.addCustomItemEnchantment(customItemNamespace, level);
        return true;
    }
}
