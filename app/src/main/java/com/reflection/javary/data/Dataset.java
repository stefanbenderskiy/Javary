package com.reflection.javary.data;

import android.content.SharedPreferences;

public class Dataset {
    private static String VALID_CHARACTERS= "ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz1234567890";
    private String name;
    private String path;

    private final DataBase dataBase;
    private static final String DATAITEM_SEPARATOR= ".";
    private static final String VALUE_SEPARATOR= ":";

    public int getInt(String key,int defValue) {
        return dataBase.getSource().getInt(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key,defValue);
    }
    public void setInt(String key,int value) {
        SharedPreferences.Editor editor = dataBase.getSource().edit();
        editor.remove(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key);
        editor.putInt(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key,value);
        editor.apply();
    }
    public String getString(String key,String defValue) {
        return dataBase.getSource().getString(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key,defValue);
    }
    public void setString(String key,String value) {
        SharedPreferences.Editor editor = dataBase.getSource().edit();
        editor.remove(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key);
        editor.putString(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key,value);
        editor.apply();
    }
    public float getFloat(String key,float defValue) {
        return dataBase.getSource().getFloat(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key,defValue);
    }
    public void setFloat(String key,Float value) {
        SharedPreferences.Editor editor = dataBase.getSource().edit();
        editor.remove(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key);
        editor.putFloat(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key,value);
        editor.apply();
    }
    public long getLong(String key,long defValue) {
        return dataBase.getSource().getLong(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key,defValue);
    }
    public void setLong(String key,long value) {
        SharedPreferences.Editor editor = dataBase.getSource().edit();
        editor.remove(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key);
        editor.putLong(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key,value);
    }


    public void clear(){
        Object[] keys = dataBase.getSource().getAll().keySet().toArray();

        for (int i =0;i<keys.length;){
            String key= (String) keys[i];
            if (key.contains(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key)){
                SharedPreferences.Editor editor = dataBase.getSource().edit();
                editor.remove(key);
                editor.apply();
            }

        }
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public void remove(String key){
        SharedPreferences.Editor editor = dataBase.getSource().edit();
        editor.remove(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key);
    }
    public Dataset(DataBase dataBase, String path, String name) {
        this.dataBase =dataBase;
        this.path = path;
        this.name = name;
    }

    public boolean contains(String key) {
        return dataBase.getSource().contains(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key);
    }
}
