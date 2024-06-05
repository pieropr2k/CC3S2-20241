package org.example;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public class PersonDB implements PersonRepository{

    @Override
    public Person save(Person person) {
        return null;
    }

    @Override
    public Optional<Person> findById(int id) {
        LocalDate date = LocalDate.of(1906, Month.DECEMBER, 9);
        Person person = new Person(id, "", "", date);
        return Optional.of(person);
        //return Optional.empty();
    }

    @Override
    public List<Person> findAll() {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Person person) {

    }
}
