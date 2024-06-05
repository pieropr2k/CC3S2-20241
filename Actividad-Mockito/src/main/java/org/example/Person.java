package org.example;

import java.time.LocalDate;

public class Person {
    int id;
    String firstName;
    String lastName;
    LocalDate date;
    public Person (int id, String firstName, String lastName, LocalDate date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
    }
    public String getFirst() {
        return firstName;
    }
}
