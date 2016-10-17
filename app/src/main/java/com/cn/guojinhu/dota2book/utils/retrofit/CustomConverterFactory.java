package com.cn.guojinhu.dota2book.utils.retrofit;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by guojin.hu on 2016/8/16.
 */

public class CustomConverterFactory extends Converter.Factory {
    public static CustomConverterFactory create() {
        return create(new Gson());
    }

    public static CustomConverterFactory create(Gson gson) {
        return new CustomConverterFactory(gson);
    }

    private final Gson gson;

    private CustomConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        String name = "data";
        for (Annotation annotation : annotations) {
            if (annotation instanceof Docid) {
                name = ((Docid) annotation).value();
                break;
            }
        }
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new CustomerResponseConverter<>(gson, adapter, name);
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return new CustomerRequestConverter<>(gson, type);
    }

}
