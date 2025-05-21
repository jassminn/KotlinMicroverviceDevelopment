package com.microservices.chapter3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentMap

@RestController
class CustomerController {
    @Autowired
    lateinit var customers : ConcurrentMap<Int, Customer>

    @RequestMapping(value = arrayOf("/customer", "/customer/{id}"), method = arrayOf(RequestMethod.GET))
    fun getCustomer(@PathVariable id : Int = 1) = customers[id] //Customer(1, "Kotlin")

    @RequestMapping(value = arrayOf("/customers"), method = arrayOf(RequestMethod.GET))
    fun searchCustomers(@RequestParam(required = false, defaultValue = "") nameFilter : String) =
        customers.filter{
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int, Customer>::value).toList()

    @RequestMapping(value = arrayOf("/customer/"), method = arrayOf(RequestMethod.POST))
    fun createCustomer(@RequestBody customer : Customer) {
        customers[customer.id] = customer
    }

    @RequestMapping(value = arrayOf("/customer/{id}"), method = arrayOf(RequestMethod.DELETE))
    fun deleteCustomer(@PathVariable id : Int) = customers.remove(id)

    @RequestMapping(value = arrayOf("/customer/{id}"), method = arrayOf(RequestMethod.PUT))
    fun updateCustomer(@PathVariable id : Int, @RequestBody customer : Customer) {
        customers.remove(id)
        customers[customer.id] = customer
    }
}