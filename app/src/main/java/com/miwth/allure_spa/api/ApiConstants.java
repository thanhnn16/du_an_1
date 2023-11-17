package com.miwth.allure_spa.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiConstants {
    //    BASE URL
    public static final String API_BASE_URL = "http://192.168.1.10:8000/api/";
//    public static final String API_BASE_URL = "http://172.16.100.118:8000/api/";

    //    RESOURCE URL
    public static final String RESOURCE_URL = "http://192.168.1.10:8000/storage/";
    //    AUTH URL
    public static final String LOGIN_URL = "login";
    public static final String REGISTER_URL = "register";
    //    COSMETICS URL
    public static final String COSMETICS_URL = "cosmetics";
    public static final String COSMETIC_URL = "cosmetics/{id}";
    public static final String COSMETIC_REVIEW_URL = "cosmetics/{id}/reviews/{review_id}";
    //    TREATMENTS URL
    public static final String TREATMENTS_URL = "treatments";
    public static final String TREATMENT_URL = "treatments/{id}";
    public static final String TREATMENT_REVIEW_URL = "treatments/{id}/reviews/{review_id}";
    //    USERS URL
    public static final String USERS_URL = "users";
    public static final String USER_URL = "users/{id}";
    public static final String USER_REVIEW_URL = "users/{id}/reviews/{review_id}";
    //    APPOINTMENTS URL
    public static final String APPOINTMENTS_URL = "appointments";
    public static final String APPOINTMENT_URL = "appointments/{id}";
    //    COMMENTS URL
    public static final String COMMENTS_URL = "comments";
    public static final String COMMENT_URL = "comments/{id}";
    //    INVOICES URL
    public static final String INVOICES_URL = "invoices";
    public static final String INVOICE_URL = "invoices/{id}";
    //    INVOICE DETAILS URL
    public static final String INVOICE_DETAILS_URL = "invoice_details";
    public static final String INVOICE_DETAIL_URL = "invoice_details/{id}";
    //    PAYMENTS URL
    public static final String PAYMENTS_URL = "payments";
    public static final String PAYMENT_URL = "payments/{id}";
    //    PAYMENT METHODS URL
    public static final String PAYMENT_METHODS_URL = "payment_methods";
    public static final String PAYMENT_METHOD_URL = "payment_methods/{id}";
    //    CARTS URL
    public static final String CARTS_URL = "carts";
    public static final String CART_URL = "carts/{id}";
    //    NOTIFICATIONS URL
    public static final String NOTIFICATIONS_URL = "notifications";
    public static final String NOTIFICATION_URL = "notifications/{id}";
    //    NOTIFICATION TYPES URL
    public static final String NOTIFICATION_TYPES_URL = "notification_types";
    public static final String NOTIFICATION_TYPE_URL = "notification_types/{id}";

    //    AUTH HEADERS
    public static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder().header("Accept", "application/json");
            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        }).build();
    }

    //    MOSHI BUILDER
    public static Moshi getMoshi() {
        return new Moshi.Builder()
                .add(new DateAdapterZ())
                .build();
    }

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
                .build();
    }

    //
    public static class DateAdapterZ {
        @ToJson
        String toJson(Date date) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.US).format(date);
        }

        @FromJson
        Date fromJson(String date) throws ParseException {
            try {
                return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.US).parse(date);
            } catch (ParseException e) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(date);
            }
        }
    }
}
