package com.example.milkrecordkeeping.util

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    fun getDate() : String {
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
        return df.format(Date())
    }
}