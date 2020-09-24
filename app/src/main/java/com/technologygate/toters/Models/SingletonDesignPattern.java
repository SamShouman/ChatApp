package com.technologygate.toters.Models;

import android.app.Activity;

public class SingletonDesignPattern {

    private static DatabaseHelper db;

    private SingletonDesignPattern() {

    }

    public static DatabaseHelper getDatabase(Activity activity) {
        if (db == null)
            db = new DatabaseHelper(activity);

        return db;
    }
}
