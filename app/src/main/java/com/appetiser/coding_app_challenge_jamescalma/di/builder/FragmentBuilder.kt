package com.appetiser.coding_app_challenge_jamescalma.di.builder

import com.appetiser.coding_app_challenge_jamescalma.di.scope.FragmentScope
import com.appetiser.coding_app_challenge_jamescalma.ui.tracklist.TrackListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun provideTrackListFragment(): TrackListFragment
}