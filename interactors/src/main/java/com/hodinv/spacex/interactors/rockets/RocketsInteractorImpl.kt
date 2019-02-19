package com.hodinv.spacex.interactors.rockets

import com.hodinv.spacex.model.domain.Rocket
import com.hodinv.spacex.repository.network.api.RocketsService
import io.reactivex.Single

class RocketsInteractorImpl(
    val rocketsService: RocketsService
) : RocketsInteractor {
    override fun getRockets(page: Int): Single<List<Rocket>> {
        return Single.create { emitter ->
            val response = rocketsService.getRockets(PAGE_SIZE, page * PAGE_SIZE).execute()
            
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}