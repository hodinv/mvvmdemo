package com.hodinv.spacex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hodinv.spacex.ui.rocketdetail.RocketDetailFragment
import com.hodinv.spacex.ui.rocketdetail.RocketDetailRouter
import com.hodinv.spacex.ui.rokets.RocketsFragment
import com.hodinv.spacex.ui.rokets.RocketsRouter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.kcontext
import org.kodein.di.generic.provider

class MainActivity : AppCompatActivity(), KodeinAware,
    RocketsRouter,
    RocketDetailRouter {
    override fun showRocket(id: String) {
        startFragmentWithStacking(RocketDetailFragment.getInstance(id))
    }

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
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            startFragment(RocketsFragment.newInstance())
        }
    }


    /**
     * Replace current content fragment with new one
     * @param newFragment fragment to show
     */
    private fun startFragment(newFragment: androidx.fragment.app.Fragment) {
        for (i in 0 until supportFragmentManager.backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newFragment, TAG_FRAGMENT)
        transaction.commit()
    }


    /**
     * Adds current fragment ot back stack and shows new fragment
     * @param newFragment fragment to show
     */
    private fun startFragmentWithStacking(newFragment: androidx.fragment.app.Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newFragment, TAG_FRAGMENT).addToBackStack(null)
        transaction.commit()
    }


    companion object {
        const val TAG_FRAGMENT = "currentFragment"
    }
}
