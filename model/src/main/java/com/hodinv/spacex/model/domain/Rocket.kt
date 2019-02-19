package com.hodinv.spacex.model.domain

data class Rocket(
    val id: String,
    val name: String,
    val country: String,
    val engines: Int
)