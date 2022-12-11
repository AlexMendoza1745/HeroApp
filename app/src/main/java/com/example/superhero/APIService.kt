package com.example.superhero

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    suspend fun getHeroesByName(@Url url:String):Response<HeroesResponse>

    @GET
    suspend fun getAllHeroes(@Url url: String):Response<Heroes>
}