package com.example.adars.jacekpoprawa.DomainModel;

/**
 * Created by Adam Bachorz on 06.02.2019.
 */
public class City extends Entity {

    private int countryID;

    public City() {
    }

    public City(int ID, String name, int countryID) {
        this.ID = ID;
        this.name = name;
        this.countryID = countryID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
