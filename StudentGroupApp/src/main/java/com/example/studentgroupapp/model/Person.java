package com.example.studentgroupapp.model;
import javafx.beans.property.*;
import java.time.LocalDate;
/**
 * Клас модель для студента (Person).
 */

public class Person {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final IntegerProperty groupCode;
    private final ObjectProperty<LocalDate> birthday;

    /**
     * Конструктор за замовчуванням.
     */
    public Person() {
        this(null, null, 0, null);
    }

    /**
     * Конструктор із деякими початковими даними.
     */
    public Person(String firstName, String lastName, int groupCode, LocalDate birthday) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.groupCode = new SimpleIntegerProperty(groupCode);
        this.birthday = new SimpleObjectProperty<>(birthday);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public int getGroupCode() {
        return groupCode.get();
    }

    public void setGroupCode(int groupCode) {
        this.groupCode.set(groupCode);
    }

    public IntegerProperty groupCodeProperty() {
        return groupCode;
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }
}
