package com.github.yingzhuo.springboot.side.kotlin

inline fun <T> T?.ifPresent(lambda: (Any) -> Unit): T? = this.ifNotNull(lambda)

inline fun <T> T?.ifNotNull(lambda: (Any) -> Unit): T? {
    if (this != null) {
        lambda(this)
    }
    return this
}
