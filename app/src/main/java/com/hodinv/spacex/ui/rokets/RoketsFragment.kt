package com.hodinv.spacex.ui.rokets


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hodinv.spacex.R
import com.hodinv.spacex.mvvm.MvvmFragment
import com.hodinv.spacex.mvvm.viewModelLazyInstance
import org.kodein.di.generic.instance

class RoketsFragment : MvvmFragment<RoketsViewModel, RoketsRouter>() {
    override val router: RoketsRouter by instance()

    companion object {
        fun newInstance() = RoketsFragment()
    }

    override val viewModel: RoketsViewModel by viewModelLazyInstance()

    override fun initViewModel() {
        super.initViewModel()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

}
