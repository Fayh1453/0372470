package ca.cours5b5.lucroy.serialisation;

import com.google.gson.Gson;

import java.util.Map;

public class Jsonification {

    private static Gson gson;

    public static Map<String, Object> enObjetJson(String json){
        Map<String, Object>objetJson=gson.fromJson(json, Map.class);
        return objetJson;
    }

    public static String enChaine(Map<String, Object> objetJson){
       String chaineJson = gson.toJson(objetJson);
       return chaineJson;
    }

}
