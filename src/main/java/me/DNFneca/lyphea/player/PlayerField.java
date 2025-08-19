package me.DNFneca.lyphea.player;

import java.lang.constant.Constable;

public class PlayerField<T extends Constable> {
    public String name;
    public T currentValue;
    public T initialValue = null;

    public PlayerField(String name, T currentValue) {
        this.name = name;
        this.currentValue = currentValue;
    }
}
