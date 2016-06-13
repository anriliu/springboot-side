package com.github.yingzhuo.springboot.side.kotlin

import org.apache.commons.codec.digest.DigestUtils
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

fun String.md5(): String =
        DigestUtils.md5Hex(this)

fun String.sha1(): String =
        DigestUtils.sha1Hex(this)

fun String.base64Encode(charset: Charset = Charsets.UTF_8): String {
    return Base64.getUrlEncoder().encodeToString(this.toByteArray(charset))
}

fun String.base64Decode(charset: Charset = Charsets.UTF_8): String {
    return Base64.getUrlDecoder().decode(this.toByteArray(charset)).toString(charset)
}
