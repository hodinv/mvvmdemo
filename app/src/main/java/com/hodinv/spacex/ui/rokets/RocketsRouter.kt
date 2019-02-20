package com.hodinv.spacex.ui.rokets

import com.hodinv.spacex.mvvm.MvvmRouter

interface RocketsRouter : MvvmRouter {
    fun showRocket(id: String)
}