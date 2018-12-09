package com.mearsk.weatherreport.adapter

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mearsk.weatherreport.R
import com.mearsk.weatherreport.model.WeatherList

class WeatherAdapter(var weatherList: ArrayList<WeatherList>) :
    BaseAdapter<WeatherList, BaseAdapter.BaseViewHolder>(weatherList) {
    var context: Context? = null

    override fun getItemViewType(position: Int): Int {
        return R.layout.adapter_weather_list
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseViewHolder {
        context = p0.context
        val view = LayoutInflater.from(p0.context).inflate(p1, p0, false)
        return WeatherViewHolder(view)
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        holder as WeatherViewHolder
        holder.area.text = item.name
        holder.temperature.text = "${String.format("%.2f", (item.main.temp - 273.0)).toDouble().toString()} C"
        holder.tempType.text = String.format(
            context?.resources!!.getString(R.string.weather_reports),
            item.weather[0].main,
            item.weather[0].description
        )
    }

    inner class WeatherViewHolder(itemView: View) : BaseAdapter.BaseViewHolder(itemView) {
        var area = itemView.findViewById(R.id.tempArea) as AppCompatTextView
        var temperature = itemView.findViewById(R.id.tempMin) as AppCompatTextView
        var tempType = itemView.findViewById(R.id.tempType) as AppCompatTextView
    }
}

