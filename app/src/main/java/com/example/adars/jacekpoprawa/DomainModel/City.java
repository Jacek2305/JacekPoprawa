package com.example.adars.jacekpoprawa.DomainModel;

/**
 * Created by Adam Bachorz on 06.02.2019.
 */
public class City extends Entity {

    private String name;
    private int countryID;

    public City() {
    }

    public City(int ID, String name, int countryID) {
        this.ID = ID;
        this.name = name;
        this.countryID = countryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
