package com.hodinv.spacex.interactors.rockets

import com.hodinv.spacex.model.domain.Rocket
import io.reactivex.Single

interface RocketsInteractor {
    fun getRockets(page: Int = 0): Single<List<Rocket>>
}