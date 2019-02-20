package com.hodinv.spacex.ui.rocketdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.hodinv.spacex.R
import com.hodinv.spacex.mvvm.MvvmFragment
import com.hodinv.spacex.mvvm.viewModelLazyInstance
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.toolbar_rocket.*
import org.kodein.di.generic.instance

class RocketDetailFragment : MvvmFragment<RocketDetailViewModel, RocketDetailRouter>() {
    override val viewModel: RocketDetailViewModel by viewModelLazyInstance()
    override val router: RocketDetailRouter by instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (toolbar as Toolbar).apply {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            title = ""
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.init(arguments?.getString(KEY) ?: "")
        viewModel.loading.observe(this, Observer {
            progress.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.rocketName.observe(this, Observer {
            txtTitle.text = it
        })
    }


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