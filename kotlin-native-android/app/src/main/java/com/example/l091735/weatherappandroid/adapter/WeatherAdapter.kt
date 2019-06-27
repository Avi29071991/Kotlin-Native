package com.example.l091735.weatherappandroid.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.avinash.kotlin.native.common.WeatherDataConverter
import com.avinash.kotlin.native.common.networkModel.DailyData
import com.example.l091735.weatherappandroid.R
import getDate

class WeatherAdapter constructor(
    itemClickListener: WeatherAdapter.OnItemClickListener,
    layoutInflater: LayoutInflater,
    private val dataConverter: WeatherDataConverter
) : RecyclerView.Adapter<WeatherAdapter.DailyViewHolder>() {

    private var listener: WeatherAdapter.OnItemClickListener = itemClickListener
    private var dailyList = ArrayList<DailyData>()
    private var inflater: LayoutInflater = layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val rootView = inflater.inflate(R.layout.weather_row, parent, false)
        return DailyViewHolder(rootView, listener)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val data: DailyData? = getItem(position)
        data?.let {

            it.temperatureMin?.let { minTemp ->
                holder.valMin?.text = dataConverter.getCelcius(minTemp).toString()
            }

            it.temperatureMax?.let { maxTemp ->
                holder.valMax?.text = dataConverter.getCelcius(maxTemp).toString()
            }

            it.summary?.let { summary ->
                holder.textCon?.text = summary
            }

            it.icon?.let { icon ->
                holder.weatherIcon?.setImageResource(setIcon(icon))
            }

            it.time?.let { timeInMillis ->
                val dateArray = getDate(time = timeInMillis)
                if (dateArray.isNotEmpty()) {
                    holder.tvDay?.text = dateArray[0]
                    holder.tvDate?.text = "${dateArray[1]} ${dateArray[2]}"
                }
            }
        }
    }

    private fun setIcon(type: String): Int {
        return when (type) {
            WeatherDataConverter.RAIN, WeatherDataConverter.HAIL,
            WeatherDataConverter.THUNDERSTORM -> {
                R.drawable.w_09n
            }
            WeatherDataConverter.CLEAR_DAY, WeatherDataConverter.CLEAR_NIGHT,
            WeatherDataConverter.SNOW, WeatherDataConverter.WIND, WeatherDataConverter.SLEET -> {
                R.drawable.w_01d
            }
            WeatherDataConverter.CLOUDY, WeatherDataConverter.FOG,
            WeatherDataConverter.PARTLY_CLOUDY_DAY, WeatherDataConverter.PARTLY_CLOUDY_NIGHT -> {
                R.drawable.w_03d
            }
            else -> {
                R.drawable.w_01d
            }
        }
    }

    override fun getItemCount(): Int {
        return dailyList.size
    }

    fun setData(dataList: List<DailyData>) {
        dailyList.clear()
        for (data in dataList) {
            dailyList.add(data)
        }
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onRowClicked(dailyItemData: DailyData?)
    }


    inner class DailyViewHolder internal constructor(v: View, listener: WeatherAdapter.OnItemClickListener) :
        RecyclerView.ViewHolder(v), View.OnClickListener {
        private var itemListener: WeatherAdapter.OnItemClickListener = listener
        var view: View = v
        var container: View? = view.findViewById(R.id.container)
        var weatherIcon: ImageView? = view.findViewById(R.id.weather_icon)
        var tvDay: TextView? = view.findViewById(R.id.tvDay)
        var tvDate: TextView? = view.findViewById(R.id.tvDate)
        var valMin: TextView? = view.findViewById(R.id.valMin)
        var valMax: TextView? = view.findViewById(R.id.valMax)
        var textCon: TextView? = view.findViewById(R.id.textCon)

        init {
            container?.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            itemListener.onRowClicked(getItem(adapterPosition))
        }
    }

    fun getItem(position: Int): DailyData? {
        return if (!dailyList.isEmpty()) dailyList[position] else null
    }
}