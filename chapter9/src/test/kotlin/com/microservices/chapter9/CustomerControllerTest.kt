package com.microservices.chapter9

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mockito.reset
import org.mockito.Mockito.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    lateinit var mocMvc: MockMvc

    @MockitoBean
    lateinit var customerService: CustomerService

    @Test
    @DisplayName("존재하는 사용자 정보를 id로 조회하여 결과 존재하는지와 사용자명 일치 검증")
    fun getCustomer() {
        given(customerService.getCustomer(1)).willReturn(Customer(1,"test"))

        mocMvc.perform(get("/customer/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$.id").value(1))
            .andExpect(jsonPath("\$.name").value("test"))
            .andDo(print())

        then(customerService).should(times(1)).getCustomer(1)
        then(customerService).shouldHaveNoMoreInteractions()

//        mocMvc.perform(get("/customer/1"))
//            .andExpect(status().isOk)
//            .andExpect(jsonPath("\$.id").value(1))
//            .andExpect(jsonPath("\$.name").value("Kotlin"))
//            .andDo(print())
    }

    @Test
    @DisplayName("존재하는 사용자 정보의 개수 검증")
    fun getCustomers() {
        given(customerService.getAllCustomers()).willReturn(listOf(Customer(1,"test"), Customer(2,"mocks")))

        mocMvc.perform(get("/customers"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$").isArray)
            .andExpect(jsonPath("\$[0].id").value(1))
            .andExpect(jsonPath("\$[0].name").value("test"))
            .andExpect(jsonPath("\$[1].id").value(2))
            .andExpect(jsonPath("\$[1].name").value("mocks"))
            .andDo(print())

        then(customerService).should(times(1)).getAllCustomers()
        then(customerService).shouldHaveNoMoreInteractions()

        reset(customerService)

//        mocMvc.perform(get("/customers"))
//            .andExpect(status().isOk)
//            .andExpect(jsonPath("\$").isArray)
//            .andExpect(jsonPath("\$[0].id").value(1))
//            .andExpect(jsonPath("\$[0].name").value("Kotlin"))
//            .andExpect(jsonPath("\$[1].id").value(2))
//            .andExpect(jsonPath("\$[1].name").value("Spring"))
//            .andExpect(jsonPath("\$[2].id").value(3))
//            .andExpect(jsonPath("\$[2].name").value("Microservice"))
//            .andDo(print())
    }

}