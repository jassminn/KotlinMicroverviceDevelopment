package com.microservices.chapter3

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {
    companion object {
        val initialCustomers = arrayOf(Customer(1, "Kotlin", Customer.Telephone("+11", "012")),
            Customer(2, "Spring"),
            Customer(3, "microservice", Customer.Telephone("+33", "019")))
    }

    var customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int) : ResponseEntity<Customer?> {
        val customer = customers[id]
        val status = if(customer == null) throw CustomerNotFoundException("customer '$id' not found") else HttpStatus.OK
        return ResponseEntity(customer, status)
    }

    override fun createCustomer(customer: Customer) : ResponseEntity<Unit?> {
        var status = HttpStatus.BAD_REQUEST
        if(customers[customer.id] == null){
            customers[customer.id] = customer
            status = HttpStatus.CREATED
        }
        return ResponseEntity(null, status)
    }

    override fun deleteCustomer(id: Int) : ResponseEntity<Unit?> {
        var status = HttpStatus.NOT_FOUND
        if(customers[id] != null){
            customers.remove(id)
            status = HttpStatus.OK
        }
        return ResponseEntity(null, status)
    }

    override fun updateCustomer(id: Int, customer: Customer) : ResponseEntity<Unit?> {
        var status = HttpStatus.NOT_FOUND
        if(customers[id] != null){
            deleteCustomer(id)
            createCustomer(customer)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(null, status)
    }

    override fun searchCustomers(nameFilter: String): ResponseEntity<List<Customer>?> {
        val customerList = customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int,Customer>::value).toList()
        val status = if(customerList.size == 0 ) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(customerList, status)
    }


}