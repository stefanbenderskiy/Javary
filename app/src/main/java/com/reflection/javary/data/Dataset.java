package com.reflection.javary.data;

import android.content.SharedPreferences;

public class Dataset {
    private static String VALID_CHARACTERS= "ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz1234567890";
    private String name;
    private String path;
    private final Dataset parent;
    private Dataset children;
    private final DataBase dataBase;
    private static final String DATAITEM_SEPARATOR= ".";
    private static final String VALUE_SEPARATOR= ":";

    public int getInt(String key,int defValue) {
        return dataBase.getSource().getInt(path+VALUE_SEPARATOR,defValue);
    }
    public void setInt(String key,int value) {
        SharedPreferences.Editor editor = dataBase.getSource().edit();
        editor.remove(path+VALUE_SEPARATOR+key);
        editor.putInt(path+VALUE_SEPARATOR+key,value);
        editor.apply();
    }
    public String getString(String key,String defValue) {
        return dataBase.getSource().getString(path+VALUE_SEPARATOR,defValue);
    }
    public void setString(String key,String value) {
        SharedPreferences.Editor editor = dataBase.getSource().edit();
        editor.remove(path+VALUE_SEPARATOR+key);
        editor.putString(path+VALUE_SEPARATOR+key,value);
        editor.apply();
    }
    public float getFloat(String key,float defValue) {
        return dataBase.getSource().getFloat(path+VALUE_SEPARATOR,defValue);
    }
    public void setFloat(String key,Float value) {
        SharedPreferences.Editor editor = dataBase.getSource().edit();
        editor.remove(path+VALUE_SEPARATOR+key);
        editor.putFloat(path+VALUE_SEPARATOR+key,value);
        editor.apply();
    }
    public long getLong(String key,long defValue) {
        return dataBase.getSource().getLong(path+VALUE_SEPARATOR,defValue);
    }
    public void setLong(String key,long value) {
        SharedPreferences.Editor editor = dataBase.getSource().edit();
        editor.remove(path+VALUE_SEPARATOR+key);
        editor.putLong(path+VALUE_SEPARATOR+key,value);
    }


    public void clear(){
        Object[] keys = dataBase.getSource().getAll().keySet().toArray();

        for (int i =0;i<keys.length;){
            String key= (String) keys[i];
            if (key.contains(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR)){
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
        editor.remove(path+VALUE_SEPARATOR+key);
    }
    public Dataset(DataBase dataBase, String path, String name) {
        this.dataBase =dataBase;


        for (char c : name.toCharArray()){
            if ((VALID_CHARACTERS+".").contains(String.valueOf(c))){

            }else{

                throw  new InvalidItemNameException("Invalid item name");
            }
        }
        this.path = path;
        for (char c : name.toCharArray()){
            if (VALID_CHARACTERS.contains(String.valueOf(c))){

            }else{

                throw  new InvalidItemNameException("Invalid item name");
            }
        }
        this.name = name;
        String[] parents = path.split(".");
        String parentName = parents[parents.length-1];
        parent= new Dataset(dataBase,path.replace("."+parentName,""),parentName);



    }

    public boolean contains(String key) {
        return dataBase.getSource().contains(path+DATAITEM_SEPARATOR+name+VALUE_SEPARATOR+key);
    }
}
