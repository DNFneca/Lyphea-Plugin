package me.DNFneca.lyphea.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.DNFneca.lypheaAPI.manager.CustomItemManager;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("giveitem")
@Description("Gives you one of the custom items")
public class GiveCustomItemExecutor extends BaseCommand {

    @Default
    @Syntax("<customItem>")
    @CommandCompletion("@customItems")
    public boolean onCommand(@NotNull CommandSender sender, @Name("customItem") @NotNull NamespacedKey customItem) {
        if (!sender.isOp() || !(sender instanceof Player player)) return false;
        player.give(CustomItemManager.getItemRegistry().get(customItem).getItemStack());
        return true;
    }
}
