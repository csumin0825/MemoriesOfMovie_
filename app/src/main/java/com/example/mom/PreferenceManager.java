package com.example.mom;


import android.content.Context;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


/**
 * 데이터 저장 및 로드 클래스
 */

public class PreferenceManager {

    public static final String PREFERENCES_NAME = "rebuild_preference";


    private static final String DEFAULT_VALUE_STRING = "";

    private static final boolean DEFAULT_VALUE_BOOLEAN = false;

    private static final int DEFAULT_VALUE_INT = -1;

    private static final long DEFAULT_VALUE_LONG = -1L;

    private static final float DEFAULT_VALUE_FLOAT = -1F;

    private static Gson gson = new GsonBuilder().create();


    private static SharedPreferences getPreferences(Context context) {

        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

    }
    public static void setObject(Context context, String key, ArrayList<String> dataArr){
        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        JSONArray a = new JSONArray();

        for (int i = 0; i < dataArr.size(); i++) {
            String string = gson.toJson(dataArr.get(i), String.class);
            a.put(string);
        }
        if (!dataArr.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.apply();
//        editor.commit();
    }

    public static ArrayList<String> getObject(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        String json = prefs.getString(key, null);
        ArrayList<String> dataArr = new ArrayList<String>();
        Gson gson =new GsonBuilder().create();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String data = gson.fromJson( a.get(i).toString() , String.class);
                    dataArr.add(data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return dataArr;

    }



    /**
     * String 값 저장
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setString(Context context, String key, String value) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(key, value);

        editor.commit();

    }


    /**
     * boolean 값 저장
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setBoolean(Context context, String key, boolean value) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean(key, value);

        editor.commit();

    }


    /**
     * int 값 저장
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setInt(Context context, String key, int value) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt(key, value);

        editor.commit();

    }


    /**
     * long 값 저장
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setLong(Context context, String key, long value) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong(key, value);

        editor.commit();

    }


    /**
     * float 값 저장
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setFloat(Context context, String key, float value) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putFloat(key, value);

        editor.commit();

    }


    /**
     * String 값 로드
     *
     * @param context
     * @param key
     * @return
     */

    public static String getString(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        String value = prefs.getString(key, DEFAULT_VALUE_STRING);

        return value;

    }


    /**
     * boolean 값 로드
     *
     * @param context
     * @param key
     * @return
     */

    public static boolean getBoolean(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        boolean value = prefs.getBoolean(key, DEFAULT_VALUE_BOOLEAN);

        return value;

    }


    /**
     * int 값 로드
     *
     * @param context
     * @param key
     * @return
     */

    public static int getInt(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        int value = prefs.getInt(key, DEFAULT_VALUE_INT);

        return value;

    }


    /**
     * long 값 로드
     *
     * @param context
     * @param key
     * @return
     */

    public static long getLong(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        long value = prefs.getLong(key, DEFAULT_VALUE_LONG);

        return value;

    }


    /**
     * float 값 로드
     *
     * @param context
     * @param key
     * @return
     */

    public static float getFloat(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        float value = prefs.getFloat(key, DEFAULT_VALUE_FLOAT);

        return value;

    }


    /**
     * 키 값 삭제
     *
     * @param context
     * @param key
     */

    public static void removeKey(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor edit = prefs.edit();

        edit.remove(key);

        edit.commit();

    }


    /**
     * 모든 저장 데이터 삭제
     *
     * @param context
     */

    public static void clear(Context context) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor edit = prefs.edit();

        edit.clear();

        edit.commit();

    }

}