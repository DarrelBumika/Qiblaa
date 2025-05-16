package com.project.qiblaa.data

data class Time(
    val hour: Int,
    val minute: Int,
    val second: Int,
    val amPm: String = "",
    val is24HourFormat: Boolean = false,
    val timeZone: String = ""
)