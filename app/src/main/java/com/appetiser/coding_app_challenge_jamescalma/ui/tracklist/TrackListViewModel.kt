package com.appetiser.coding_app_challenge_jamescalma.ui.tracklist

import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appetiser.coding_app_challenge_jamescalma.TrackListApp
import com.appetiser.coding_app_challenge_jamescalma.api.TrackHttp
import com.appetiser.coding_app_challenge_jamescalma.model.Constants
import com.appetiser.coding_app_challenge_jamescalma.model.TrackList
import com.appetiser.coding_app_challenge_jamescalma.util.LoadingState
import com.appetiser.coding_app_challenge_jamescalma.util.Status
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrackListViewModel @Inject constructor(private val trackHttp: TrackHttp) : ViewModel() {
    private val disposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private var _trackList = MutableLiveData<TrackList>()
    var trackList : LiveData<TrackList> = _trackList

    private var _unique = MutableLiveData<String>()
    var unique : LiveData<String> = _unique

    private var _trackListLoader = MutableLiveData<LoadingState>()
    var trackListLoader : LiveData<LoadingState> = _trackListLoader

    val TAG = "TrackListVM"

    /** This function gets the value list of tracks from the API endpoint*/
    fun getTrackList(){
        trackHttp.getTracks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                Log.d(TAG, "Subscribing...")
                _trackListLoader.value = LoadingState.loading()
            }
            .subscribe ({
                Log.d(TAG, "${it.resultCount}")
                _trackList.value = it
                _trackListLoader.value = LoadingState.success("Successfully loaded Track List")
            }, {
                Log.d(TAG, "Error: ${it.localizedMessage}")
                it.printStackTrace()
                _trackListLoader.value = LoadingState.error("Failed to load Track List")
            })
            .addTo(disposables)

    }

    /** This function saves the trackId*/
    fun saveRecentTracks(unique: String){
        _unique.value = unique
        TrackListApp.sharedPreferences?.edit {
            putString(Constants.PREF_RECENT_TRACKS, unique)
        }
    }
    /** This function saves the collectionId*/
    fun saveRecentTrackCollection(collection: String){
        TrackListApp.sharedPreferences?.edit {
            putString(Constants.PREF_RECENT_TRACKS_COLLECTION, collection)
        }
    }
}