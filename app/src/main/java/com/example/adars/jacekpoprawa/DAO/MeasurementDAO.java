package com.example.adars.jacekpoprawa.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.adars.jacekpoprawa.DomainModel.Measurement;
import com.example.adars.jacekpoprawa.DomainModel.User;

import java.util.ArrayList;

/**
 * Created on 20.02.2019.
 */
public class MeasurementDAO extends ADAO<Measurement> {

    public MeasurementDAO(Activity activity) {
        super(activity);
    }

    @Override
    public ArrayList<Measurement> getAll() {
        ArrayList<Measurement> list = new ArrayList<>(); // utworzenie listy do zgromadzenia danych

        connectDB(); // otwarcie bazy danych (jeżeli z jakiegoś powodu jest zamknięta)

        /* Utworzenie kursora który będzie wskazywał na dane z konkretnej tabeli w bazie */
        Cursor cursor =  base.query(entityName(),null,null,null, null, null, null);
        cursor.moveToFirst(); // przejście do pierwszego wiersza

        /* Ta pętla będzie działaś tak długo, aż kursor nie przeleci przez wszystkie wiersze */
        while (!cursor.isAfterLast()) {

            /* Tworzymy obiekt klasy będącej odpowiednikiem w bazie (analogicznie w każdej klasie DAO)*/
            Measurement measurement = new Measurement();
            /* Ustawiamy kolejne parametry klasy za pomocą kursorów
               indeksujemy od 0 i zgodnie z kolejnością kolumn w tabeli
            */
            measurement.setID(cursor.getInt(0));  // jeżeli jakiś parametr jest liczbą to uzywamy getInt
            measurement.setAccValue(cursor.getFloat(1));
            measurement.setName(cursor.getString(2)); // jeżeli tekstem to getString
            measurement.setUserID(cursor.getInt(3));
            list.add(measurement); // umieszczamy gotowy obiekt na liście
            cursor.moveToNext(); // na końcu kroku pętli przeskakujemy do następnego wiersza
        }
        base.close();
        return list; // zwracamy gotowa listę
    }

    @Override
    public Measurement getOneByID(int ID) {
        return null;
    }

    @Override
    public void insert(Measurement measurement) {
        connectDB(); // otwieramy połączenie
        ContentValues record = new ContentValues(); // tworzymy obiekt do tworzenia rekordu w bazie

        /* Teraz po kolei umieszczamy kolejne wartości, które mają znaleźć się w bazie (oprócz ID bo ono będzie generowane
           automatycznie).
           Po lewej dokładna nazwa tabeli w bazie, a po prawej wartość */
        record.put("acc_value", measurement.getAccValue());
        record.put("name", measurement.getName());
        record.put("user_id", measurement.getUserID());
        base.insert(entityName(), null, record); // zapisujemy rekord w bazie
        base.close(); // zamykamy połączenie
    }
}
