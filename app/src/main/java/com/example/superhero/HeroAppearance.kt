package com.example.superhero

import com.google.gson.annotations.SerializedName

data class HeroAppearance (
    @SerializedName("gender") var gender:String,
    @SerializedName("race") var race:String,
    //@SerializedName("height") var height:String,
   // @SerializedName("weight") var weight:String,
    @SerializedName("height") var height:List<String>,
    @SerializedName("weight") var weight:List<String>,
    @SerializedName("eye-color") var eye_color:String,
    @SerializedName("hair-color") var hair_color:String,
  //  @SerializedName("publisher") var publisher:String,
  //  @SerializedName("aligment") var aligment:String
)