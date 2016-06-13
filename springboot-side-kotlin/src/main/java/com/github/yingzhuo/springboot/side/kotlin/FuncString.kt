package com.github.yingzhuo.springboot.side.kotlin

import org.apache.commons.lang3.RandomStringUtils
import java.util.*

fun uuid(short: Boolean = true): String =
        if (short) UUID.randomUUID().toString().replace("-", "") else UUID.randomUUID().toString()

fun randomString(count: Int, letters: Boolean = true, numbers: Boolean = true): String =
        RandomStringUtils.random(count, letters, numbers)

