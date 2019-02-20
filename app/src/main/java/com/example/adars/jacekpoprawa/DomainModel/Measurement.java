package com.example.adars.jacekpoprawa.DomainModel;

/**
 * Created on 20.02.2019.
 */
public class Measurement extends Entity {

    private float accValue;
    private int userID;

    public float getAccValue() {
        return accValue;
    }

    public void setAccValue(float accValue) {
        this.accValue = accValue;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "accValue=" + accValue +
                ", userID=" + userID +
                ", name='" + name + '\'' +
                '}';
    }
}
