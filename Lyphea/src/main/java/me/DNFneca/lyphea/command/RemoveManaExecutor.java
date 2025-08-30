package me.DNFneca.lyphea.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import me.DNFneca.lypheaAPI.player.CustomPlayer;
import me.DNFneca.lypheaAPI.player.PlayerField;
import me.DNFneca.lypheaAPI.registry.CustomStatRegistry;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("removeMana")
@Description("Removes mana from the mentioned player")
public class RemoveManaExecutor extends BaseCommand {

    @Default
    @Syntax("<target> <amount>")
    @CommandCompletion("@players @range:-10000-10000")
    public boolean onCommand(@NotNull CommandSender sender, @Name("target") @Flags("other") @NotNull Player target, @Name("amount") @Default("10") @NotNull Float amount) {
        if (!sender.isOp() || !(sender instanceof Player player)) return false;
        CustomPlayer customPlayer = CustomPlayerManager.getPlayer(target.getUniqueId());
        float stat = customPlayer.getStat("mana");
        player.sendMessage(Component.text("Your current mana: \""+ stat +"\""));
        customPlayer.stat("mana", stat + amount);
        stat = customPlayer.getStat("mana");
        player.sendMessage(Component.text("New mana: \""+ stat +"\""));
        return true;
    }
}
