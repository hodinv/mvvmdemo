package com.hodinv.spacex

import android.app.Application
import android.content.Context
import com.hodinv.spacex.interactors.interactorsModule
import com.hodinv.spacex.model.modelModule
import com.hodinv.spacex.repository.network.UrlProvider
import com.hodinv.spacex.repository.repositoryModule
import com.hodinv.spacex.ui.rokets.RoketsViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class App : Application(), KodeinAware, UrlProvider {
    override val baseUrl: String
        get() = getString(R.string.api_url)

    override val kodein by lazy {
        Kodein {
            bind<Context>() with singleton { this@App }
            bind<UrlProvider>() with singleton { this@App }
            import(repositoryModule)
            import(interactorsModule)
            import(modelModule)

            bind<RoketsViewModel>() with provider { RoketsViewModel() }
        }
    }

    override fun onCreate() {
        super.onCreate()
        // force lazy init

        kodein.run { }

    }

}