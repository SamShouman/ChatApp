package com.technologygate.toters.ViewModels;

import android.app.Activity;

import com.technologygate.toters.Models.DatabaseHelper;
import com.technologygate.toters.Models.SingletonDesignPattern;
import com.technologygate.toters.Models.User;
import com.technologygate.toters.R;
import com.technologygate.toters.SharedPreferences.GeneratedFirstTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivityViewModel {

    Activity activity;
    DatabaseHelper db;
    // i only chose 4 pics to prevent increasing the size of the apk
    int[] profiles = {R.drawable.profile1, R.drawable.profile2, R.drawable.profile3, R.drawable.profile4};

    public MainActivityViewModel(Activity activity) {

        this.activity = activity;
        db = SingletonDesignPattern.getDatabase(activity);
    }

    //this only runs for the 1st time
    public ArrayList<User> generateUsers() {

        ArrayList<User> users = new ArrayList<>();

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();


        for (int i = 0; i < 200; i++) {// to generate 200 user

            StringBuilder salt = new StringBuilder();
            int nameSize = rnd.nextInt(13);
            int generatedProfile = generateProfile();

            if (nameSize < 3) // since there's no name less than 3 chars then if the generated size < 3 we set it to 3
                nameSize = 3;

            while (salt.length() < nameSize) { //append the randomized letters

                int index = (int) (rnd.nextFloat() * letters.length());
                salt.append(letters.charAt(index));
            }

            User user = new User();
            String generatedName = salt.toString();

            user.setId(i);
            user.setName(generatedName);
            user.setLastMessage("");
            user.setTimeStamp(Calendar.getInstance().getTime().toString());
            user.setProfile(profiles[generatedProfile]); // generatedProfile is basically the index chosen between 0-3
            users.add(user);


        }

        db.addUser(users);

        GeneratedFirstTime.setGenerated(activity.getApplicationContext(), "true");

        return users;

    }

    private int generateProfile() {
        Random random = new Random();
        return random.nextInt(3);
    }

    public ArrayList<User> loadUsers() {

        return db.getUsers();

    }

    public boolean checkIfGenerated() {
        GeneratedFirstTime.getSharedPreferences(activity.getApplicationContext());

        if (GeneratedFirstTime.getGenerated(activity.getApplicationContext()).equals(""))
            return false;

        return true;
    }
}
