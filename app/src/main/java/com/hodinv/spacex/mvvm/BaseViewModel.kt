package com.hodinv.spacex.mvvm

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel<R : MvvmRouter> : ViewModel() {
    var router: R? = null
    val allTasks = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        allTasks.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        allTasks.dispose()
    }
}