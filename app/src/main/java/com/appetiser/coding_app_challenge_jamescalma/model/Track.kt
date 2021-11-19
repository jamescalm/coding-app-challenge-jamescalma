package com.appetiser.coding_app_challenge_jamescalma.model

import android.os.Parcelable
import com.appetiser.coding_app_challenge_jamescalma.extensions.formattedString
import kotlinx.parcelize.Parcelize

@Parcelize
data class Track (
    var wrapperType : String? = null,
    var kind : String? = null,
    var artistId : Int? = null,
    var collectionId : Int? = null,
    var trackId : Int? = null,
    var artistName : String? = null,
    var collectionName : String? = null,
    var trackName : String? = null,
    var collectionCensoredName : String? = null,
    var trackCensoredName : String? = null,
    var collectionViewUrl : String? = null,
    var artworkUrl30 : String? = null,
    var artworkUrl60 : String? = null,
    var artworkUrl100 : String? = null,
    var collectionPrice : Double? = null,
    var trackPrice : Double? = null,
    var releaseDate : String? = null,
    var collectionExplicitness : String? = null,
    var trackExplicitness : String? = null,
    var discCount : Int? = null,
    var discNumber : Int? = null,
    var trackCount : Int? = null,
    var trackNumber : Int? = null,
    var trackTimeMillis : Int? = null,
    var country : String? = null,
    var currency : String? = null,
    var primaryGenreName : String? = null,
    var description : String? = null,
    var longDescription : String? = null,
    var stampDate : Long? = null
) : Parcelable {

    fun getTrackPrice() : String {
        return if (trackPrice != null && trackPrice != 0.0) "$${String.format("%.2f", trackPrice)}" else "Free"
    }

    fun getValidTrackName() : String? {
        return if (trackName != "null" && !trackName.isNullOrEmpty()) trackName else collectionName
    }

    fun getValidDescription() : String {
        return if (!longDescription.isNullOrEmpty() && longDescription != "null") longDescription!!.formattedString()
        else if (!description.isNullOrEmpty() && description != "null") description!!.formattedString() else "No Description"
    }

}
