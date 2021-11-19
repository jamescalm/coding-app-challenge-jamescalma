package com.appetiser.coding_app_challenge_jamescalma

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appetiser.coding_app_challenge_jamescalma.api.TrackHttp
import com.appetiser.coding_app_challenge_jamescalma.ui.mainactivity.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val api: TrackHttp) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}