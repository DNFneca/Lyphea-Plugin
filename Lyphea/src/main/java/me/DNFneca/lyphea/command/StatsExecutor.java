package me.DNFneca.lyphea.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.DNFneca.lyphea.GUI.StatsGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("stats")
@Description("Shows you all of your stats")
public class StatsExecutor extends BaseCommand {

    @Default
    public boolean onCommand(@NotNull CommandSender sender) {
        if (!(sender instanceof Player player)) return false;
        new StatsGUI().open(player);
        return true;
    }
}