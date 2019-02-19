package com.example.adars.jacekpoprawa.Core;

public abstract class Settings {
    public static String tableCity =
            "CREATE TABLE IF NOT EXISTS City (idCity INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name STRING NOT NULL, idCountry INTEGER REFERENCES Country (idCountry) NOT NULL);";
    public static String tableCountry =
            "CREATE TABLE IF NOT EXISTS Country (idCountry INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name STRING NOT NULL)";
    public static String tableProduct =
            "CREATE TABLE IF NOT EXISTS Product (idProduct INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, idCity INTEGER REFERENCES CIty (idCity) NOT NULL, idCountry INTEGER REFERENCES Country (idCountry) NOT NULL)";

}
