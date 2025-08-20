package me.DNFneca.lyphea.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.DNFneca.lyphea.manager.CustomPlayerManager;
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
    public boolean onCommand(@NotNull CommandSender sender, @Name("target") @Flags("other") @NotNull Player target, @Name("amount") @Default("10") @NotNull Double amount) {
        if (!sender.isOp() || !(sender instanceof Player player)) return false;
        player.sendMessage(Component.text("Current mana: " + CustomPlayerManager.players.get(player.getUniqueId()).getField("mana").currentValue));
        Double newMana = (Double) (CustomPlayerManager.players.get(player.getUniqueId()).getField("mana").currentValue);
        newMana -= amount;
        CustomPlayerManager.players.get(player.getUniqueId()).registerField("mana", newMana, "Mana");
        player.sendMessage(Component.text("Current mana: " + CustomPlayerManager.players.get(player.getUniqueId()).getField("mana").currentValue));
        return true;
    }
}
