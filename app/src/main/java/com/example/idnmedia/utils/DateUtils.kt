package com.example.idnmedia.utils

import com.example.idnmedia.utils.DateUtils.DateFormat.ReadableDate
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun convertStringToDate(dateInString: String, format: DateFormat): Date? {
        val formatter = SimpleDateFormat(format.pattern, Locale.getDefault())
        return formatter.parse(dateInString)
    }

    fun Date.toDateString(): String {
        val format = SimpleDateFormat(ReadableDate.pattern, Locale.getDefault())
        return format.format(this)
    }

    enum class DateFormat(val pattern: String) {
        Simple("yyyy-MM-dd"),
        Complete("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
        ReadableDate("EEEE, dd MMMM yyyy")
    }
}
