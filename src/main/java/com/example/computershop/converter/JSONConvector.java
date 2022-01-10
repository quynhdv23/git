package com.example.computershop.converter;

import com.example.computershop.dto.request.UpdateOrganizationForm;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.lang.reflect.Field;
import java.util.List;

public class JSONConvector {
    public static String toJSON(Object object) throws JSONException, IllegalAccessException {
        String str = "";
        Class c = object.getClass();
        JSONObject jsonObject = new JSONObject();
        for (Field field : c.getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            String value = String.valueOf(field.get(object));
            jsonObject.put(name, value);
        }
        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }
//
//
//    public static String toJSON(List list ) throws JSONException, IllegalAccessException {
//        JSONArray jsonArray = new JSONArray();
//        for (Object i : list) {
//            String jstr = toJSON(i);
//            JSONObject jsonObject = new JSONObject(jstr);
//            jsonArray.put(jsonArray);
//        }
//        return jsonArray.toString();
//    }

    public static void main(String[] args) throws JSONException, IllegalAccessException {
        UpdateOrganizationForm updateOrganizationForm=new UpdateOrganizationForm();
        JSONConvector.toJSON(updateOrganizationForm);
    }
}
