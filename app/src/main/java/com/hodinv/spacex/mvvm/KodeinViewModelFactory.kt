package com.hodinv.spacex.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.generic.instance

inline fun <reified ViewModelT : BaseViewModel<R>, R : MvvmRouter> MvvmFragment<ViewModelT, R>.viewModelLazyInstance() =
    lazy {
        ViewModelProviders
            .of(this, object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return this@viewModelLazyInstance.kodein.run {
                        val viewModel by instance<ViewModelT>()
                        viewModel
                    } as T
                }
            })
            .get(ViewModelT::class.java)
    }


inline fun <reified ViewModelT : BaseViewModel<R>, R : MvvmRouter> MvvmFragment<ViewModelT, R>.viewModelInstance() =
    ViewModelProviders
        .of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return this@viewModelInstance.kodein.run {
                    val viewModel by instance<ViewModelT>()
                    viewModel
                } as T
            }
        })
        .get(ViewModelT::class.java)

