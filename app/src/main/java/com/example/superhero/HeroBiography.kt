package com.example.superhero

import com.google.gson.annotations.SerializedName

data class HeroBiography  (
    @SerializedName("full-name") var full_name:String,
    @SerializedName("alter-egos") var alter_egos:String,
    //@SerializedName("aliases") var aliases:String,
    @SerializedName("aliases") var aliases:List<String>,
    //@SerializedName("durability") var durability:String,
    @SerializedName("place-of-birth") var place_of_birth:String,
    @SerializedName("fist-appearance") var first_appearance:String,
    @SerializedName("publisher") var publisher:String,
    @SerializedName("aligment") var aligment:String
)