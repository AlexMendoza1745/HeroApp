package com.example.superhero

import com.google.gson.annotations.SerializedName

data class HeroConnections (
    @SerializedName("group-affiliation") var group_affiliation:String,
    @SerializedName("relatives") var relatives:String
)