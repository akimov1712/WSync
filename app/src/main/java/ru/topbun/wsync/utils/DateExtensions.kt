package ru.topbun.wsync.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.toTime(): String {
    val format = SimpleDateFormat("HH:mm",Locale.getDefault())
    return format.format(this.time)
}

fun Date.toDateString(): String {
    val format = SimpleDateFormat("MMM dd",Locale.ENGLISH)
    return format.format(this.time)
}