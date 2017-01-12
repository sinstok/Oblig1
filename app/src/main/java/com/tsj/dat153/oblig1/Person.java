package com.tsj.dat153.oblig1;

/**
 * Created by Sindre on 12.01.2017.
 */

public class Person {
    private String name;
    private String image;

    public Person(String n, String i){
        this.name = n;
        this.image = i;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
