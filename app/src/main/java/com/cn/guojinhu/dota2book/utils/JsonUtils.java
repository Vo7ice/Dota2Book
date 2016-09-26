package com.cn.guojinhu.dota2book.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.cn.guojinhu.dota2book.bean.Equipments;
import com.cn.guojinhu.dota2book.bean.Heroes;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by guojin.hu on 2016/9/18.
 */

public class JsonUtils {

    public static final String KEY_HEROESLIST_FILE_NAME = "Heroes.json";
    public static final String KEY_EQUIPMENT_FILE_NAME = "";

    public static List<Heroes.Hero> getHeroesFromAssets(Context context) throws IOException {
        InputStream is = context.getAssets().open(KEY_HEROESLIST_FILE_NAME);
        String result = getJsonString(is);
        Gson gson = new Gson();
        Heroes heroes = gson.fromJson(result, Heroes.class);
        return heroes.Heroes;
    }

    public static List<Equipments.Equipment> getEquipmentFromAssets(Context context) throws IOException{
        InputStream is = context.getAssets().open(KEY_EQUIPMENT_FILE_NAME);
        String result = getJsonString(is);
        Gson gson = new Gson();
        Equipments equipments = gson.fromJson(result, Equipments.class);
        return equipments.equipments;
    }

    @NonNull
    private static String getJsonString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        final StringBuilder sb = new StringBuilder();
        final char[] buffer = new char[1024];
        int len = -1;
        while ((len = br.read(buffer)) > 0) {
            sb.append(buffer, 0, len);
        }
        OtherUtils.close(br);
        return sb.toString();
    }
}
