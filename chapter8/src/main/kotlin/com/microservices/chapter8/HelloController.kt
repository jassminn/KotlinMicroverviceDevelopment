package com.microservices.chapter8

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.concurrent.atomic.AtomicInteger

@RestController
class HelloController {
    private val id: String = UUID.randomUUID().toString()

    companion object {
        val total: AtomicInteger = AtomicInteger()
    }

    @GetMapping("/hello")
    fun hello() = "Hello I'm $id and I have been called ${total.incrementAndGet()} time(s)"
}