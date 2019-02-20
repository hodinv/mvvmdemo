package com.hodinv.spacex.interactors


import com.hodinv.spacex.interactors.rockets.RocketsInteractor
import com.hodinv.spacex.interactors.rockets.RocketsInteractorImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val interactorsModule = Kodein.Module("Interactors module") {
    bind<RocketsInteractor>() with singleton { RocketsInteractorImpl(instance()) }
}