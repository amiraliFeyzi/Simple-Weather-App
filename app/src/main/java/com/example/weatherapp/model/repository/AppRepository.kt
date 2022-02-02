package com.example.weatherapp.model.repository

import com.example.weatherapp.model.dataclass.Weather
import com.example.weatherapp.model.source.RemoteDataSource
import io.reactivex.rxjava3.core.Single

import javax.inject.Inject

class AppRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

     fun getData(apikey:String , location:String , api:String): Single<Weather> {
        return remoteDataSource.getData(apikey , location , api)

    }
}