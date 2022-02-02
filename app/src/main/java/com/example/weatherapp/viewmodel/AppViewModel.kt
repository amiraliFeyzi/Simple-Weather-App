package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.dataclass.Weather
import com.example.weatherapp.model.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

import javax.inject.Inject
@HiltViewModel
class AppViewModel @Inject constructor(private val appRepository: AppRepository):ViewModel() {
    private val _data = MutableLiveData<Weather>()
    val weatherLiveData:LiveData<Weather> get() = _data

    private val compositeDisposable = CompositeDisposable()

    init {
        getData()
    }

    private fun getData(){
        appRepository.getData("7c73fa525e0f422f802142932222801", "Tehran" , "no")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<Weather>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: Weather) {
                    _data.value = t
                }

                override fun onError(e: Throwable) {
                   Timber.e(e.toString())
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}