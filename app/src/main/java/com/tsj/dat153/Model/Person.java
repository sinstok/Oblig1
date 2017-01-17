package com.tsj.dat153.model;

import android.graphics.Bitmap;

/**
 * Created by Sindre on 12.01.2017.
 */

public class Person {
    private String name;
    private Bitmap image;

    public Person(String n, Bitmap i){
        this.name = n;
        this.image = i;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
