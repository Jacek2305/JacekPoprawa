package com.example.adars.jacekpoprawa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.adars.jacekpoprawa.Core.Settings;
import com.example.adars.jacekpoprawa.DAO.CityDAO;
import com.example.adars.jacekpoprawa.DomainModel.City;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase base = openOrCreateDatabase("baza.db", Context.MODE_PRIVATE,  null);

        base.execSQL(Settings.tableCountry);
        base.execSQL(Settings.tableCity);
        base.execSQL(Settings.tableProduct);

        base.execSQL("INSERT INTO Country (name) VALUES ('Polska')");
        base.execSQL("INSERT INTO Country (name) VALUES ('Niemcy')");
        base.execSQL("INSERT INTO City (name, idCountry) VALUES ('Bydgoszcz', 1)");
        base.execSQL("INSERT INTO City (name, idCountry) VALUES ('Berlin', 2)");

        base.close();

        CityDAO cityDAO = new CityDAO(this);

        ArrayList<City> l = cityDAO.getAll();
        String result = "";

        for (City city : l) result += city.getName() + "\n";

        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

    }
}
