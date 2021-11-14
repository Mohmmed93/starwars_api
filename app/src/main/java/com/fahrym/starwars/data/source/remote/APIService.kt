package com.fahrym.starwars.data.source.remote

import com.fahrym.starwars.data.source.model.Models
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    companion object {
        const val BASE_URL = "https://swapi.dev/api/"
    }

    @GET("planets/?")
    fun getPlanets(
        @Query("page") pageNum: Int?,
    ): Single<Models.BasePlanet>
}

