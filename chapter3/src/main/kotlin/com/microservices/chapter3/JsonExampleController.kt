package com.microservices.chapter3

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JsonExampleController {
    @GetMapping(value = arrayOf("/json"))
    fun getJson() = SimpleObject()

    @GetMapping(value = arrayOf("/json2"))
    fun getJsonWithData() = SimpleObjectWithData("minsu", "inchon")

    @GetMapping(value = arrayOf("/json3"))
    fun getJsonWithComplexData() = ComplexObject(object1 = SimpleObjectWithData("sanggil", "seoul"))
}