package com.tsj.dat153.database;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

import com.tsj.dat153.model.Person;
import com.tsj.dat153.oblig1.R;

/**
 * Created by Joakim on 14.01.2017.
 */

public class DAO extends Application{
    private static List<Person> personList = new ArrayList<Person>();
    private static int score = 0;
    private static int count = 0;

    @Override
    public void onCreate(){
        Person p1 = new Person("Jerry", BitmapFactory.decodeResource(this.getResources(), R.drawable.seinfeld_jerry));
        Person p2 = new Person("George", BitmapFactory.decodeResource(this.getResources(), R.drawable.george_costanza));
        Person p3 = new Person("Kramer", BitmapFactory.decodeResource(this.getResources(), R.drawable.kramer));
        Person p4 = new Person("Lionel", BitmapFactory.decodeResource(this.getResources(), R.drawable.lionel_richie));
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
    }
    public static void addPerson(Person p){
        personList.add(p);
    }

    //
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
