package com.example.superhero

import com.google.gson.annotations.SerializedName


data class Heroes(
    @SerializedName("id") var id:String,
    @SerializedName("name") var name:String,
    @SerializedName("powerstats") var powerstats:HeroPowerStats,
    @SerializedName("biography") var biography:HeroBiography,
    @SerializedName("appearnceval") var appearanceval:HeroAppearance,
    @SerializedName("work") var work:HeroWork,
    @SerializedName("connections") var connections:HeroConnections,
    @SerializedName("image") var image:HeroImage
    )