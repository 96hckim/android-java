package com.hocheol.dagger2demo.di;

import com.hocheol.dagger2demo.model.RecyclerList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroServiceInterface {

    @GET("repositories")
    Call<RecyclerList> getDataFromAPI(@Query("q") String query);
}
