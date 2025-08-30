package me.DNFneca.lypheaAPI.stat;

import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Stat {
    private final String displayName;
    private final String hexTextColor;
    private final List<String> itemDescription;
    private final List<String> playerDescription;
    @Getter
    private final float defaultValue;

    public Stat(Component displayName, List<Component> itemDescription, List<Component> playerDescription, TextColor textColor, float defaultValue) {
        this.displayName = GsonComponentSerializer.gson().serialize(displayName);
        List<String> stringItemDescription = new ArrayList<>();
        for (Component component : itemDescription) {
            stringItemDescription.add(GsonComponentSerializer.gson().serialize(component));
        }
        this.itemDescription = stringItemDescription;
        stringItemDescription.clear();
        for (Component component : playerDescription) {
            stringItemDescription.add(GsonComponentSerializer.gson().serialize(component));
        }
        this.hexTextColor = textColor.asHexString();
        this.playerDescription = stringItemDescription;
        this.defaultValue = defaultValue;
    }

    public Component getDisplayName() {
        return GsonComponentSerializer.gson().deserialize(displayName);
    }

    public List<Component> getItemDescription() {
        List<Component> list = new ArrayList<>();
        for (String string : itemDescription) {
            list.add(GsonComponentSerializer.gson().deserialize(string));
        }
        return list;
    }

    public List<Component> getPlayerDescription() {
        List<Component> list = new ArrayList<>();
        for (String string : playerDescription) {
            list.add(GsonComponentSerializer.gson().deserialize(string));
        }
        return list;
    }

    public TextColor getHexTextColor() {
        return TextColor.fromHexString(hexTextColor);
    }
}
