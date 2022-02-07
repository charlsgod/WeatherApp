package com.charlsgod.weatherapp.ui.weather.view

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.charlsgod.weatherapp.R
import com.charlsgod.weatherapp.databinding.ItemForecastBinding
import com.charlsgod.weatherapp.model.Weather
import com.charlsgod.weatherapp.utils.inflate
import com.charlsgod.weatherapp.utils.loadUrl
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    var forecasts: List<Weather> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_forecast, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = forecasts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = forecasts[position]
        holder.bind(weather)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemForecastBinding.bind(view)
        fun bind(weather: Weather) = with(binding) {

            var date = ""
            val dateApiFormat = "yyyy-MM-dd"
            val backendSdf: DateFormat = SimpleDateFormat(dateApiFormat, Locale.US)

            val dateFormat = "EEEE, MMMM d"
            val sdf: DateFormat = SimpleDateFormat(dateFormat, Locale.US)

            try {
                val dateData = weather.date
                val parse = backendSdf.parse(dateData)!!
                date = sdf.format(parse)
            } catch (e: ParseException) {
                e.message?.let { Log.e("ForecastAdapter", it) }
            }
            val temp = "${weather.avgtempF}°F"
            val desc = weather.hourly[4].weatherDesc[0].value

            val iconUrl = weather.hourly[4].weatherIconUrl[0].value

            tvDate.text = date
            tvAvgTemp.text = temp
            tvDescription.text = desc
            ivIcon.loadUrl(iconUrl)
        }
    }
}