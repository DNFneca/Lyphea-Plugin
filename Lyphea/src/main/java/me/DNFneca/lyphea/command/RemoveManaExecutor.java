package me.DNFneca.lyphea.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.DNFneca.lypheaAPI.manager.CustomPlayerManager;
import me.DNFneca.lypheaAPI.player.PlayerField;
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
        PlayerField<?> playerMana = CustomPlayerManager.getPlayer(target.getUniqueId()).getField("mana");
        player.sendMessage(Component.text("Your current mana \""+playerMana.getCurrentValue(Double.class)+"\""));
        playerMana.setCurrentValue(((Double) playerMana.getCurrentValue(Double.class)) - amount);
        player.sendMessage(Component.text("Current mana: " + playerMana.getCurrentValue(Double.class)));
        return true;
    }
}
