package com.hodinv.spacex.ui.rokets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hodinv.spacex.R
import com.hodinv.spacex.model.domain.Rocket

class RocketsAdapter(private val onDetail: (rocket: Rocket) -> Unit) :
    ListAdapter<Rocket, RocketViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        return RocketViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.listitem_rocket, parent, false),
            onDetail
        )
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        holder.setRocket(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Rocket>() {
            override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
                return false
            }

        }
    }
}