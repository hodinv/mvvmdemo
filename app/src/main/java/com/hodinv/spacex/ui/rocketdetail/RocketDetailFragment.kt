package com.hodinv.spacex.ui.rocketdetail

import android.os.Bundle
import com.hodinv.spacex.mvvm.MvvmFragment
import org.kodein.di.generic.instance

class RocketDetailFragment : MvvmFragment<RocketDetailViewModel, RocketDetailRouter>() {
    override val viewModel: RocketDetailViewModel by instance()
    override val router: RocketDetailRouter by instance()

    companion object {
        const val KEY = "id"
        fun getInstance(id: String): RocketDetailFragment =
            RocketDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY, id)
                }
            }
    }
}