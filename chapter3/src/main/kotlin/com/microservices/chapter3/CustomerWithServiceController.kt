package com.microservices.chapter3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = arrayOf("/service"))
class CustomerWithServiceController {
    @Autowired
    private lateinit var customerService : CustomerService

    @RequestMapping(value = arrayOf("/customer", "/customer/{id}"), method = arrayOf(RequestMethod.GET))
    fun getCustomer(@PathVariable id : Int = 1) : ResponseEntity<Customer?> = customerService.getCustomer(id)

    @RequestMapping(value = arrayOf("/customers"), method = arrayOf(RequestMethod.GET))
    fun searchCustomers(@RequestParam(required = false, defaultValue = "") nameFilter : String) : ResponseEntity<List<Customer>?> =
        customerService.searchCustomers(nameFilter)

    @RequestMapping(value = arrayOf("/customer/"), method = arrayOf(RequestMethod.POST))
    fun createCustomer(@RequestBody customer : Customer) : ResponseEntity<Unit?> =
        customerService.createCustomer(customer)


    @RequestMapping(value = arrayOf("/customer/{id}"), method = arrayOf(RequestMethod.DELETE))
    fun deleteCustomer(@PathVariable id : Int) : ResponseEntity<Unit?> = customerService.deleteCustomer(id)

    @RequestMapping(value = arrayOf("/customer/{id}"), method = arrayOf(RequestMethod.PUT))
    fun updateCustomer(@PathVariable id : Int, @RequestBody customer : Customer) : ResponseEntity<Unit?> =
        customerService.updateCustomer(id, customer)

}