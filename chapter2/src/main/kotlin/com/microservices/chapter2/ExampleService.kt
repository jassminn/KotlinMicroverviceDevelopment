package com.microservices.chapter2

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

interface ServiceInterface {
    fun getHello(name: String) : String
}
// @Service
class ExampleService : ServiceInterface {
    override fun getHello(name : String) = "$text $name"

    @Value(value = "\${service.message.text}")
    private lateinit var text : String

//    @Value(value = "#{4+3}")
//    private lateinit var result1 : Number
//
//    @Value(value = "#{\${one.value} div \${another.value}}")
//    private lateinit var result2 : Number
}