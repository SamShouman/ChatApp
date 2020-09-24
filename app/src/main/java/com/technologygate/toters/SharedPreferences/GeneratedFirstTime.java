package com.technologygate.toters.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

// this class is used to determine if the user has generated the users or not
public class GeneratedFirstTime {

    public static String GENERATED = "generated";

    public static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }


    public static void setGenerated(Context ctx, String generated)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(GENERATED, generated);
        editor.apply();
    }

    public static String getGenerated(Context ctx)
    {
        return getSharedPreferences(ctx).getString(GENERATED, "");
    }
}
