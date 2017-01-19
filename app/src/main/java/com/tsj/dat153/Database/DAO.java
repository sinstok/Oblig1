package com.tsj.dat153.database;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import com.tsj.dat153.model.Person;

/**
 * Created by Joakim on 14.01.2017.
 */

public class DAO {
    private static List<Person> personList = new ArrayList<Person>();
    private static int score = 0;
    private static int count = 0;

    public static void addPerson(Person p){
        personList.add(p);
    }

    public static List<Person> getPersonList() {
        return new ArrayList(personList);
    }

    public static Bitmap getBitmap(String n){
        Bitmap bitmap = null;
        boolean found = false;
        for(int i = 0; i < personList.size() && !found; i++){
            if(personList.get(i).getName().equals(n)){
                bitmap = personList.get(i).getImage();
                found = true;
            }
        }
        return bitmap;
    }

    public static int getCount() {
        return count;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        DAO.score = score;
    }

    public static void setCount(int count) {
        DAO.count = count;
    }
}
