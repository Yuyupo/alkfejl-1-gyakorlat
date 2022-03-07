package com.example.dao;

import com.example.model.Contact;
import java.util.List;

public interface ContactDAO {
    // Adatbazis es applikacio kozotti osszekottetes
    // Lekerdezesek osszerakasa, eredmenyek feldolgozasa
    List<Contact> findAll();
    Contact save(Contact contact);
    void delete(Contact contact);
}
