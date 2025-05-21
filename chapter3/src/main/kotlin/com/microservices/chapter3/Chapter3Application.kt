package com.microservices.chapter3

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class Chapter3Application {
	companion object {
		val initialCustomers = arrayOf(Customer(1, "Kotlin", Customer.Telephone("+11", "012")),
			Customer(2, "Spring", Customer.Telephone("+22", "017")),
			Customer(3, "microservice", Customer.Telephone("+33", "019")))
	}

	@Bean
	fun customers() = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))
}

fun main(args: Array<String>) {
	runApplication<Chapter3Application>(*args)
}
