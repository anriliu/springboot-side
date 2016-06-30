package com.github.yingzhuo.springboot.side.kotlin

import org.apache.commons.lang3.time.DateFormatUtils
import org.apache.commons.lang3.time.DateUtils
import java.util.*

fun Date.toString(pattern: String): String = format(pattern)

fun Date.format(pattern: String = "yyyy-MM-dd HH:mm:ss.SSS"): String =
        DateFormatUtils.format(this, pattern)

fun Date.toCalendar(): Calendar = DateUtils.toCalendar(this)

fun Date.truncate(field: Int = Calendar.DATE): Date =
        DateUtils.truncate(this, field)

fun Date.ceiling(field: Int = Calendar.DATE): Date =
        DateUtils.ceiling(this, field)

fun Date.round(field: Int = Calendar.DATE): Date =
        DateUtils.round(this, field)

fun Date.addYears(amount: Int = 1): Date =
        DateUtils.addYears(this, amount)

fun Date.addMonths(amount: Int = 1): Date =
        DateUtils.addMonths(this, amount)

fun Date.addDays(amount: Int = 1): Date =
        DateUtils.addDays(this, amount)

fun Date.addHours(amount: Int = 1): Date =
        DateUtils.addHours(this, amount)

fun Date.addMinutes(amount: Int = 1): Date =
        DateUtils.addMinutes(this, amount)

fun Date.addSeconds(amount: Int = 1): Date =
        DateUtils.addSeconds(this, amount)

fun Date.addMilliseconds(amount: Int = 1): Date =
        DateUtils.addMilliseconds(this, amount)
