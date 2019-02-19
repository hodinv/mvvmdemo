package com.hodinv.spacex.repository.network

import com.hodinv.spacex.repository.network.api.RocketsService

interface NetworkService {
    val rocketsService: RocketsService
}