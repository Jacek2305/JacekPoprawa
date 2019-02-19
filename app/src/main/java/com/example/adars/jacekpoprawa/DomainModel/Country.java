package com.example.adars.jacekpoprawa.DomainModel;

/**
 * Created by Adam Bachorz on 06.02.2019.
 */
public class Country extends Entity {

    private String name;

    public Country() {
    }

    public Country(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
