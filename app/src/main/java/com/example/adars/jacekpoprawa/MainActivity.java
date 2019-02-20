package com.example.adars.jacekpoprawa;

import android.content.Context;
import android.content.Intent;
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
import com.example.adars.jacekpoprawa.DomainModel.Entity;
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
//        _cities = _cityDAO.getAll();

        // do zaimplementowania
        spinnerCities = findViewById(R.id.spinner_cities);
        spinnerCountries = findViewById(R.id.spinner_countries);
        editTextProductName = findViewById(R.id.edit_text_product_name);
        buttonAdd = findViewById(R.id.button_add);
        setDataToSpinner(spinnerCountries, _countries.toArray(new Country[0]));

        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Pozycja nr" + spinnerCountries.getSelectedItemPosition());
                int selectedElement = spinnerCountries.getSelectedItemPosition();
                int countryID = _countries.get(selectedElement).getID();
                _cities = _cityDAO.getAllByCountryID(countryID);
                setDataToSpinner(spinnerCities, _cities.toArray(new City[0]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cityID = _cities.get(spinnerCities.getSelectedItemPosition()).getID();
                int countryID = _countries.get(spinnerCountries.getSelectedItemPosition()).getID();
                String productName = editTextProductName.getText().toString().trim();

                if (productName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nazwa produktu nie może być pusta", Toast.LENGTH_LONG).show();
                    return;
                }

                Product product = new Product();
                product.setName(productName);
                product.setCityID(cityID);
                product.setCountryID(countryID);
                System.out.println(product.toString());
                _productDAO.insert(product);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
//        ProductDAO productDAO = new ProductDAO(this);
//        ArrayList<Product> products = productDAO.getAll();
//        String result = "Produkty \n";
//        for (Product product : products) result += product.getName() + "\n";
//        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

    }

    void setDataToSpinner(Spinner spinner, Entity[] entities) {
        String[] content = new String[entities.length];
        for (int i = 0; i < entities.length; i++) content[i] = entities[i].getName();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, content);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    }

}
