package com.microservices.chapter9

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    lateinit var customerService: CustomerService
    @Test
    @DisplayName("존재하는 사용자 정보를 id로 조회하여 결과 존재하는지와 사용자명 일치 검증")
    fun getCustomer() {
        val customer = customerService.getCustomer(1)
        assertNotNull(customer)
        assertEquals(customer?.name, "Kotlin")
    }

    @Test
    @DisplayName("존재하는 사용자 정보의 개수 검증")
    fun getAllCustomers() {
        val customers = customerService.getAllCustomers()
        assertEquals(customers.size, 3)
    }

}