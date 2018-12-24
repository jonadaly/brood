package com.jonadaly.brood.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jonadaly.brood.R
import com.jonadaly.brood.db.entities.Chicken
import kotlinx.android.synthetic.main.chicken_list_item.view.*

class ChickenAdapter(val items: ArrayList<Chicken>, val context: Context): RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.chicken_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.chickenName.text = items.get(position).name
        holder.chickenStatus.text = items.get(position).status
        holder.chickenTemp.text = "${items.get(position).temperature?.toInt()}°"
        val icon: String? = items.get(position).openweatherIconId
        Glide.with(context).load("http://openweathermap.org/img/w/$icon.png").into(holder.chickenWeather)
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val chickenName = view.chickenName
    val chickenStatus = view.chickenStatus
    val chickenTemp = view.chickenTemp
    val chickenWeather = view.chickenWeather
}