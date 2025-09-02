package me.DNFneca.lypheaAPI.ability.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Entity;

@Getter
public class CustomItemEnchantmentEvent {
    protected Entity subject;
    protected Entity object;

    public CustomItemEnchantmentEvent(Entity subject, Entity object) {
        this.subject = subject;
        this.object = object;
    }
}
