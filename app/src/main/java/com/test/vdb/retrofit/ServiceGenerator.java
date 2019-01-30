package com.test.vdb.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;


import com.test.vdb.utils.AppConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kashif on 29-Jul-18.
 * Retrofit Service Generator
 */

public class ServiceGenerator {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(final Context context) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                                      @Override
                                      public Response intercept(@NonNull Chain chain) throws IOException {

                                          Request request = chain.request().newBuilder()
                                                  .addHeader(AppConstants.CONTENT_TYPE_KEY, AppConstants.CONTENT_TYPE_VALUE).build();
                                          return chain.proceed(request);
                                      }
                                  });

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
