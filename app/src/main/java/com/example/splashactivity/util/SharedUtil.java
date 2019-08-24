package com.example.splashactivity.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtil {
    public static void saveInShared(Context context , boolean value){
        SharedPreferences musicApp = context.getSharedPreferences("musicApp", Context.MODE_PRIVATE);
        musicApp.edit().putBoolean("isplaying",value).commit();
    }

    public static boolean getInShared(Context context){
        SharedPreferences musicApp = context.getSharedPreferences("musicApp", Context.MODE_PRIVATE);
        return musicApp.getBoolean("isplaying",false);
    }

    public static void saveSongShared(Context context , String name){
        SharedPreferences musicApp = context.getSharedPreferences("musicApp", Context.MODE_PRIVATE);
        musicApp.edit().putString("song",name).commit();
    }

    public static String getPlayingSong(Context context){
        SharedPreferences musicApp = context.getSharedPreferences("musicApp", Context.MODE_PRIVATE);
        return musicApp.getString("song","");
    }
}
