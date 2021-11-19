package com.appetiser.coding_app_challenge_jamescalma.di.module

import com.appetiser.coding_app_challenge_jamescalma.TrackListApp
import com.appetiser.coding_app_challenge_jamescalma.api.TrackHttp
import com.appetiser.coding_app_challenge_jamescalma.api.interceptor.InternetConnectionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {
    companion object {
        const val API_NEW : String = "API_NEW"
        const val baseURL : String = "https://itunes.apple.com/"
    }

    @Provides
    @Singleton
    fun provideInternetInterceptor(app:TrackListApp) = InternetConnectionInterceptor(app)

    @Provides
    @Singleton
    fun providesOkHttpClient(internetConnectionInterceptor: InternetConnectionInterceptor) : OkHttpClient {
        val interceptor =  HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(internetConnectionInterceptor)
                .hostnameVerifier { _, _ -> true }
                .build()
    }

    @Provides
    @Singleton
    @Named(API_NEW)
    fun providesRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providesTrackHttp(@Named(API_NEW) retrofit: Retrofit) : TrackHttp = retrofit.create(TrackHttp::class.java)
}