package com.example.adars.jacekpoprawa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adars.jacekpoprawa.Core.StandardAccelerometer;
import com.example.adars.jacekpoprawa.DAO.MeasurementDAO;
import com.example.adars.jacekpoprawa.DAO.UserDAO;
import com.example.adars.jacekpoprawa.DomainModel.Entity;
import com.example.adars.jacekpoprawa.DomainModel.Measurement;
import com.example.adars.jacekpoprawa.DomainModel.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private StandardAccelerometer accelerometer;
    private Thread thread;

    private MeasurementDAO measurementDAO;
    private UserDAO userDAO;
    private ArrayList<User> users;
    private ArrayList<Measurement> measurements;

    private Spinner spinner;
    private EditText editTextAcc;
    private EditText editTextAccName;
    private Button buttonAdd;
    private Button buttonSave;
    float x = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        measurementDAO = new MeasurementDAO(this);
        userDAO = new UserDAO(this);

        users = userDAO.getAll();

        spinner = findViewById(R.id.spinner);
        editTextAcc = findViewById(R.id.edit_text_acc);
        editTextAccName = findViewById(R.id.edit_text_name_acc);
        buttonAdd = findViewById(R.id.button_add);
        buttonSave = findViewById(R.id.button_save);
        setDataToSpinner(spinner, users.toArray(new User[0]));


        accelerometer = new StandardAccelerometer(getBaseContext());
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    x = accelerometer.getX();
                }
            }
        });
        thread.start();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextAcc.setText(x+"");
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float accVal = Float.parseFloat(editTextAcc.getText().toString().trim());
                String accName = editTextAccName.getText().toString().trim();
                int spinnerPosition = spinner.getSelectedItemPosition();
                int userID = users.get(spinnerPosition).getID();

                if (accVal == 0.0f || accName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Wszystkie pola muszą być wypełnione", Toast.LENGTH_LONG).show();
                    return;
                }

                Measurement measurement = new Measurement();
                measurement.setName(accName);
                measurement.setAccValue(accVal);
                measurement.setUserID(userID);
                System.out.println(measurement.toString());
                measurementDAO.insert(measurement);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        String result = "Pomiary \n";
        ArrayList<Measurement> measurements = measurementDAO.getAll();
        for (Measurement m : measurements) result += m.getName() + "\n";
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
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

//        setDataToSpinner(spinnerCountries, _countries.toArray(new Country[0]));
//
//        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                System.out.println("Pozycja nr" + spinnerCountries.getSelectedItemPosition());
//                int selectedElement = spinnerCountries.getSelectedItemPosition();
//                int countryID = _countries.get(selectedElement).getID();
//                _cities = _cityDAO.getAllByCountryID(countryID);
//                setDataToSpinner(spinnerCities, _cities.toArray(new City[0]));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        buttonAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int cityID = _cities.get(spinnerCities.getSelectedItemPosition()).getID();
//                int countryID = _countries.get(spinnerCountries.getSelectedItemPosition()).getID();
//                String productName = editTextProductName.getText().toString().trim();
//
//                if (productName.isEmpty()){
//                    Toast.makeText(getApplicationContext(), "Nazwa produktu nie może być pusta", Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                Product product = new Product();
//                product.setName(productName);
//                product.setCityID(cityID);
//                product.setCountryID(countryID);
//                System.out.println(product.toString());
//                _productDAO.insert(product);
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            }
//        });
//
//
////        SQLiteDatabase base = openOrCreateDatabase("baza.db", Context.MODE_PRIVATE,  null);
////
////        /* Tworzenie tabel jeśli te nie istnieją */
////        base.execSQL(Settings.tableCountry);
////        base.execSQL(Settings.tableCity);
////        base.execSQL(Settings.tableProduct);
////
////        base.close();
//
//        /*Testowanie danych (można później wykomentować*/
////        ProductDAO productDAO = new ProductDAO(this);
////        ArrayList<Product> products = productDAO.getAll();
////        String result = "Produkty \n";
////        for (Product product : products) result += product.getName() + "\n";
////        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
//
//    }
//

// }
