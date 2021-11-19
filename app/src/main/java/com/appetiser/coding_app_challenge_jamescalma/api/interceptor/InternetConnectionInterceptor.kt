package com.appetiser.coding_app_challenge_jamescalma.api.interceptor


import com.appetiser.coding_app_challenge_jamescalma.R
import com.appetiser.coding_app_challenge_jamescalma.TrackListApp
import okhttp3.Interceptor
import okhttp3.Response

class InternetConnectionInterceptor(var app: TrackListApp) : Interceptor  {
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!app.isAppOnline()){
            app.showMessage(R.string.internet_unavailable)
        }
        return chain.proceed(chain.request())
    }
}