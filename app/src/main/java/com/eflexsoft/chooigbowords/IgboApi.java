package com.eflexsoft.chooigbowords;

import com.eflexsoft.chooigbowords.model.IgboApiResponse;
import com.eflexsoft.chooigbowords.util.Util;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IgboApi {

    @Headers({"X-API-Key: 658ba048-c7f2-4d48-94fb-f13d1055ecea"})
    @GET("words")
    Call<List<IgboApiResponse>> IGBO_API_RESPONSE_CALL(@QueryMap Map<String,Object> map);

}