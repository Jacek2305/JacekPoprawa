package com.example.adars.jacekpoprawa.DAO;

import com.example.adars.jacekpoprawa.DomainModel.Country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Adam Bachorz on 06.02.2019.
 */
public class CountryDAO implements IDAO<Country>{


    @Override
    public ArrayList<Country> getAll() {
        // inne instrukcje
//        List<Country> countries = Arrays.asList(
//                new Country(1, "kA"),
//                new Country(2, "kB"),
//                new Country(3, "kC"),
//                new Country(4, "kD")
//
//        );

        ArrayList<Country> list = new ArrayList<>();



        return list;
    }

    @Override
    public Country getOneByID(int ID) {
        return null;
    }

    @Override
    public void insert(Country country) {

    }
}
