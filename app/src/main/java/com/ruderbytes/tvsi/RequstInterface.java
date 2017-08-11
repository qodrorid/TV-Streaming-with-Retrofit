package com.ruderbytes.tvsi;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by muhammad on 10/07/17.
 */

public interface RequstInterface {

    @GET("streaming.json")
    Call<JSONResponse> getJSON();
}
