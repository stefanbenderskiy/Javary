package com.reflection.javary.data;

import android.content.Context;
import android.content.SharedPreferences;

public class DataBase {
    private final String name;

    private final SharedPreferences source;

    public SharedPreferences getSource() {
        return source;
    }
    public DataBase(Context context, String name) {
        this.name = name;
        source = context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }
    public void deleteAll(){
        SharedPreferences.Editor editor = source.edit();
        editor.clear();
        editor.apply();
    }

}
