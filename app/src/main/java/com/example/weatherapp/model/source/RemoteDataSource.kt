package com.example.weatherapp.model.source

import com.example.weatherapp.model.dataclass.Weather
import com.example.weatherapp.model.network.ApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getData(apikey:String , location:String , api:String): Single<Weather> {
         return apiService.getData(apikey , location , api)
     }
}