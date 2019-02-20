package com.hodinv.spacex.ui.rokets

import androidx.lifecycle.MutableLiveData
import com.hodinv.spacex.interactors.rockets.RocketsInteractor
import com.hodinv.spacex.model.domain.Rocket
import com.hodinv.spacex.mvvm.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class RocketsViewModel(
    val rocketsInteractor: RocketsInteractor
) : BaseViewModel<RocketsRouter>() {

    val rockets = MutableLiveData<List<Rocket>>()
    val loading = MutableLiveData<Boolean>()

    var nextPage = 0
    var isLast = false
    var isLoading = false
    var task: Disposable? = null


    init {
        loading.value = true
        loadNextPage()
    }

    fun refresh() {
        nextPage = 0
        isLast = false
        rockets.value = ArrayList()
        loadNextPage()
    }


    fun loadNextPage() {
        if (isLoading || isLast) {
            return
        }
        isLoading = true
        task?.dispose()
        task =
                rocketsInteractor.getRockets(nextPage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ newRockets ->
                        loading.value = false
                        isLoading = false
                        val newList = ArrayList<Rocket>(rockets.value ?: emptyList())
                        newList.addAll(newRockets)
                        rockets.value = newList
                        isLast = newRockets.isEmpty()
                        nextPage++
                    }, {
                        isLoading = false
                        loading.value = false
                    })


    }


    fun showRocket(rocket: Rocket) {
        router?.showRocket(rocket.id)
    }

}
