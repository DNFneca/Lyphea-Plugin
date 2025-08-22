package me.DNFneca.lyphea.player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Value;
import me.DNFneca.lyphea.Lyphea;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

import java.lang.constant.Constable;
import java.lang.reflect.Type;

public class PlayerField<T> {
    private String name;
    private String currentValue;

    public PlayerField(Component name, T value) {
        this.name = GsonComponentSerializer.gson().serialize(name);
        this.currentValue = new Gson().toJson(value, value.getClass());
    }

    public Component getName() {
        return GsonComponentSerializer.gson().deserialize(name);
    }

    public void setName(Component name) {
        this.name = GsonComponentSerializer.gson().serialize(name);
    }

    public <K, returnType> returnType getCurrentValue(TypeToken<K> typeToken) {
        return new Gson().fromJson(currentValue, typeToken.getType());
    }

    public <K extends Type, returnType> returnType getCurrentValue(K typeToken) {
        return new Gson().fromJson(currentValue, typeToken);
    }

    public <K> void setCurrentValue(K value) {
        this.currentValue = new Gson().toJson(value, value.getClass());
    }
}
