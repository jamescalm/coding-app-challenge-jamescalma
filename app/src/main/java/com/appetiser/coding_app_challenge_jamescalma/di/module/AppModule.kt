package com.appetiser.coding_app_challenge_jamescalma.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.appetiser.coding_app_challenge_jamescalma.TrackListApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun providesTrackListApplication(app: Application) :TrackListApp = app as TrackListApp

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun providesPreferences(context: Context) : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
}