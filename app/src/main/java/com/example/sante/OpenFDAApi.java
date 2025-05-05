package com.example.sante;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenFDAApi {

    @GET("drug/label.json")
    Call<MedicamentResponse> getMedicaments(@Query("search") String query, @Query("limit") int limit);
}
