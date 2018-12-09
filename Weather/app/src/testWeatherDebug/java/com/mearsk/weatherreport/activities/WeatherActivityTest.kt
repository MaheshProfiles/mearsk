package com.mearsk.weatherreport.activities

import com.mearsk.weatherreport.adapter.WeatherAdapter
import com.mearsk.weatherreport.presenter.WeatherViewPresenter
import org.junit.Assert
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import org.powermock.core.classloader.annotations.PrepareForTest

@RunWith(PowerMockRunner::class)
@PrepareForTest(WeatherActivity::class, WeatherAdapter::class, WeatherViewPresenter::class)
class WeatherActivityTest {
    private val URL = "http://api.openweathermap.org/data/2.5/find&"
    lateinit var spyWeatherActivity: WeatherActivity

    @Test
    fun checkTestFun() {
//        assertEquals("Hii", "Hii")
        assertTrue(true)
    }

}