package com.example.adars.jacekpoprawa.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.adars.jacekpoprawa.DomainModel.City;

import java.util.ArrayList;

public class CityDAO implements IDAO<City> {

    private Activity activity;
    public CityDAO(Activity activity)
    {
        this.activity = activity;
    }

    @Override
    public ArrayList<City> getAll() {
        ArrayList<City> list = new ArrayList<>();

        SQLiteDatabase base = activity.openOrCreateDatabase("baza.db", Context.MODE_PRIVATE,  null);

        String[] columns = {"idCity", "name", "idCountry"};
        Cursor cursor =  base.query("City",null,null,null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            City city = new City();
            city.setID(cursor.getInt(1));
            city.setName(cursor.getString(2));
            city.setCountryID(cursor.getInt(3));
            list.add(city);
            cursor.moveToNext();
        }

        return list;
    }

    @Override
    public City getOneByID(int ID) {
        return null;
    }

    @Override
    public void insert(City city) {

    }
}
