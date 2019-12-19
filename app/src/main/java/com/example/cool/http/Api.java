package com.example.cool.http;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
    private static final String TAG = "Api";

    private Api() {
    }

    public static <T> void enqueue(Class clazz, String methodName, Callback<T> callback) {
        Object obj = retrofit.create(clazz);
        try {
            Method method = clazz.getMethod(methodName);
            Call<T> call = (Call<T>) method.invoke(obj);
            call.enqueue(callback);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
