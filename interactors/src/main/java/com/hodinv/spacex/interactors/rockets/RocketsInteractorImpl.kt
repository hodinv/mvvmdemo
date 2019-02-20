package com.hodinv.spacex.interactors.rockets

import com.hodinv.spacex.model.domain.Rocket
import com.hodinv.spacex.repository.network.api.RocketsService
import io.reactivex.Single

class RocketsInteractorImpl(
    val rocketsService: RocketsService
) : RocketsInteractor {
    override fun getRocket(id: String): Single<Rocket> {
        return Single.create { emitter ->
            val response = rocketsService.getRocket(id).execute()
            if (response.isSuccessful && response.body() != null) {
                emitter.onSuccess(getRocket(response.body()!!))
            } else {
                emitter.onError(RuntimeException("No rocket with od = $id"))
            }
        }
    }

    override fun getRockets(page: Int): Single<List<Rocket>> {
        return Single.create { emitter ->
            val response = rocketsService.getRockets(PAGE_SIZE, page * PAGE_SIZE).execute()
            if (response.isSuccessful && (response.body()?.size ?: 0) > 0) {
                emitter.onSuccess(response.body()!!.map { networkItem ->
                    getRocket(networkItem)
                })
            } else {
                emitter.onSuccess(emptyList())
            }
        }
    }

    private fun getRocket(networkItem: com.hodinv.spacex.model.network.Rocket): Rocket {
        return Rocket(
            networkItem.id,
            networkItem.name,
            networkItem.country,
            networkItem.engines.number
        )
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}