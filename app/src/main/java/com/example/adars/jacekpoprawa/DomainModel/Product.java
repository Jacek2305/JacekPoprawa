package com.example.adars.jacekpoprawa.DomainModel;

/**
 * Created by Adam Bachorz on 06.02.2019.
 */
public class Product extends Entity {

    private String name;
    private int cityID;
    private int countryID;

    public Product() {
    }

    public Product(int ID, String name, int cityID) {
        this.ID = ID;
        this.name = name;
        this.cityID = cityID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }
}
