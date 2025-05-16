package com.project.qiblaa.data

data class Date(
    val day: Int,
    val month: Int,
    val year: Int,
    val dayOfWeek: String,
    val monthName: String,
    val isToday: Boolean = false
)
