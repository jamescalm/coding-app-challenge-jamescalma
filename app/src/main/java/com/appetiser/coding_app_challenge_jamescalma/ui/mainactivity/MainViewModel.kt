package com.appetiser.coding_app_challenge_jamescalma.ui.mainactivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appetiser.coding_app_challenge_jamescalma.`interface`.HasDisposable
import com.appetiser.coding_app_challenge_jamescalma.api.TrackHttp
import com.appetiser.coding_app_challenge_jamescalma.model.TrackList
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val trackHttp: TrackHttp) : ViewModel() {
    private val disposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private var _trackList = MutableLiveData<TrackList>()
    var trackList : LiveData<TrackList> = _trackList

    fun getTrackList(){
        trackHttp.getTracks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                Log.d("MainVM", "Subscribing...")
            }
            .subscribe ({
                val gson = Gson()
                Log.d("MainVM", "${it.resultCount}")
            }, {
                Log.d("MainVM", "Error: ${it.localizedMessage}")
                it.printStackTrace()
            })
            .addTo(disposables)

    }
}