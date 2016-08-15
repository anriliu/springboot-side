package com.github.yingzhuo.springboot.side.kotlin

import org.springframework.web.multipart.MultipartFile

inline fun MultipartFile?.ifPresent(lambda: (MultipartFile) -> Unit) {
    if (this != null && !this.isEmpty) {
        lambda(this)
    }
}
