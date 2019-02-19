package com.hodinv.spacex.repository

import com.hodinv.spacex.repository.network.NetworkService
import com.hodinv.spacex.repository.network.NetworkServiceImpl
import com.hodinv.spacex.repository.network.api.RocketsService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val repositoryModule = Kodein.Module("Repository module") {
    bind<NetworkService>() with singleton { NetworkServiceImpl(instance()) }
    bind<RocketsService>() with singleton { instance<NetworkService>().rocketsService }
}