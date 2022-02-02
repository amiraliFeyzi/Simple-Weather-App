package com.example.weatherapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.viewmodel.AppViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.IOException
import java.time.OffsetDateTime
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModel:AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isDark = isDarkTheme(this)
        if (isDark)
            binding.imageView.setImageResource(R.drawable.night)
        else
            binding.imageView.setImageResource(R.drawable.background)


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val offset: OffsetDateTime = OffsetDateTime.now()
            binding.dayTxt.text = offset.dayOfWeek.toString()
        }

        viewModel.weatherLiveData.observe(this){
            checkWeather(it.current.condition.text)
            binding.numberTxt.text = it.current.temp_c.toString()
            binding.statusWeatherTxt.text =it.current.condition.text
            binding.cloudValueTxt.text = it.current.cloud.toString()
            binding.pressureMbValueTxt.text = it.current.pressure_mb.toString()
            binding.windMphValueTxt.text = it.current.wind_mph.toString()
            //Picasso.get().load(it.current.condition.icon).into(binding.iconImg)
        }


    }

    fun checkWeather(status:String){
        when(status){
            "Sunny" -> binding.iconImg.setImageResource(R.drawable.sunny)
            "Snow" -> binding.iconImg.setImageDrawable(ContextCompat.getDrawable(this , R.drawable.snow))
            "Rainy"-> binding.iconImg.setImageDrawable(ContextCompat.getDrawable(this , R.drawable.rainy))
            "Wind" -> binding.iconImg.setImageDrawable(ContextCompat.getDrawable(this , R.drawable.wind))

        }

    }

    fun isDarkTheme(activity: Activity): Boolean {
        return activity.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

}