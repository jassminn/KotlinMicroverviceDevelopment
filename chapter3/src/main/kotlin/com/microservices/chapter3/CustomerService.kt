package com.microservices.chapter3

import org.springframework.http.ResponseEntity

interface CustomerService {
    fun getCustomer(id : Int) : ResponseEntity<Customer?>
    fun createCustomer(customer : Customer) : ResponseEntity<Unit?>
    fun deleteCustomer(id : Int) : ResponseEntity<Unit?>
    fun updateCustomer(id : Int, customer : Customer) : ResponseEntity<Unit?>
    fun searchCustomers(nameFilter : String) : ResponseEntity<List<Customer>?>
}