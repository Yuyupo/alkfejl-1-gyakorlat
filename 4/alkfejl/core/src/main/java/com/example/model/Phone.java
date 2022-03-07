package com.example.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.*;

public class Phone {
    public enum PhoneType {
        HOME("Home"),
        WORK("Work"),
        MOBILE("Mobile"),
        UNKNOWN("Unknown");

        // Vissza adja a phonetype stringet az erteke (szamok) alapjan
        private final StringProperty value = new SimpleStringProperty(this, "value");

        public String getValue() {
            return value.get();
        }

        public StringProperty valueProperty() {
            return value;
        }

        PhoneType(String value) {
            this.value.set(value);
        }

        @Override
        public String toString() {
            return getValue();
        }
    }

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty number = new SimpleStringProperty(this, "number");
    private ObjectProperty<PhoneType> phoneType= new SimpleObjectProperty<>(this, "phoneType");

    // Getter/setter
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNumber() {
        return number.get();
    }

    public StringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public PhoneType getPhoneType() {
        return phoneType.get();
    }

    public ObjectProperty<PhoneType> phoneTypeProperty() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType.set(phoneType);
    }
}
