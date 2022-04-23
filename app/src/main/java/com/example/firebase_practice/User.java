package com.example.firebase_practice;
import android.util.Log;

public class User {

    String firstName, lastName, age;

    public String getFirstName() {

        Log.i("ABCDEF", "First " + firstName);

        return firstName;
    }

    public String getLastName() {

        Log.i("ABCDEF", "last " + lastName);

        return lastName;
    }

    public String getAge() {

        Log.i("ABCDEF", "age " + age);

        return age;
    }
}
