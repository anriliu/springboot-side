package com.github.yingzhuo.springboot.side.kotlin

import org.apache.commons.lang3.time.DateFormatUtils
import java.util.*

fun Calendar.format(pattern: String = "yyyy-MM-dd HH:mm:ss.SSS"): String =
        DateFormatUtils.format(this, pattern)

