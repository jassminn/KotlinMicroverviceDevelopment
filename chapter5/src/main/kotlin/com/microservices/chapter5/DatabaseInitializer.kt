package com.microservices.chapter5

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component

@Component
class DatabaseInitializer {
//    @Autowired
//    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var mongoOperations: ReactiveMongoOperations

//    companion object {
//        val initialCustomers = listOf(Customer(1, "spring"),
//            Customer(2,"Kotlin"),
//            Customer(3, "Microservice", Customer.Telephone("+11","011")))
//    }

    @PostConstruct
    fun initData(){
        mongoOperations.collectionExists("Customers").subscribe{
            if (it != true) mongoOperations.createCollection("Customers").subscribe{
                println("Customers collections created")
            } else {
                println("Customers collections already exist")
            }
//            customerRepository.save(Customer(1,"spring")).subscribe{
//                println("Default customers created")
//            }
//            customerRepository.saveAll(initialCustomers).subscribe { println("Default customers created") }
        }
    }
}