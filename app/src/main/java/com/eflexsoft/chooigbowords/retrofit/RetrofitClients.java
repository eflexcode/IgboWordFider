package com.eflexsoft.chooigbowords.retrofit;

import com.eflexsoft.chooigbowords.util.Util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClients {

    static RetrofitClients retrofitClients;
    private Retrofit retrofit;

    //using the sigleton
    public RetrofitClients() {
    }

    public static RetrofitClients retrofitClientsInstance() {

        if (retrofitClients == null) {
            retrofitClients = new RetrofitClients();
        }
        return retrofitClients;
    }

    public Retrofit getRetrofit() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Util.IGBO_API_LINK_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
