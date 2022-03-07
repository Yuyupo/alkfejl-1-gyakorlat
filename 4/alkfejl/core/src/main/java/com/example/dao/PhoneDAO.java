package com.example.dao;

import com.example.model.*;

import java.util.List;

public interface PhoneDAO {

    List<Phone> findAllByContactId(Contact contact);
    List<Phone> findAllByContactId(int contactId);
    Phone save(Phone p, int contactId);
    void delete(Phone p);
}
