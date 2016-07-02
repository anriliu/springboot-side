package com.github.yingzhuo.springboot.side.kotlin

import com.github.yingzhuo.springboot.side.util.EmojiUtils
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.time.DateUtils
import java.nio.charset.Charset
import java.util.*

inline fun String?.ifNotBlank(lambda: (String) -> Unit): String? {
    if (!this.isNullOrBlank()) {
        lambda.invoke(this!!)
    }
    return this
}

inline fun String?.ifNotEmpty(lambda: (String) -> Unit): String? {
    if (!this.isNullOrEmpty()) {
        lambda.invoke(this!!)
    }
    return this
}

fun String.abbreviate(maxSize: Int): String = StringUtils.abbreviate(this, maxSize)

fun String.abbreviateMiddle(maxSize: Int, middle: String = "."): String = StringUtils.abbreviateMiddle(this, middle, maxSize)

fun String.strip(chars: String): String = remove { it in chars }

inline fun String.remove(lambda: (Char) -> Boolean): String = this.filterNot(lambda)

fun String.removeWhitespace(): String = remove { it.isWhitespace() }

fun String.removeSpace(): String = remove { it.equals(" ") }

fun String.leftPad(size: Int, padString: String = " "): String = StringUtils.leftPad(this, size, padString)

fun String.rightPad(size: Int, padString: String = " "): String = StringUtils.rightPad(this, size, padString)

fun String.center(size: Int, padString: String = " "): String = StringUtils.center(this, size, padString)

fun String.md5(): String =
        DigestUtils.md5Hex(this)

fun String.sha1(): String =
        DigestUtils.sha1Hex(this)

fun String.base64Encode(charset: Charset = Charsets.UTF_8): String =
        Base64.getUrlEncoder().encodeToString(this.toByteArray(charset))

fun String.base64Decode(charset: Charset = Charsets.UTF_8): String =
        Base64.getUrlDecoder().decode(this.toByteArray(charset)).toString(charset)

fun String.toDate(vararg patterns: String): Date =
        DateUtils.parseDate(this, *patterns)

fun String.toCalendar(vararg patterns: String): Calendar =
        DateUtils.toCalendar(this.toDate(*patterns))

fun String.removeEmojis(encoding: String = "UTF-8"): String =
        EmojiUtils.removeEmojis(this, encoding)
