package com.example.dao;

import com.example.config.ContactConfiguration;
import com.example.model.Contact;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO{
    // SQL Statements
    private static final String SELECT_ALL_CONTACTS = "SELECT * FROM CONTACT";
    private static final String INSERT_CONTACT = "INSERT INTO CONTACT (name, email, address, dateOfBirth, company, position) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_CONTACT = "UPDATE CONTACT SET name=?, email = ?, address = ?, dateOfBirth=?, company=?, position = ? WHERE id=?";
    private static final String DELETE_CONTACT = "DELETE FROM CONTACT WHERE id = ?";
    private String connectionURL;

    public ContactDAOImpl() {
        // az adatbazis url-je, az application.propertiesbol
        connectionURL = ContactConfiguration.getValue("db.url"); // obtaining DB URL
    }

    @Override
    public List<Contact> findAll() {

        List<Contact> result = new ArrayList<>();

        // nyit connectiont az adatbazishoz, dobhat exceptiont ha nem eri el
        try(Connection c = DriverManager.getConnection(connectionURL);
            // kapcsolathoz uj statement
            Statement stmt = c.createStatement();
            // commit a statement
            ResultSet rs = stmt.executeQuery(SELECT_ALL_CONTACTS)
        ){
            // resultokon vegig iteral es minden bejegyzeshez peldanyositunk egy bean-t
            while(rs.next()){
                Contact contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setAddress(rs.getString("address"));
                Date date = Date.valueOf(rs.getString("dateOfBirth"));
                contact.setDateOfBirth(date == null ? LocalDate.now() : date.toLocalDate());
                contact.setCompany(rs.getString("company"));
                contact.setPosition(rs.getString("position"));
                // beszurjuk a result listaba az adott contactot
                result.add(contact);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // vissza terunk a listaval benne az osszes contacttal
        return result;

    }

    @Override
    public Contact save(Contact contact) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            // Ha nincsen id-ja a contactnak, akkor beszur (INSERT_CONTACT)
            // maskepp updateli a meglevot (UPDATE_CONTACT)
            PreparedStatement stmt = contact.getId() <= 0 ? c.prepareStatement(INSERT_CONTACT, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_CONTACT)
        ){
            if(contact.getId() > 0){ // UPDATE
                stmt.setInt(7, contact.getId());
            }

            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getEmail());
            stmt.setString(3, contact.getAddress());
            stmt.setString(4, contact.getDateOfBirth().toString());
            stmt.setString(5, contact.getCompany());
            stmt.setString(6, contact.getPosition());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return null;
            }

            if(contact.getId() <= 0){ // INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if(genKeys.next()){
                    contact.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return contact;
    }

    @Override
    public void delete(Contact contact) {

        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE_CONTACT);
        ) {
            stmt.setInt(1, contact.getId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
