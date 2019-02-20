package com.hodinv.spacex.repository.network.api

import com.hodinv.spacex.model.network.Rocket
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RocketsService {
    @GET("/v3/rockets")
    fun getRockets(@Query("limit") limit: Int? = null, @Query("offset") offset: Int? = null): Call<List<Rocket>>


    @GET("/v3/rockets/{id}")
    fun getRocket(@Path("id") id: String): Call<Rocket>

}