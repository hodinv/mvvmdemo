package com.hodinv.spacex.ui.rokets

import androidx.lifecycle.MutableLiveData
import com.hodinv.spacex.interactors.rockets.RocketsInteractor
import com.hodinv.spacex.model.domain.Rocket
import com.hodinv.spacex.mvvm.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RoketsViewModel(
    val rocketsInteractor: RocketsInteractor
) : BaseViewModel<RoketsRouter>() {

    val rockets = MutableLiveData<List<Rocket>>()
    val loading = MutableLiveData<Boolean>()

    var nextPage = 0
    var isLast = false


    init {
        loading.value = true
        loadNextPage()
    }

    fun refresh() {
        nextPage = 0
        isLast = false
        rockets.value = ArrayList()
    }

    fun loadNextPage() {
        if (loading.value == true || isLast) {
            return
        }
        loading.value = nextPage == 0
        addDisposable(rocketsInteractor.getRockets(nextPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { newRockets ->
                loading.value = false
                val newList = ArrayList<Rocket>(rockets.value)
                newList.addAll(newRockets)
                rockets.value = newList
                isLast = newRockets.isEmpty()
            })
    }

    fun showRocket(rocket: Rocket) {
        router?.showRocket(rocket.id)
    }

}
