package com.hodinv.spacex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hodinv.spacex.ui.main.MainFragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.kcontext
import org.kodein.di.generic.provider

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodeinContext: KodeinContext<*> = kcontext(this)

    private val _parentKodein by closestKodein()
    override val kodein: Kodein by lazy {
        Kodein {
            extend(_parentKodein)
            bind() from provider { this@MainActivity }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}
