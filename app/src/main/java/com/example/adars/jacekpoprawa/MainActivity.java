package com.example.adars.jacekpoprawa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.adars.jacekpoprawa.Core.Settings;
import com.example.adars.jacekpoprawa.DAO.CityDAO;
import com.example.adars.jacekpoprawa.DAO.CountryDAO;
import com.example.adars.jacekpoprawa.DAO.ProductDAO;
import com.example.adars.jacekpoprawa.DomainModel.City;
import com.example.adars.jacekpoprawa.DomainModel.Country;
import com.example.adars.jacekpoprawa.DomainModel.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase base = openOrCreateDatabase("baza.db", Context.MODE_PRIVATE,  null);

        /* Tworzenie tabel jeśli te nie istnieją */
        base.execSQL(Settings.tableCountry);
        base.execSQL(Settings.tableCity);
        base.execSQL(Settings.tableProduct);

        base.close();

        /*Testowanie danych (można później wykomentować*/
        CityDAO cityDAO = new CityDAO(this);
        CountryDAO countryDAO = new CountryDAO(this);
        ProductDAO productDAO = new ProductDAO(this);

        /*Test insert (Tak się wrzuca obiekty do bazy. Analogicznie dla innych Encji)*/
//        City newCity = new City();
//        newCity.setName("Warszawa");
//        newCity.setCountryID(1);
//        cityDAO.insert(newCity);

        ArrayList<City> cities = cityDAO.getAll();
        ArrayList<Country> countries = countryDAO.getAll();
        ArrayList<Product> products = productDAO.getAll();

        String result = "Państwa: \n";
        for (Country country : countries) result += country.getName() + "\n";

        result += "\n Miasta: \n";
        for (City city : cities) result += city.getName() + "\n";

        result += "\n Kraje wg ID kraju = 1: \n";
        ArrayList<City> citiesByCountryID = cityDAO.getAllByCountryID(1);
        for (City city : citiesByCountryID) result += city.getName() + "\n";

        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

    }
}
