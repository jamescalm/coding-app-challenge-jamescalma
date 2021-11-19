package com.appetiser.coding_app_challenge_jamescalma.di.component

import android.app.Application
import com.appetiser.coding_app_challenge_jamescalma.TrackListApp
import com.appetiser.coding_app_challenge_jamescalma.di.builder.ActivityBuilder
import com.appetiser.coding_app_challenge_jamescalma.di.builder.FragmentBuilder
import com.appetiser.coding_app_challenge_jamescalma.di.module.AppModule
import com.appetiser.coding_app_challenge_jamescalma.di.module.NetModule
import com.appetiser.coding_app_challenge_jamescalma.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetModule::class,
        ViewModelModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }

    fun inject(app : TrackListApp)
}