package com.microservices.chapter4

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class CustomerRouter(private var customerHandler: CustomerHandler) {
//    @Autowired
//    lateinit var customerHandler: CustomerHandler

    @Bean
//    fun customerRouters(): RouterFunction<*> = router {
    fun customerRouters() = router {
        "/functional".nest {
            "/customer".nest {
                /*
                GET("/"){
//                    ServerResponse.ok().body("hello world".toMono(), String::class.java)
//                    ok().body(Customer(1,"functional web").toMono(), Customer::class.java)
//                    it: ServerRequest -> ok().body(Customer(1, "functional web").toMono(), Customer::class.java)
                    it: ServerRequest -> customerHandler.get(it)
                }
                 */
                GET("/{id}", customerHandler::get)
                POST("/", customerHandler::create)
            }
            "/customers".nest {
                GET("/", customerHandler::search)
            }
        }
    }
}