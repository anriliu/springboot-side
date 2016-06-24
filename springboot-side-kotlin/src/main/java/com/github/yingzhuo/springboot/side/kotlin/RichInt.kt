package com.github.yingzhuo.springboot.side.kotlin

import java.util.*

fun Int.toDate(): Date = toLong().toDate()

fun Int.toCalendar(): Calendar = toLong().toCalendar()
