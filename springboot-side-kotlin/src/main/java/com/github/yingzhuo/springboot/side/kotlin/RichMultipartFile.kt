package com.github.yingzhuo.springboot.side.kotlin

import org.springframework.web.multipart.MultipartFile

inline fun MultipartFile?.ifPresent(lambda: (MultipartFile) -> Unit) {
    if (this != null && !this.isEmpty && this.size != 0L) {
        lambda(this)
    }
}

inline fun MultipartFile?.ifAbsent(lambda: (MultipartFile) -> Unit) {
    if (this == null) return

    if (this.isEmpty || this.size == 0L) {
        lambda(this)
    }
}
