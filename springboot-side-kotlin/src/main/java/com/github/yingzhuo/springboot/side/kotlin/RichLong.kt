package com.github.yingzhuo.springboot.side.kotlin

import java.util.*

fun Long.toDate(): Date = Date(this)

fun Long.toCalendar(): Calendar = toDate().toCalendar()
