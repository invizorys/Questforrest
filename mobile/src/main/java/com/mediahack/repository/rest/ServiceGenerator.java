package com.mediahack.repository.rest;

import com.mediahack.util.SharedPrefHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Roma on 05.10.2016.
 */

public class ServiceGenerator {
    private static final String API_BASE_URL = "http://192.168.43.85:8080";
//    private static final String API_BASE_URL = "http://192.168.43.166:8080";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass, boolean authHeader) {
        if (authHeader) {
            HttpLoggingInterceptor logging
                    = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient.addInterceptor(logging);
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request original = chain.request();

                    String s = SharedPrefHelper.getToken();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("token", SharedPrefHelper.getToken());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);

//                    Request newRequest = chain.request().newBuilder()
//                            .addHeader("Accept", "application/json;versions=1")
//                            .addHeader("token", SharedPrefHelper.getToken())
//                            .build();
//
//                    return chain.proceed(newRequest);
                }
            });
        }

        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
