package com.hodinv.spacex.ui.rocketdetail

import androidx.lifecycle.MutableLiveData
import com.hodinv.spacex.interactors.rockets.RocketsInteractor
import com.hodinv.spacex.mvvm.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RocketDetailViewModel(
    val rocketsInteractor: RocketsInteractor
) : BaseViewModel<RocketDetailRouter>() {

    val rocketName = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()


    fun init(id: String) {
        loading.value = true
        addDisposable(
            rocketsInteractor
                .getRocket(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { loadedRocket ->
                    rocketName.value = loadedRocket.name
                    loading.value = false
                }
        )
    }
}