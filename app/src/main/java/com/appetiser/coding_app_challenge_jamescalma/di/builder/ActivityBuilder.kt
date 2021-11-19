package com.appetiser.coding_app_challenge_jamescalma.di.builder

import com.appetiser.coding_app_challenge_jamescalma.di.scope.ActivityScope
import com.appetiser.coding_app_challenge_jamescalma.ui.mainactivity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}