package com.appetiser.coding_app_challenge_jamescalma.extensions

import androidx.core.text.HtmlCompat
import java.text.SimpleDateFormat
import java.util.*

/** This function converts HTML string to proper string*/
fun String.formattedString() : String {
    val str = this
    return HtmlCompat.fromHtml(str, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}