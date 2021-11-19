package com.appetiser.coding_app_challenge_jamescalma.di.module

import com.appetiser.coding_app_challenge_jamescalma.api.TrackHttp
import com.appetiser.coding_app_challenge_jamescalma.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun providesTrackListViewModelFactory(track: TrackHttp): ViewModelFactory {
        return ViewModelFactory(track)
    }
}