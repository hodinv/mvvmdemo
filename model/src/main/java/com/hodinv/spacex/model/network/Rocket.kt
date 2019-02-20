package com.hodinv.spacex.model.network

import com.google.gson.annotations.SerializedName

data class Rocket(

    @SerializedName("rocket_id")
    val id: String,

    @SerializedName("rocket_name")
    val name: String,

    val country: String,

    val engines: EngineInfo
)