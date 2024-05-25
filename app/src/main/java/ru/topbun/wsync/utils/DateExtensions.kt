package ru.topbun.wsync.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.toTime(): String {
    val format = SimpleDateFormat("HH:mm",Locale.getDefault())
    return format.format(this.time)
}

fun Date.toShortString(): String {
    val format = SimpleDateFormat("dd MMM")
    return format.format(this.time)
}

fun Date.toLongString(): String{
    val calendar = Calendar.getInstance().apply{time = this@toLongString}
    val tomorrow = Calendar.getInstance()
    tomorrow.add(Calendar.DAY_OF_YEAR, 1)
    if (calendar.get(Calendar.YEAR) == tomorrow.get(Calendar.YEAR) &&
        calendar.get(Calendar.DAY_OF_YEAR) == tomorrow.get(Calendar.DAY_OF_YEAR)) {
        return "Завтра"
    }
    val dateFormat = SimpleDateFormat("d MMMM", Locale("ru"))
    return dateFormat.format(this)
}