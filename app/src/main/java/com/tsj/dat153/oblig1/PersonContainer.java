package com.tsj.dat153.oblig1;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sindre on 12.01.2017.
 */

public class PersonContainer extends Application {
    private List<Person> personList;

    public PersonContainer(){
        personList = new ArrayList<Person>();
    }

    public void addPerson(Person p){
        personList.add(p);
    }

    public List<Person> getPersonList() {
        return new ArrayList(personList);
    }
}
