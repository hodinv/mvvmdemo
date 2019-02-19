package com.hodinv.spacex.ui.rokets

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hodinv.spacex.model.domain.Rocket
import kotlinx.android.synthetic.main.listitem_rocket.view.*

class RocketViewHolder(
    val view: View,
    onDetail: (rocket: Rocket) -> Unit
) : RecyclerView.ViewHolder(view) {
    var currentRocket: Rocket? = null

    init {
        view.setOnClickListener {
            currentRocket?.also {
                onDetail(it)
            }
        }
    }

    fun setRocket(rocket: Rocket) {
        currentRocket = rocket
        view.txtName.text = rocket.name
        view.txtCountry.text = rocket.country
        view.txtEngines.text = "${rocket.engines} eng."
    }

}