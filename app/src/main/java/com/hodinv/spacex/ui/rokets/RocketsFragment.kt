package com.hodinv.spacex.ui.rokets


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hodinv.spacex.R
import com.hodinv.spacex.mvvm.MvvmFragment
import com.hodinv.spacex.mvvm.viewModelLazyInstance
import kotlinx.android.synthetic.main.fragment_main.*
import org.kodein.di.generic.instance

class RocketsFragment : MvvmFragment<RocketsViewModel, RocketsRouter>() {
    override val router: RocketsRouter by instance()

    companion object {
        fun newInstance() = RocketsFragment()
    }

    override val viewModel: RocketsViewModel by viewModelLazyInstance()

    private val adapter = RocketsAdapter {
        viewModel.showRocket(it)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.loading.observe(this, Observer {
            progress.visibility = if (it) View.VISIBLE else View.GONE
            if (!it) {
                refresh.isRefreshing = false
            }
        })
        viewModel.rockets.observe(this, Observer {
            adapter.submitList(it)
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context)
        list.layoutManager = linearLayoutManager
        list.adapter = adapter
        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearLayoutManager.childCount
                val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                if ((visibleItemCount + firstVisibleItemPosition) >= (viewModel.rockets.value?.size) ?: 0
                    && firstVisibleItemPosition >= 0
                ) {
                    viewModel.loadNextPage()
                }
            }
        })
        refresh.setOnRefreshListener { viewModel.refresh() }

    }

}
