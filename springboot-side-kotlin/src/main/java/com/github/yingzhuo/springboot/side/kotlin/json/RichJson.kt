package com.github.yingzhuo.springboot.side.kotlin.json

import com.github.yingzhuo.springboot.side.restful.Json
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun Json.toResponseEntity(): ResponseEntity<Json> =
        asResponseEntity()

infix fun HttpStatus.and(payload: Map<String, Any>): ResponseEntity<Json> {
    val json = Json.create(this)
    for ((k, v) in payload) {
        json.addPayload(k, v)
    }
    return json.toResponseEntity()
}

infix fun HttpStatus.and(pair: Pair<String, Any>): ResponseEntity<Json> {
    val json = Json.create(this)
    json.addPayload(pair.first, pair.second)
    return json.toResponseEntity()
}

infix fun HttpStatus.and(errors: Iterable<String>): ResponseEntity<Json> {
    val json = Json.create(this)
    json.addErrors(errors)
    return json.toResponseEntity()
}
