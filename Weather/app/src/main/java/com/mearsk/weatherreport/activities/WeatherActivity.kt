package com.mearsk.weatherreport.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.mearsk.weatherreport.R
import com.mearsk.weatherreport.adapter.WeatherAdapter
import com.mearsk.weatherreport.model.WeatherList
import com.mearsk.weatherreport.presenter.WeatherViewPresenter
import com.mearsk.weatherreport.view.WeatherView
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : AppCompatActivity(), WeatherView {
    var weatherAdapter: WeatherAdapter? = null
    var weatherViewPresenter: WeatherViewPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        initView()
    }

    private fun initView() {
        weatherViewPresenter = WeatherViewPresenter(this)
        rvCityList.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        rvCityList.setHasFixedSize(true)
        searchCity.setOnClickListener {
            val city = enterSearch.text.toString()
            if (!city.isEmpty())
                weatherViewPresenter?.fetchAndLoadData(enterSearch.text.toString(), 10)
            else
                Toast.makeText(this, "Please Enter the City Name", Toast.LENGTH_SHORT).show()
        }
    }

    override fun setWeatherSearchCompleted(weatherList: ArrayList<WeatherList>?) {
        weatherList?.let {
            if (it.size > 0) {
                weatherAdapter = WeatherAdapter(it)
                rvCityList.visibility = View.VISIBLE
                rvCityList.adapter = weatherAdapter
                noProducts.visibility = View.GONE
            } else {
                noProducts.visibility = View.VISIBLE
                rvCityList.visibility = View.GONE
            }
        }
    }

    override fun showProgressBar() {
        progress_circular.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_circular.visibility = View.GONE
    }

    override fun onFailedApi(e: Throwable) {
        progress_circular.visibility = View.GONE
        Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
    }

    override fun networkFailed(message: String?) {
    }
}
