package me.DNFneca.lypheaAPI.player.collection;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

public class Collection {
    @Getter
    private final CollectionType collectionType;
    private String collectionName;
    @Getter
    private float collectedAmount;

    public Collection(CollectionType collectionType, Component collectionName, int collectedAmount) {
        this.collectionType = collectionType;
        this.setCollectionName(collectionName);
        this.collectedAmount = collectedAmount;
    }


    public void addCollectedAmount(float amount) {
        this.collectedAmount += amount;
    }

    public Component getCollectionName() {
        return GsonComponentSerializer.gson().deserialize(this.collectionName);
    }

    public void setCollectionName(Component collectionName) {
        this.collectionName = GsonComponentSerializer.gson().serialize(collectionName);
    }

}
