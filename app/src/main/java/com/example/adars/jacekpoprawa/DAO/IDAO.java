package com.example.adars.jacekpoprawa.DAO;

import java.util.ArrayList;

/**
 * Created by Adam Bachorz on 06.02.2019.
 */
public interface IDAO<E> {
    ArrayList<E> getAll();
    E getOneByID(int ID);
    void insert(E e);
}
