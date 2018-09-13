package ca.cours5b5.lucroy.serialisation;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import ca.cours5b5.lucroy.activites.AParametres;

public class Jsonification {

    private static Gson gson = new GsonBuilder().create();

    public static Map<String, Object> enObjetJson(String json){
        Map<String, Object>objetJson=gson.fromJson(json, Map.class);
        return objetJson;
    }

    public static String enChaine(Map<String, Object> objetJson){
       String chaineJson = gson.toJson(objetJson);
       return chaineJson;
    }

}
