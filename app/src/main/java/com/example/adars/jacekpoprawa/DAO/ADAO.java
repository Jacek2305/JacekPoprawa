package com.example.adars.jacekpoprawa.DAO;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * Created on 06.02.2019.
 */

/*Zmiana interfejsu na klasę abstrakcyjną z inną nazwą z uwagi na większe możliwości*/
public abstract class ADAO<E> {

    /* Deklaracja domyślnych pól dla wszystkich klas dziedziczących po ADAO */
    protected Activity activity; // dostęp do aktywności (główny interfejs)
    protected SQLiteDatabase base; // jeden obiekt bazy danych dla wszytskich klas dziedziczących po ADAO

    /* Inicjalizacja tych obiektów wg PDFa*/
    public ADAO(Activity activity) {
        this.activity = activity;
        connectDB();
    }

    protected void connectDB() {
        if (base == null || !base.isOpen()) {
            base = this.activity.openOrCreateDatabase("baza.db", Context.MODE_PRIVATE,  null);
        }
    }

    /* Spokojnie. To tylko taka sztuczka żeby uzyskać dostęp do klasy generycznej */
    public Class genericClass() {
        return ((Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /* Pobieranie nazwy klasy generycznej */
    public String entityName() {
        return genericClass().getSimpleName();
    }

    /* wszytskie funkcje, których użyjemy w klasach pochodnych */
    /* Musimy im ustawić specyfikatory 'public' oraz 'abstract' */
    public abstract ArrayList<E> getAll();
    public abstract E getOneByID(int ID);
    public abstract void insert(E e);

}
