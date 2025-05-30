package com.microservices.chapter9

interface CustomerService {
    fun getCustomer(id: Int): Customer?
    fun getAllCustomers(): List<Customer>
}