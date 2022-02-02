package com.example.weatherapp.model.network

import com.example.weatherapp.model.dataclass.Weather
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("current.json")
     fun getData(@Query("key")apiKey:String , @Query("q")location:String , @Query("aqi")api:String):Single<Weather>
}