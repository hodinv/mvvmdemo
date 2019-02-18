package com.hodinv.spacex.model

import com.google.gson.Gson
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton


val modelModule = Kodein.Module("Model module") {
    bind<Gson>() with singleton { Gson() }
}