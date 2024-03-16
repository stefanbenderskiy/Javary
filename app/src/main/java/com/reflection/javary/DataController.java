package com.reflection.javary;

import android.content.SharedPreferences;
import android.content.res.Resources;

import java.util.ArrayList;

public class DataController {
    private final SharedPreferences dataSource;

    public DataController(SharedPreferences dataSource) {
        this.dataSource = dataSource;
    }

    public SharedPreferences getDataSource() {
        return dataSource;
    }
    public int getInt(String key,int defValue){
        return  dataSource.getInt(key,defValue);
    }
    public void setInt(String key,int value){
        SharedPreferences.Editor editor = dataSource.edit();
        if (dataSource.contains(key)) {
            editor.remove(key);
        }
        editor.putInt(key,value);
        editor.apply();


    }
    public String getString(String key,String defValue){
        return dataSource.getString(key,defValue);
    }
    public void setString(String key,String value){

        SharedPreferences.Editor editor = dataSource.edit();
        if (dataSource.contains(key)) {
            editor.remove(key);
        }
        editor.putString(key,value);
        editor.apply();


    }
    public boolean getBoolean(String key,boolean defValue){
        return  dataSource.getBoolean(key,defValue);
    }
    public void setBoolean(String key,Boolean value){

        SharedPreferences.Editor editor = dataSource.edit();
        if (dataSource.contains(key)) {
            editor.remove(key);
        }
        editor.putBoolean(key,value);
        editor.apply();


    }
    public float getFloat(String key,float defValue){
        return  dataSource.getFloat(key,defValue);
    }
    public void setFloat(String key,float value){

        SharedPreferences.Editor editor = dataSource.edit();
        if (dataSource.contains(key)) {
            editor.remove(key);
        }
        editor.putFloat(key,value);
        editor.apply();


    }
    public void clear(){
        SharedPreferences.Editor editor = dataSource.edit();
        editor.clear();
        editor.apply();
    }
    public void remove(String key) {
        SharedPreferences.Editor editor = dataSource.edit();
        editor.remove(key);
        editor.apply();
    }
    public boolean contains(String key){
        return  dataSource.contains(key);
    }

}
