package com.example.adars.jacekpoprawa.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.adars.jacekpoprawa.DomainModel.Country;
import com.example.adars.jacekpoprawa.DomainModel.Product;

import java.util.ArrayList;


public class CountryDAO extends ADAO<Country> {


    public CountryDAO(Activity activity) {
        super(activity);
    }

    @Override
    public ArrayList<Country> getAll() {

        ArrayList<Country> list = new ArrayList<>(); // utworzenie listy do zgromadzenia danych

        connectDB(); // otwarcie bazy danych (jeżeli z jakiegoś powodu jest zamknięta)

        /* Utworzenie kursora który będzie wskazywał na dane z konkretnej tabeli w bazie */
        Cursor cursor =  base.query("Country",null,null,null, null, null, null);
        cursor.moveToFirst(); // przejście do pierwszego wiersza

        /* Ta pętla będzie działaś tak długo, aż kursor nie przeleci przez wszystkie wiersze */
        while (!cursor.isAfterLast()) {

            /* Tworzymy obiekt klasy będącej odpowiednikiem w bazie (analogicznie w każdej klasie DAO)*/
            Country country = new Country();
            /* Ustawiamy kolejne parametry klasy za pomocą kursorów
               indeksujemy od 0 i zgodnie z kolejnością kolumn w tabeli
            */
            country.setID(cursor.getInt(0));  // jeżeli jakiś parametr jest liczbą to uzywamy getInt
            country.setName(cursor.getString(1)); // jeżeli tekstem to getString
            list.add(country); // umieszczamy gotowy obiekt na liście
            cursor.moveToNext(); // na końcu kroku pętli przeskakujemy do następnego wiersza
        }
        base.close();
        return list; // zwracamy gotowa listę
    }

    @Override
    public Country getOneByID(int ID) {

        /* Tu sprawa wygląda podobnie tylko bez pętli bo zwracamy 1 element */

        connectDB();

        /* Potrzebujemy tablicę z nazwami kolumn. Nie wolno przegapić żadnej columny !!! */
        String[] columns = {"idCountry", "name"};

        /* Teraz w trzecim parametrze kursora ustawiliśmy warunek
           potrzebujemy wiersza o ID równym tej liczbie, którą podajemy w argumencie metody (nawias u góry)
           W drugim parametrze jest nasza tablica z nazwami kolumn*/
        Cursor cursor =  base.query("Country", columns,"idCountry = " + ID,null,null, null, null, null);
        cursor.moveToFirst(); // przechodimy tylko do pierwszego elementu i ustawiamy dane poniżej

        Country country = new Country();
        country.setID(cursor.getInt(0));
        country.setName(cursor.getString(1));
        base.close(); // zamykamy połączenie
        return country; // zwracamy tylko ten element
    }



    @Override
    public void insert(Country country) {

        /*Tutaj umieszczamy nowe dane w bazie*/

        connectDB(); // otwieramy połączenie
        ContentValues record = new ContentValues(); // tworzymy obiekt do tworzenia rekordu w bazie

        /* Teraz po kolei umieszczamy kolejne wartości, które mają znaleźć się w bazie (oprócz ID bo ono będzie generowane
           automatycznie).
           Po lewej dokładna nazwa tabeli w bazie, a po prawej wartość */
        record.put("name", country.getName());
        base.insert("Country", null, record); // zapisujemy rekord w bazie
        base.close(); // zamykamy połączenie
    }
}
