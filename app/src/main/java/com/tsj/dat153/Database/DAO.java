package com.tsj.dat153.database;

import java.util.ArrayList;
import java.util.List;

import com.tsj.dat153.model.Person;

/**
 * Created by Joakim on 14.01.2017.
 */

public class DAO {
    private static List<Person> personList = new ArrayList<Person>();

    public static void addPerson(Person p){
        personList.add(p);
    }

    public static List<Person> getPersonList() {
        return new ArrayList(personList);
    }
}
