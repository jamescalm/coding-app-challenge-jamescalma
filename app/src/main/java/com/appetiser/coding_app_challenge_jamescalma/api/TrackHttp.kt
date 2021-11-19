package com.appetiser.coding_app_challenge_jamescalma.api

import com.appetiser.coding_app_challenge_jamescalma.model.TrackList
import io.reactivex.Observable
import retrofit2.http.GET

interface TrackHttp {
    @GET("search?term=star&amp;country=au&amp;media=movie&amp;all")
    fun getTracks() : Observable<TrackList>
}