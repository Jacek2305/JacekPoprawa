package com.example.adars.jacekpoprawa.DAO;

import android.app.Activity;
import android.database.Cursor;

import com.example.adars.jacekpoprawa.DomainModel.Product;
import com.example.adars.jacekpoprawa.DomainModel.User;

import java.util.ArrayList;

/**
 * Created on 20.02.2019.
 */
public class UserDAO extends ADAO<User> {

    public UserDAO(Activity activity) {
        super(activity);
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> list = new ArrayList<>(); // utworzenie listy do zgromadzenia danych

        connectDB(); // otwarcie bazy danych (jeżeli z jakiegoś powodu jest zamknięta)

        /* Utworzenie kursora który będzie wskazywał na dane z konkretnej tabeli w bazie */
        Cursor cursor =  base.query(entityName(),null,null,null, null, null, null);
        cursor.moveToFirst(); // przejście do pierwszego wiersza

        /* Ta pętla będzie działaś tak długo, aż kursor nie przeleci przez wszystkie wiersze */
        while (!cursor.isAfterLast()) {

            /* Tworzymy obiekt klasy będącej odpowiednikiem w bazie (analogicznie w każdej klasie DAO)*/
            User user = new User();
            /* Ustawiamy kolejne parametry klasy za pomocą kursorów
               indeksujemy od 0 i zgodnie z kolejnością kolumn w tabeli
            */
            user.setID(cursor.getInt(0));  // jeżeli jakiś parametr jest liczbą to uzywamy getInt
            user.setName(cursor.getString(1)); // jeżeli tekstem to getString
            list.add(user); // umieszczamy gotowy obiekt na liście
            cursor.moveToNext(); // na końcu kroku pętli przeskakujemy do następnego wiersza
        }
        base.close();
        return list; // zwracamy gotowa listę
    }

    @Override
    public User getOneByID(int ID) {
        return null;
    }

    @Override
    public void insert(User user) {

    }
}
