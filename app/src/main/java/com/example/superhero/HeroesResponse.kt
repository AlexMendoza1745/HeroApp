package com.example.superhero

import com.google.gson.annotations.SerializedName

data class HeroesResponse(
    @SerializedName("response") var response:String,
    @SerializedName("result-for") var result_for:String,
    @SerializedName("results") var results:List<Heroes>
)