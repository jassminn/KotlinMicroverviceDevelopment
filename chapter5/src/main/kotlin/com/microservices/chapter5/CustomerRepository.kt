package com.microservices.chapter5

import jakarta.annotation.PostConstruct
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.findById
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.mongodb.core.remove
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

//interface CustomerRepository : ReactiveCrudRepository<Customer, Int>
@Repository
class CustomerRepository(private val template: ReactiveMongoTemplate){
//    fun create(customer: Mono<Customer>) = template.save(customer)

    companion object {
        val initialCustomers = listOf(Customer(1, "spring"),
            Customer(2,"Kotlin"),
            Customer(3, "Microservice", Customer.Telephone("+11","011")))
    }

    @PostConstruct
    fun initializeRepository() = initialCustomers.map(Customer::toMono).map(this::create).map(Mono<Customer>::subscribe)

    fun create(customer: Mono<Customer>) = template.save(customer)

    fun findById(id: Int) = template.findById<Customer>(id)

    fun deleteById(id: Int) = template.remove<Customer>(Query(where("_id").isEqualTo(id)))

    fun findCustomer(nameFilter: String) = template.find<Customer>(Query(where("name").regex(".*$nameFilter.*","i")))
}