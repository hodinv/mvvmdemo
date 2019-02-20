package com.hodinv.spacex.mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

abstract class MvvmFragment<T : BaseViewModel<R>, R : MvvmRouter> : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    abstract val viewModel: T
    abstract val router: R

    open fun initViewModel() {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.router = router
    }

    override fun onPause() {
        super.onPause()
        viewModel.router = null
    }
}