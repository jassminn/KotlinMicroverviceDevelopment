package com.microservices.chapter7

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingsController {
    @GetMapping("/greetings")
    fun greetings() = "hello from a Docker"
}