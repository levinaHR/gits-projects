package com.levinahr.asus.tugas12.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
//    @TypeConverter
//    public static ArrayList<String> fromString(String value) {
//        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
//        return new Gson().fromJson(value, listType);
//    }
//
//    @TypeConverter
//    public static String fromArrayList(ArrayList<String> list) {
//        Gson gson = new Gson();
//        String json = gson.toJson(list);
//        return json;
//    }

    @TypeConverter
    public String fromIntegerList(List<Integer> integer) {
        if (integer == null) {
            return (null);
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        String json = gson.toJson(integer, type);
        return json;
    }

    @TypeConverter
    public List<Integer> toIntegerList(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> integerList = gson.fromJson(countryLangString, type);
        return integerList;
    }
}
