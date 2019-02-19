package com.example.adars.jacekpoprawa.DomainModel;

/**
 * Created by Adam Bachorz on 06.02.2019.
 */
public abstract class Entity {

    protected int ID;

    public Entity() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
