package com.mikerusetsky.appcinema

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.mikerusetsky.appcinema.data.Entity.TmdbResultsDto
import retrofit2.http.Path

interface TmdbApi {
    @GET("3/movie/popular")
    fun getFilms(
        @Path("category") category: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): retrofit2.Call <TmdbResultsDto>
}