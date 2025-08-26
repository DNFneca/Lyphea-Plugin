package me.DNFneca.lypheaAPI.GUI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GUIItem {
    private ItemStack item;
    private Runnable onClick;
}
