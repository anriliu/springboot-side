package com.github.yingzhuo.springboot.side.kotlin.json

import com.github.yingzhuo.springboot.side.restful.Json
import org.springframework.http.ResponseEntity

fun Json.toResponseEntity(): ResponseEntity<Json> =
        asResponseEntity()
