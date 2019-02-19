package com.example.adars.jacekpoprawa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adars.jacekpoprawa.Core.Settings;
import com.example.adars.jacekpoprawa.DAO.CityDAO;
import com.example.adars.jacekpoprawa.DAO.CountryDAO;
import com.example.adars.jacekpoprawa.DAO.ProductDAO;
import com.example.adars.jacekpoprawa.DomainModel.City;
import com.example.adars.jacekpoprawa.DomainModel.Country;
import com.example.adars.jacekpoprawa.DomainModel.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCities;
    private Spinner spinnerCountries;
    private EditText editTextProductName;
    private Button buttonAdd;

    private ArrayList<City> _cities;
    private ArrayList<Country> _countries;
    private CityDAO _cityDAO;
    private CountryDAO _countryDAO;
    private ProductDAO _productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _cityDAO = new CityDAO(this);
        _countryDAO = new CountryDAO(this);
        _productDAO = new ProductDAO(this);
        _countries = _countryDAO.getAll();
        _cities = _cityDAO.getAll();

        ArrayList<String> citiesStr = new ArrayList<>();
        ArrayList<String> countiriesStr = new ArrayList<>();
        for (City c : _cities) citiesStr.add(c.getName());
        for (Country c : _countries) countiriesStr.add(c.getName());

        // do zaimplementowania
        spinnerCities = findViewById(R.id.spinner_cities);
        spinnerCountries = findViewById(R.id.spinner_countries);
        editTextProductName = findViewById(R.id.edit_text_product_name);
        buttonAdd = findViewById(R.id.button_add);
        setDataToSpinner(spinnerCities, citiesStr);
        setDataToSpinner(spinnerCountries, countiriesStr);

        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int countryID = (int) spinnerCountries.getSelectedItemId();
                //_cities = _cityDAO.getAllByCountryID();
                ArrayList<String> citiesStr = new ArrayList<>();
                for (City c : _cities) citiesStr.add(c.getName());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cityID = _cities.get((int)spinnerCities.getSelectedItemId()).getID();
                int countryID = _countries.get((int)spinnerCountries.getSelectedItemId()).getID();
                String productName = buttonAdd.getText().toString().trim();

                if (productName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nazwa produktu nie może być pusta", Toast.LENGTH_LONG).show();
                    return;
                }

                Product product = new Product();
                product.setName(productName);
                product.setCityID(cityID);
                product.setCountryID(countryID);
                //_productDAO.insert(product);
            }
        });


//        SQLiteDatabase base = openOrCreateDatabase("baza.db", Context.MODE_PRIVATE,  null);
//
//        /* Tworzenie tabel jeśli te nie istnieją */
//        base.execSQL(Settings.tableCountry);
//        base.execSQL(Settings.tableCity);
//        base.execSQL(Settings.tableProduct);
//
//        base.close();

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

    void setDataToSpinner(Spinner spinner, List<String> data) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}
