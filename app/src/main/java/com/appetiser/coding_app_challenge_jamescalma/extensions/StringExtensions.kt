package com.appetiser.coding_app_challenge_jamescalma.extensions

import androidx.core.text.HtmlCompat
import java.text.SimpleDateFormat
import java.util.*

fun String.formattedString() : String {
    val str = this
    return HtmlCompat.fromHtml(str, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}