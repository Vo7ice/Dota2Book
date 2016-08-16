package com.cn.guojinhu.dota2book.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by guojin.hu on 2016/8/16.
 */

public class CustomerResponseConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final String name;

    CustomerResponseConverter(Gson gson, TypeAdapter<T> adapter, String name) {
        this.gson = gson;
        this.adapter = adapter;
        this.name = name;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        /*Reader reader = value.charStream();
        try {
            return gson.fromJson(reader, type);
        } finally {
            try {
                reader.close();
            } catch (IOException ignored) {
            }
        }*/
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            String body = value.string();
            JSONObject json = new JSONObject(body);
            Log.d("Vo7ice","name-->"+name);
            if (json.has(name)) {
                Object obj = json.get(name);
                body = obj.toString();
                return adapter.fromJson(body);
            } else {
                return adapter.read(jsonReader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                value.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
